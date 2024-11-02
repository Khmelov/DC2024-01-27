package domain

import (
	e "DC-eremeev/app/errors"
	"DC-eremeev/ekafka"
	"fmt"

	"github.com/google/uuid"
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

// IDAO
func (post *Post) Exist() bool {
	// bypass to Kafka
	return true
}

func (post *Post) Save() error {
	validator := NewPostValidator(post)
	if !validator.Validate() {
		return validator.Error()
	}
	post.State = Pending
	if post.ID == 0 {
		id, _ := uuid.NewV7()
		post.ID = uint(id.ID()) * uint(id.ClockSequence())
	}
	return ekafka.ProduceToLocalTopic(ekafka.InTopic, &KafkaMessagePost{
		Cmd:  Save,
		Post: *post,
	})
}

func (post *Post) Find(id uint) error {
	err := ekafka.ProduceToLocalTopic(ekafka.InTopic, &KafkaMessagePost{
		Cmd:  Get,
		Post: Post{ID: id},
	})
	if err != nil {
		return &e.ErrNotFound{}
	}

	consumer := ekafka.WithCachedLocalConsumer(ekafka.OutTopic)
	for retry := 0; retry < 10; retry += 1 {
		msg := KafkaMessagePost{}
		if consumer.ReadMessage(&msg) {
			post.ID = msg.Post.ID
			post.IssueID = msg.Post.IssueID
			post.Content = msg.Post.Content
			post.State = msg.Post.State
			return nil
		}
	}
	return &e.ErrNotFound{}
}

func (post *Post) Delete() {
	ekafka.ProduceToLocalTopic(ekafka.InTopic, &KafkaMessagePost{
		Cmd:  Delete,
		Post: *post,
	})
}

func GetAllPosts() ([]Post, error) {
	err := ekafka.ProduceToLocalTopic(ekafka.InTopic, &KafkaMessageAllPosts{
		Cmd: All,
	})
	if err != nil {
		return nil, err
	}

	consumer := ekafka.WithCachedLocalConsumer(ekafka.OutTopic)
	for retry := 0; retry < 10; retry += 1 {
		msg := KafkaMessageAllPosts{}
		if consumer.ReadMessage(&msg) {
			return msg.Posts, nil
		}
	}
	return nil, &e.ErrNotFound{}
}

func (post *Post) RedisKey() string {
	return fmt.Sprintf("post_%d", post.ID)
}
