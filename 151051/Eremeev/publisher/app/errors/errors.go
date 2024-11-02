package errors

type ErrNotFound struct{}

func (err *ErrNotFound) Error() string {
	return "not found"
}

type ErrValidationError struct {
	Msg string
}

func (err *ErrValidationError) Error() string {
	return err.Msg
}

type ErrUniqueKeyViolation struct{}

func (err *ErrUniqueKeyViolation) Error() string {
	return "unique field violation"
}
