package repository

import (
	"errors"

	"github.com/mrandrey24/url-shortening-server/internal/domain"
	"gorm.io/gorm"
)

// Dependency injection
type repository struct {
	db *gorm.DB
}

// NewURLRepository Constructor for memoryRepository
func NewURLRepository(db *gorm.DB) domain.URLRepository {
	return &repository{
		db: db,
	}
}

func (r *repository) Save(req *domain.URL) error {
	return r.db.Create(req).Error
}

func (r *repository) Update(code string, req *domain.URL) (*domain.URL, error) {
	u := r.db.Model(&domain.URL{}).Where("short_code = ?", code).Update("URL", req.URL)
	if u.Error != nil {
		return nil, u.Error
	}
	return req, nil
}

func (r *repository) Delete(shortCode string) error {
	var u domain.URL
	return r.db.Where("short_code = ?", shortCode).Delete(&u).Error
}

func (r *repository) FindByShortCode(shortCode string) (*domain.URL, error) {
	var u domain.URL
	err := r.db.Model(&domain.URL{}).Where("short_code = ?", shortCode).UpdateColumn("access_count", gorm.Expr("access_count + 1")).First(&u).Error
	if errors.Is(err, gorm.ErrRecordNotFound) {
		return nil, errors.New("ShortCode not found")
	}
	return &u, err
}
