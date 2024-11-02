package controllers

import (
	"github.com/gin-gonic/gin"

	"DC-eremeev/app/domain"
	"DC-eremeev/app/dto"
	"DC-eremeev/db"
)

func GetTags(c *gin.Context) ([]domain.Tag, error) {
	var posts []domain.Tag
	db.Connection().Find(&posts)
	return posts, nil
}

func GetTag(c *gin.Context, req *dto.SingleRecordRequest) (domain.IDAO, error) {
	return BaseGetEntity(c, req, &domain.Tag{ID: req.ID})
}

func PostTags(c *gin.Context, req *dto.CreateTagRequest) (domain.IDAO, error) {
	return BasePostEntity(c, req)
}

func UpdateTag(c *gin.Context, req *dto.UpdateTagRequest) (domain.IDAO, error) {
	return BaseUpdateEntity(c, req)
}

func DeleteTag(c *gin.Context, req *dto.SingleRecordRequest) error {
	return BaseDeleteEntity(c, &domain.Tag{ID: req.ID})
}
