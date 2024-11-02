package controllers

import (
	"github.com/gin-gonic/gin"

	"DC-eremeev/app/domain"
	"DC-eremeev/app/dto"
	"DC-eremeev/db"
)

func GetEditors(c *gin.Context) ([]domain.Editor, error) {
	var editors []domain.Editor
	db.Connection().Find(&editors)
	return editors, nil
}

func GetEditor(c *gin.Context, req *dto.SingleRecordRequest) (domain.IDAO, error) {
	return BaseGetEntity(c, req, &domain.Editor{ID: req.ID})
}

func PostEditor(c *gin.Context, req *dto.CreateEditorRequest) (domain.IDAO, error) {
	return BasePostEntity(c, req)
}

func UpdateEditor(c *gin.Context, req *dto.UpdateEditorRequest) (domain.IDAO, error) {
	return BaseUpdateEntity(c, req)
}

func DeleteEditor(c *gin.Context, req *dto.SingleRecordRequest) error {
	return BaseDeleteEntity(c, &domain.Editor{ID: req.ID})
}
