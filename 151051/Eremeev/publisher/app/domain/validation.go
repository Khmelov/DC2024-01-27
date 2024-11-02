package domain

import (
	e "DC-eremeev/app/errors"
	"fmt"
)

type IValidator interface {
	Validate() bool
	Error() error
}

func ValidateLenght(val string, min int, max int) bool {
	return len(val) >= min && len(val) <= max
}

type ValidatorError struct {
	Errs map[string]string
}

func (v *ValidatorError) Error() error {
	if len(v.Errs) == 0 {
		return nil
	}

	msg := ""

	for key, value := range v.Errs {
		msg = fmt.Sprintf("%s: %s; ", key, value)
	}

	return &e.ErrValidationError{Msg: msg}
}

func NewValidatorError() ValidatorError {
	return ValidatorError{
		Errs: make(map[string]string),
	}
}
