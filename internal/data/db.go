package data

import (
	"log"

	"github.com/mrandrey24/url-shortening-server/internal/domain"
	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
)

func InitDB(path string) *gorm.DB {
	db, err := gorm.Open(sqlite.Open(path), &gorm.Config{})
	if err != nil {
		log.Fatal("failed to connect database: ", err)
	}

	if err := db.AutoMigrate(&domain.URL{}); err != nil {
		log.Fatal("failed to migrate database: ", err)
	}

	log.Println("Database connection established")
	return db
}
