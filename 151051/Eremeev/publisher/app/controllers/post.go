package controllers

import (
	"github.com/gin-gonic/gin"

	"DC-eremeev/app/domain"
	"DC-eremeev/app/dto"
)

func GetPosts(c *gin.Context) ([]domain.Post, error) {
	return domain.GetAllPosts()
}

func GetPost(c *gin.Context, req *dto.SingleRecordRequest) (domain.IDAO, error) {
	return BaseGetEntity(c, req, &domain.Post{ID: req.ID})
}

func PostPosts(c *gin.Context, req *dto.CreatePostRequest) (domain.IDAO, error) {
	return BasePostEntity(c, req)
}

func UpdatePost(c *gin.Context, req *dto.UpdatePostRequest) (domain.IDAO, error) {
	return BaseUpdateEntity(c, req)
}

func DeletePost(c *gin.Context, req *dto.SingleRecordRequest) error {
	return BaseDeleteEntity(c, &domain.Post{ID: req.ID})
}
