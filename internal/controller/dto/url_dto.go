package dto

import (
	"time"
)

type URLRequest struct {
	URL string `json:"url" binding:"required,url"`
}

type URLResponseGetByCode struct {
	ID        uint      `json:"id"`
	URL       string    `json:"url"`
	ShortCode string    `json:"shortCode"`
	CreatedAt time.Time `json:"createdAt"`
	UpdatedAt time.Time `json:"updatedAt"`
}

type URLResponseGetStats struct {
	ID          uint      `json:"id"`
	URL         string    `json:"url"`
	ShortCode   string    `json:"shortCode"`
	CreatedAt   time.Time `json:"createdAt"`
	UpdatedAt   time.Time `json:"updatedAt"`
	AccessCount int64     `json:"accessCount"`
}

type URLResponseUpdate struct {
	URL string `json:"url"`
}
