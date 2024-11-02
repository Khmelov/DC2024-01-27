package config

import (
	"github.com/gookit/config/v2"
	"github.com/gookit/config/v2/yaml"
)

type Config struct {
	DBName     string `mapstructure:"db_name"`
	DBHost     string `mapstructure:"db_host"`
	DBPort     int    `mapstructure:"db_port"`
	DBUser     string `mapstructure:"db_user"`
	DBPassword string `mapstructure:"db_password"`

	NoSqlDBName string `mapstructure:"nosql_db_name"`
	NoSqlDBHost string `mapstructure:"nosql_db_host"`
	NoSqlDBPort int    `mapstructure:"nosql_db_port"`
}

var staticConfig Config

func LoadConfig() (*Config, error) {
	config.AddDriver(yaml.Driver)

	{
		err := config.LoadFiles("config/config.yml")
		if err != nil {
			return nil, err
		}
	}

	{
		err := config.Decode(&staticConfig)
		if err != nil {
			return nil, err
		}

		return &staticConfig, nil
	}
}

func GetConfig() *Config {
	return &staticConfig
}
