package domain

type IssueValidator struct {
	Issue *Issue
	ValidatorError
}

func NewIssueValidator(i *Issue) *IssueValidator {
	return &IssueValidator{
		Issue:          i,
		ValidatorError: NewValidatorError(),
	}
}

// IValidator
func (v *IssueValidator) Validate() bool {
	if !ValidateLenght(v.Issue.Title, 2, 64) {
		v.Errs["title"] = "invalid lenght"
	}
	if !ValidateLenght(v.Issue.Content, 4, 2048) {
		v.Errs["content"] = "invalid lenght"
	}

	return len(v.Errs) == 0
}
