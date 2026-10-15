package domain

import (
	"time"

	"gorm.io/gorm"
)

type URL struct {
	gorm.Model
	ID          uint   `gorm:"primarykey"`
	URL         string `gorm:"not null"`
	ShortCode   string `gorm:"unique;not null"`
	AccessCount int64  `gorm:"default:0"`
	CreatedAt   time.Time
	UpdatedAt   time.Time
}

type URLRepository interface {
	Save(u *URL) error
	Update(shortCode string, u *URL) (*URL, error)
	Delete(shortCode string) error
	FindByShortCode(shortCode string) (*URL, error)
}
