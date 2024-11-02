package domain

type EditorValidator struct {
	Editor *Editor
	ValidatorError
}

func NewEditorValidator(e *Editor) *EditorValidator {
	return &EditorValidator{
		Editor:         e,
		ValidatorError: NewValidatorError(),
	}
}

// IValidator
func (v *EditorValidator) Validate() bool {
	if !ValidateLenght(v.Editor.Login, 2, 64) {
		v.Errs["login"] = "invalid lenght"
	}
	if !ValidateLenght(v.Editor.Password, 8, 128) {
		v.Errs["password"] = "invalid lenght"
	}
	if !ValidateLenght(v.Editor.FirstName, 2, 64) {
		v.Errs["firstname"] = "invalid lenght"
	}
	if !ValidateLenght(v.Editor.LastName, 2, 64) {
		v.Errs["lastname"] = "invalid lenght"
	}

	return len(v.Errs) == 0
}
