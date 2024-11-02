package main

import (
	"DC-eremeev/app"
	"DC-eremeev/config"
	"DC-eremeev/db"
	"DC-eremeev/ekafka"
	"DC-eremeev/eredis"
	"flag"
	"log"
)

func main() {
	dropSchema := flag.Bool("drop", false, "drop schema on start")
	flag.Parse()

	_, err := config.LoadConfig()
	if err != nil {
		log.Fatalln("Can not load config:", err.Error())
	}

	db.Init(*dropSchema)

	// Test ERedis
	eredis.TestConnection()

	// Init OutTopic consumer
	ekafka.WithCachedLocalConsumer(ekafka.OutTopic)
	defer ekafka.WithCachedLocalConsumer(ekafka.OutTopic).Close()

	app.Run()
}
