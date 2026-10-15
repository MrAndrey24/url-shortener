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
	h := handler.NewURLHandler(src)

	router := gin.Default()

	v1 := router.Group("/api/v1")
	{
		shorter := v1.Group("/shorter")
		{
			const codePath = "/:code"
			shorter.POST("", h.CreateShortUrl)
			shorter.PUT(codePath, h.UpdateShortUrl)
			shorter.GET(codePath, h.GetShortUrlByCode)
			shorter.GET(codePath+"/stats", h.GetShortUrlStats)
			shorter.DELETE(codePath, h.DeleteShortUrl)

		}
	}

	err := router.Run(":8080")
	if err != nil {
		return
	}
}
