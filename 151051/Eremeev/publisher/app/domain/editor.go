package domain

import (
	e "DC-eremeev/app/errors"
	"DC-eremeev/db"
	"errors"
	"fmt"

	"gorm.io/gorm"
)

type Editor struct {
	ID        uint   `json:"id"`
	Login     string `json:"login,omitempty" gorm:"unique"`
	Password  string `json:"-"`
	FirstName string `json:"firstname,omitempty" gorm:"column:firstname"`
	LastName  string `json:"lastname,omitempty" gorm:"column:lastname"`
}

// GORM
func (Editor) TableName() string {
	return "tbl_editor"
}

// IDAO
func (editor *Editor) Exist() bool {
	exist := db.Connection().First(&Editor{}, editor.ID)
	return exist.Error == nil
}

func (editor *Editor) Save() error {
	validator := NewEditorValidator(editor)
	if !validator.Validate() {
		return validator.Error()
	}
	err := db.Connection().Save(editor).Error
	if err != nil && errors.Is(err, gorm.ErrDuplicatedKey) {
		return &e.ErrUniqueKeyViolation{}
	}
	return err
}

func (editor *Editor) Find(id uint) error {
	err := db.Connection().First(editor, id).Error
	if err != nil && errors.Is(err, gorm.ErrRecordNotFound) {
		return &e.ErrNotFound{}
	}
	return err
}

func (editor *Editor) Delete() {
	db.Connection().Delete(&Editor{}, editor.ID)
}

func (editor *Editor) RedisKey() string {
	return fmt.Sprintf("editor_%d", editor.ID)
}
