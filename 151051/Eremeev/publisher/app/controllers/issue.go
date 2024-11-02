package controllers

import (
	"github.com/gin-gonic/gin"

	"DC-eremeev/app/domain"
	"DC-eremeev/app/dto"
	"DC-eremeev/db"
)

func GetIssues(c *gin.Context) ([]domain.Issue, error) {
	var issues []domain.Issue
	db.Connection().Find(&issues)
	return issues, nil
}

func GetIssue(c *gin.Context, req *dto.SingleRecordRequest) (domain.IDAO, error) {
	return BaseGetEntity(c, req, &domain.Issue{ID: req.ID})
}

func PostIssues(c *gin.Context, req *dto.CreateIssueRequest) (domain.IDAO, error) {
	return BasePostEntity(c, req)
}

func UpdateIssue(c *gin.Context, req *dto.UpdateIssueRequest) (domain.IDAO, error) {
	return BaseUpdateEntity(c, req)
}

func DeleteIssue(c *gin.Context, req *dto.SingleRecordRequest) error {
	return BaseDeleteEntity(c, &domain.Issue{ID: req.ID})
}
