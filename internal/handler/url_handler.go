package handler

import (
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/mrandrey24/url-shortening-server/internal/domain"
	"github.com/mrandrey24/url-shortening-server/internal/handler/dto"
	"github.com/mrandrey24/url-shortening-server/internal/handler/mapper"
	"github.com/mrandrey24/url-shortening-server/internal/service"
)

// UrlHandler Dependency Injection
type UrlHandler struct {
	service *service.URLService
}

// NewURLHandler Constructor for UrlHandler
func NewURLHandler(s *service.URLService) *UrlHandler {
	return &UrlHandler{
		service: s,
	}
}

func (h *UrlHandler) CreateShortUrl(c *gin.Context) {
	var req *dto.URLRequest

	if err := c.ShouldBindJSON(&req); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "URL invalid"})
		return
	}

	url, err := h.service.CreateShortUrl(req.URL)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	response := mapper.DomainURLToResponseCode(url)

	c.JSON(http.StatusCreated, response)
}

func (h *UrlHandler) UpdateShortUrl(c *gin.Context) {
	code := c.Param("code")
	var req dto.URLRequest

	if err := c.ShouldBindJSON(&req); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "The URL is not valid"})
		return
	}

	urlData := &domain.URL{
		URL: req.URL,
	}

	updatedURL, err := h.service.UpdateShortUrl(code, urlData)
	if err != nil {
		c.JSON(http.StatusNotFound, gin.H{"error": err.Error()})
		return
	}

	response := mapper.DomainURLToResponseUpdate(updatedURL)

	c.JSON(http.StatusOK, response)
}

func (h *UrlHandler) GetShortUrlByCode(c *gin.Context) {
	code := c.Param("code")

	url, err := h.service.RetrieveByShortCode(code)

	if err != nil {
		c.JSON(http.StatusNotFound, gin.H{"error": err.Error()})
		return
	}

	response := mapper.DomainURLToResponseCode(url)

	c.JSON(http.StatusOK, response)
}

func (h *UrlHandler) GetShortUrlStats(c *gin.Context) {
	code := c.Param("code")

	url, err := h.service.RetrieveByShortCode(code)

	if err != nil {
		c.JSON(http.StatusNotFound, gin.H{"error": err.Error()})
		return
	}

	response := mapper.DomainURLToResponseStats(url)

	c.JSON(http.StatusOK, response)
}

func (h *UrlHandler) DeleteShortUrl(c *gin.Context) {
	code := c.Param("code")

	if err := h.service.DeleteShortUrl(code); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusOK, gin.H{"message": "URL deleted "})
}
