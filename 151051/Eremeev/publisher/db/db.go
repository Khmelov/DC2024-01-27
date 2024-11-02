package db

import (
	"fmt"
	"log"

	"DC-eremeev/config"

	"github.com/golang-migrate/migrate/v4"
	_ "github.com/golang-migrate/migrate/v4/database/cassandra"
	_ "github.com/golang-migrate/migrate/v4/database/postgres"
	_ "github.com/golang-migrate/migrate/v4/source/file"

	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

func migrateCassandra(cfg *config.Config, drop bool) {
	connectionString := fmt.Sprintf(
		"cassandra://%s:%d/%s?sslmode=disable",
		cfg.NoSqlDBHost,
		cfg.NoSqlDBPort,
		cfg.NoSqlDBName,
	)
	m, err := migrate.New("file://db/migrations/nosql", connectionString)
	if err != nil {
		log.Fatalln("Could not connect to database:", err)
	}
	if drop {
		log.Println("Down migrations")
		if err := m.Down(); err != nil && err != migrate.ErrNoChange {
			log.Fatalln("Could not run Down migration:", err)
		}
	}
	log.Println("Up migrations")
	if err := m.Up(); err != nil && err != migrate.ErrNoChange {
		log.Fatalln("Could not run Up migration:", err)
	}
}

func Init(drop bool) {
	cfg := config.GetConfig()

	migrateCassandra(cfg, drop)

	connectionString := fmt.Sprintf(
		"postgres://%s:%s@%s:%d/%s?sslmode=disable",
		cfg.DBUser,
		cfg.DBPassword,
		cfg.DBHost,
		cfg.DBPort,
		cfg.DBName,
	)
	m, err := migrate.New("file://db/migrations", connectionString)
	if err != nil {
		log.Fatalln("Could not connect to database:", err)
	}

	if drop {
		log.Println("Down migrations")
		if err := m.Down(); err != nil && err != migrate.ErrNoChange {
			log.Fatalln("Could not run Down migration:", err)
		}
	}

	log.Println("Up migrations")
	if err := m.Up(); err != nil && err != migrate.ErrNoChange {
		log.Fatalln("Could not run Up migration:", err)
	}
}

var connection *gorm.DB

func Connection() *gorm.DB {
	if connection != nil {
		return connection
	}

	cfg := config.GetConfig()
	connectionString := fmt.Sprintf(
		"host=%s user=%s password=%s dbname=%s port=%d sslmode=disable",
		cfg.DBHost,
		cfg.DBUser,
		cfg.DBPassword,
		cfg.DBName,
		cfg.DBPort,
	)
	db, err := gorm.Open(postgres.Open(connectionString), &gorm.Config{TranslateError: true})
	if err != nil {
		log.Fatalln("Could not connect to database:", err)
	}
	connection = db
	return connection
}
