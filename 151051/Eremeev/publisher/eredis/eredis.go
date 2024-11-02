package eredis

import (
	"context"
	"encoding/json"
	"fmt"
	"time"

	"github.com/redis/go-redis/v9"
)

type ERedis struct {
	connection *redis.Client
}

var client *ERedis
var ctx = context.Background()

func Client() *ERedis {
	if client == nil {
		connection := redis.NewClient(
			&redis.Options{
				Addr:     "localhost:6379",
				Password: "",
				DB:       0,
			},
		)
		client = &ERedis{connection: connection}
	}

	return client
}

type TestConnectionType struct {
	Msg string
	Num int
}

func TestConnection() {
	fmt.Println("Test ERedis connection.")
	in := TestConnectionType{
		Msg: "Test",
		Num: 42,
	}
	var out TestConnectionType
	Client().Set("eredis-test", &in)
	ok := Client().Get("eredis-test", &out)
	if !ok {
		panic("ERedis connection failed on Get()")
	}
	fmt.Println(out)
}

func (c *ERedis) Set(key string, value any) {
	jsonb, _ := json.Marshal(value)
	err := c.connection.Set(ctx, key, jsonb, time.Minute*5).Err()
	if err != nil {
		panic(err)
	}
	fmt.Println("ERedis: set ", key)
}

func (c *ERedis) Get(key string, dest any) bool {
	jsonb, err := c.connection.Get(ctx, key).Bytes()
	if err == redis.Nil {
		fmt.Println("ERedis: miss ", key)
		return false
	} else if err != nil {
		panic(err)
	} else {
		json.Unmarshal(jsonb, dest)
		fmt.Println("ERedis: hit ", key)
		return true
	}
}

func (c *ERedis) Del(key string) {
	err := c.connection.Del(ctx, key).Err()
	if err != nil {
		panic(err)
	}
	fmt.Println("ERedis: delete ", key)
}
