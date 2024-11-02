package domain

type PostValidator struct {
	Post *Post
	ValidatorError
}

func NewPostValidator(p *Post) *PostValidator {
	return &PostValidator{
		Post:           p,
		ValidatorError: NewValidatorError(),
	}
}

// IValidator
func (v *PostValidator) Validate() bool {
	if !ValidateLenght(v.Post.Content, 4, 2048) {
		v.Errs["content"] = "invalid lenght"
	}

	return len(v.Errs) == 0
}
