package domain

type TagValidator struct {
	Tag *Tag
	ValidatorError
}

func NewTagValidator(t *Tag) *TagValidator {
	return &TagValidator{
		Tag:            t,
		ValidatorError: NewValidatorError(),
	}
}

// IValidator
func (v *TagValidator) Validate() bool {
	if !ValidateLenght(v.Tag.Name, 2, 32) {
		v.Errs["name"] = "invalid lenght"
	}

	return len(v.Errs) == 0
}
