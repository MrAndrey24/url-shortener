package main

import (
	"github.com/gin-gonic/gin"
	"github.com/mrandrey24/url-shortening-server/internal/handler"
	"github.com/mrandrey24/url-shortening-server/internal/repository"
	"github.com/mrandrey24/url-shortening-server/internal/service"
)

func main() {

	db := repository.InitDB("data.db")

	repo := repository.NewURLRepository(db)
	src := service.NewURLService(repo)
	ctrl := handler.NewURLHandler(src)

	router := gin.Default()

	v1 := router.Group("/api/v1")
	{
		shorter := v1.Group("/shorter")
		{
			const codePath = "/:code"
			shorter.POST("", ctrl.CreateShortUrl)
			shorter.PUT(codePath, ctrl.UpdateShortUrl)
			shorter.GET(codePath, ctrl.GetShortUrlByCode)
			shorter.GET(codePath+"/stats", ctrl.GetShortUrlStats)
			shorter.DELETE(codePath, ctrl.DeleteShortUrl)

		}
	}

	err := router.Run(":8080")
	if err != nil {
		return
	}
}
