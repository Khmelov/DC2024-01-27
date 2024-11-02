module DC-eremeev/discussion

go 1.22.2

require DC-eremeev/ekafka v0.0.0

require (
	github.com/confluentinc/confluent-kafka-go/v2 v2.6.0 // indirect
	github.com/gocql/gocql v1.7.0 // indirect
	github.com/golang/snappy v0.0.3 // indirect
	github.com/google/uuid v1.6.0 // indirect
	github.com/hailocab/go-hostpool v0.0.0-20160125115350-e80d13ce29ed // indirect
	gopkg.in/inf.v0 v0.9.1 // indirect
)

replace DC-eremeev/ekafka => ../ekafka
