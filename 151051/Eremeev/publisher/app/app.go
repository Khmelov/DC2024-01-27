package app

import (
	"github.com/gin-gonic/gin"
	"github.com/loopfz/gadgeto/tonic"

	"DC-eremeev/app/controllers"
)

func Run() {
	router := gin.New()

	router.Use(LimitMiddleware(5))
	tonic.SetErrorHook(ErrorHandler)

	v1 := router.Group("api/v1.0")

	v1.GET("/editors", tonic.Handler(controllers.GetEditors, 200))
	v1.GET("/editors/:id", tonic.Handler(controllers.GetEditor, 200))
	v1.POST("/editors", tonic.Handler(controllers.PostEditor, 201))
	v1.PUT("/editors", tonic.Handler(controllers.UpdateEditor, 200))
	v1.DELETE("/editors/:id", tonic.Handler(controllers.DeleteEditor, 204))

	v1.GET("issues", tonic.Handler(controllers.GetIssues, 200))
	v1.GET("issues/:id", tonic.Handler(controllers.GetIssue, 200))
	v1.POST("issues", tonic.Handler(controllers.PostIssues, 201))
	v1.PUT("issues", tonic.Handler(controllers.UpdateIssue, 200))
	v1.DELETE("issues/:id", tonic.Handler(controllers.DeleteIssue, 204))

	v1.GET("posts", tonic.Handler(controllers.GetPosts, 200))
	v1.GET("posts/:id", tonic.Handler(controllers.GetPost, 200))
	v1.POST("posts", tonic.Handler(controllers.PostPosts, 201))
	v1.PUT("posts", tonic.Handler(controllers.UpdatePost, 200))
	v1.DELETE("posts/:id", tonic.Handler(controllers.DeletePost, 204))

	v1.GET("tags", tonic.Handler(controllers.GetTags, 200))
	v1.GET("tags/:id", tonic.Handler(controllers.GetTag, 200))
	v1.POST("tags", tonic.Handler(controllers.PostTags, 201))
	v1.PUT("tags", tonic.Handler(controllers.UpdateTag, 200))
	v1.DELETE("tags/:id", tonic.Handler(controllers.DeleteTag, 204))

	router.Run(":24110")
}
