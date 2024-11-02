package domain

import (
	e "DC-eremeev/app/errors"
	"DC-eremeev/db"
	"errors"
	"fmt"
	"time"

	"gorm.io/gorm"
)

type Issue struct {
	ID        uint      `json:"id"`
	EditorID  uint      `json:"editorId"`
	Title     string    `json:"title"`
	Content   string    `json:"content"`
	CreatedAt time.Time `json:"created" gorm:"column:created"`
	UpdatedAt time.Time `json:"modified" gorm:"column:modified"`
}

// GORM
func (Issue) TableName() string {
	return "tbl_issue"
}

// IDAO
func (issue *Issue) Exist() bool {
	exist := db.Connection().First(&Issue{}, issue.ID)
	return exist.Error == nil
}

func (issue *Issue) Save() error {
	validator := NewIssueValidator(issue)
	if !validator.Validate() {
		return validator.Error()
	}
	err := db.Connection().Save(issue).Error
	if err != nil && errors.Is(err, gorm.ErrDuplicatedKey) {
		return &e.ErrUniqueKeyViolation{}
	}
	return err
}

func (issue *Issue) Find(id uint) error {
	err := db.Connection().First(issue, id).Error
	if err != nil && errors.Is(err, gorm.ErrRecordNotFound) {
		return &e.ErrNotFound{}
	}
	return err
}

func (issue *Issue) Delete() {
	db.Connection().Delete(&Issue{}, issue.ID)
}

func (issue *Issue) RedisKey() string {
	return fmt.Sprintf("issue_%d", issue.ID)
}
