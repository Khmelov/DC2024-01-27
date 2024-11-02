package controllers

import (
	"DC-eremeev/app/domain"
	"DC-eremeev/app/dto"
	e "DC-eremeev/app/errors"
	"DC-eremeev/eredis"

	"github.com/gin-gonic/gin"
)

func BaseGetEntity(c *gin.Context, req *dto.SingleRecordRequest, d domain.IDAO) (domain.IDAO, error) {
	ok := eredis.Client().Get(d.RedisKey(), d)
	if ok {
		return d, nil
	}

	err := d.Find(req.ID)
	if err != nil {
		return nil, err
	}

	eredis.Client().Set(d.RedisKey(), d)
	return d, nil
}

func BasePostEntity(c *gin.Context, d dto.IToDomain) (domain.IDAO, error) {
	entity := d.ToDomain()
	err := entity.Save()
	if err != nil {
		return nil, err
	}

	eredis.Client().Set(entity.RedisKey(), entity)
	return entity, nil
}

func BaseUpdateEntity(c *gin.Context, d dto.IToDomain) (domain.IDAO, error) {
	entity := d.ToDomain()

	if !entity.Exist() {
		return nil, &e.ErrNotFound{}
	}

	err := entity.Save()
	if err != nil {
		return nil, err
	}

	eredis.Client().Set(entity.RedisKey(), entity)
	return entity, nil
}

func BaseDeleteEntity(c *gin.Context, d domain.IDAO) error {
	if !d.Exist() {
		return &e.ErrNotFound{}
	}

	d.Delete()
	eredis.Client().Del(d.RedisKey())
	return nil
}
