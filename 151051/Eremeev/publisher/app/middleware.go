package app

import (
	e "DC-eremeev/app/errors"

	"github.com/gin-gonic/gin"
)

type ErrorResponse struct {
	Msg string `json:"error"`
}

func ErrorHandler(c *gin.Context, err error) (int, interface{}) {
	response := &ErrorResponse{err.Error()}

	switch err.(type) {
	case *e.ErrNotFound:
		return 404, response
	case *e.ErrUniqueKeyViolation:
		return 403, response
	default:
		return 422, response
	}
}

func LimitMiddleware(limit int) gin.HandlerFunc {
	// create a buffered channel with N spaces
	semaphore := make(chan bool, limit)

	return func(c *gin.Context) {
		select {
		case semaphore <- true: // Try putting a new val into our semaphore
			// Ok, managed to get a space in queue. execute the handler
			c.Next()

			// Don't forget to release a handle
			<-semaphore
		default:
			// Buffer full, so drop the connection. Return whatever status you want here
			return
		}
	}
}
