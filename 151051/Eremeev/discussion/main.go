package main

import (
	"fmt"
	"log"
	"os"
	"os/signal"
	"syscall"

	"DC-eremeev/ekafka"

	"github.com/gocql/gocql"
)

type Post struct {
	ID      uint   `json:"id"`
	IssueID uint   `json:"issueId"`
	Content string `json:"content"`
	State   string `json:"state"`
}

type KafkaMessagePost struct {
	Cmd  string `json:"cmd"`
	Post Post   `json:"post"`
}

type KafkaMessageAllPosts struct {
	Cmd   string `json:"cmd"`
	Posts []Post `json:"posts"`
}

const (
	Pending = "PENDING"
	Approve = "APPROVE"
	Decline = "DELCINE"
)

const (
	Save   = "SAVE"
	Get    = "GET"
	Delete = "DELETE"
	All    = "ALL"
)

func nosql() *gocql.Session {
	cluster := gocql.NewCluster("localhost:9042")
	cluster.Keyspace = "distcomp"
	session, err := cluster.CreateSession()
	if err != nil {
		log.Fatal(err)
	}
	return session
}

func SavePostRecord(s *gocql.Session, post *Post) {
	err := s.Query(
		"INSERT INTO tbl_post (id, issue_id, content, state) VALUES (?, ?, ?, ?)",
		post.ID,
		post.IssueID,
		post.Content,
		post.State,
	).Exec()
	if err != nil {
		panic(err)
	}
}

func FindPostRecord(s *gocql.Session, post *Post) {
	err := s.Query("SELECT * from tbl_post WHERE id = ?", post.ID).Scan(&post.ID, &post.IssueID, &post.Content, &post.State)
	if err != nil {
		panic(err)
	}
}

func FindAllPostRecords(s *gocql.Session) []Post {
	iter := s.Query("SELECT * from tbl_post").Iter()
	var posts []Post
	run := true

	for run {
		var post Post
		run = iter.Scan(&post.ID, &post.IssueID, &post.Content, &post.State)
		if run {
			posts = append(posts, post)
		}
	}

	return posts
}

func DeletePostRecord(s *gocql.Session, post *Post) {
	err := s.Query(
		"DELETE FROM tbl_post WHERE id = ?",
		post.ID,
	).Exec()
	if err != nil {
		panic(err)
	}
}

func main() {
	cassandra := nosql()
	consumer := ekafka.WithLocalConsumer(ekafka.InTopic)
	signals := make(chan os.Signal, 1)
	signal.Notify(signals, os.Interrupt, syscall.SIGTERM)

PollLoop:

	select {
	case <-signals:
		fmt.Println("Exit.")
		consumer.Close()
		cassandra.Close()
		os.Exit(0)
	default:
		msg := KafkaMessagePost{}
		if consumer.ReadMessage(&msg) {
			fmt.Println(msg)
			post := msg.Post

			if msg.Cmd == Save {
				post.State = Approve
				SavePostRecord(cassandra, &post)
			}

			if msg.Cmd == Get {
				FindPostRecord(cassandra, &msg.Post)
				ekafka.ProduceToLocalTopic(ekafka.OutTopic, msg)
			}

			if msg.Cmd == Delete {
				DeletePostRecord(cassandra, &post)
			}

			if msg.Cmd == All {
				ekafka.ProduceToLocalTopic(ekafka.OutTopic, KafkaMessageAllPosts{
					Posts: FindAllPostRecords(cassandra),
				})
			}
		}
	}

	goto PollLoop
}
