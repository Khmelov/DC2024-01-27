package domain

import (
	e "DC-eremeev/app/errors"
	"DC-eremeev/db"
	"errors"
	"fmt"

	"gorm.io/gorm"
)

type Tag struct {
	ID   uint   `json:"id"`
	Name string `json:"name"`
}

// GORM
func (Tag) TableName() string {
	return "tbl_tag"
}

// IDAO
func (tag *Tag) Exist() bool {
	exist := db.Connection().First(&Tag{}, tag.ID)
	return exist.Error == nil
}

func (tag *Tag) Save() error {
	validator := NewTagValidator(tag)
	if !validator.Validate() {
		return validator.Error()
	}
	err := db.Connection().Save(tag).Error
	if err != nil && errors.Is(err, gorm.ErrDuplicatedKey) {
		return &e.ErrUniqueKeyViolation{}
	}
	return err
}

func (tag *Tag) Find(id uint) error {
	err := db.Connection().First(tag, id).Error
	if err != nil && errors.Is(err, gorm.ErrRecordNotFound) {
		return &e.ErrNotFound{}
	}
	return err
}

func (tag *Tag) Delete() {
	db.Connection().Delete(&Tag{}, tag.ID)
}

func (tag *Tag) RedisKey() string {
	return fmt.Sprintf("tag_%d", tag.ID)
}
