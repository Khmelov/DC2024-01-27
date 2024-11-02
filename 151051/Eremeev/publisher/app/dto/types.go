package dto

import "DC-eremeev/app/domain"

type IToDomain interface {
	ToDomain() domain.IDAO
}

type CreateEditorRequest struct {
	Login     string `json:"login"`
	Password  string `json:"password"`
	FirstName string `json:"firstname"`
	LastName  string `json:"lastname"`
}

type UpdateEditorRequest struct {
	ID uint `json:"id"`
	CreateEditorRequest
}

type SingleRecordRequest struct {
	ID uint `path:"id"`
}

func (data *CreateEditorRequest) ToDomain() domain.IDAO {
	return &domain.Editor{
		Login:     data.Login,
		Password:  data.Password,
		FirstName: data.FirstName,
		LastName:  data.LastName,
	}
}

func (data *UpdateEditorRequest) ToDomain() domain.IDAO {
	editor := data.CreateEditorRequest.ToDomain().(*domain.Editor)
	editor.ID = data.ID
	return editor
}

type CreateIssueRequest struct {
	EditorID uint   `json:"editorId"`
	Title    string `json:"title"`
	Content  string `json:"content"`
}

type UpdateIssueRequest struct {
	ID uint `json:"id"`
	CreateIssueRequest
}

func (data *CreateIssueRequest) ToDomain() domain.IDAO {
	return &domain.Issue{
		EditorID: data.EditorID,
		Title:    data.Title,
		Content:  data.Content,
	}
}

func (data *UpdateIssueRequest) ToDomain() domain.IDAO {
	issue := data.CreateIssueRequest.ToDomain().(*domain.Issue)
	issue.ID = data.ID
	return issue
}

type CreatePostRequest struct {
	IssueID uint   `json:"issueId"`
	Content string `json:"content"`
}

type UpdatePostRequest struct {
	ID uint `json:"id"`
	CreatePostRequest
}

func (data *CreatePostRequest) ToDomain() domain.IDAO {
	return &domain.Post{
		IssueID: data.IssueID,
		Content: data.Content,
	}
}

func (data *UpdatePostRequest) ToDomain() domain.IDAO {
	post := data.CreatePostRequest.ToDomain().(*domain.Post)
	post.ID = data.ID
	return post
}

type CreateTagRequest struct {
	Name string `json:"name"`
}

type UpdateTagRequest struct {
	ID uint `json:"id"`
	CreateTagRequest
}

func (data *CreateTagRequest) ToDomain() domain.IDAO {
	return &domain.Tag{
		Name: data.Name,
	}
}

func (data *UpdateTagRequest) ToDomain() domain.IDAO {
	tag := data.CreateTagRequest.ToDomain().(*domain.Tag)
	tag.ID = data.ID
	return tag
}
