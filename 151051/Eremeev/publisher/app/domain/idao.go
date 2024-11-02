package domain

type IDAO interface {
	Exist() bool
	Save() error
	Find(uint) error
	Delete()
	RedisKey() string
}
