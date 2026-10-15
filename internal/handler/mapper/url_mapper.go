package mapper

import (
	"github.com/mrandrey24/url-shortening-server/internal/domain"
	"github.com/mrandrey24/url-shortening-server/internal/handler/dto"
)

func DomainURLToResponseCode(url *domain.URL) *dto.URLResponseGetByCode {
	return &dto.URLResponseGetByCode{
		ID:        url.ID,
		URL:       url.URL,
		ShortCode: url.ShortCode,
		CreatedAt: url.CreatedAt,
		UpdatedAt: url.UpdatedAt,
	}
}

func DomainURLToResponseStats(url *domain.URL) *dto.URLResponseGetStats {
	return &dto.URLResponseGetStats{

		ID:          url.ID,
		URL:         url.URL,
		ShortCode:   url.ShortCode,
		CreatedAt:   url.CreatedAt,
		UpdatedAt:   url.UpdatedAt,
		AccessCount: url.AccessCount,
	}
}

func DomainURLToResponseUpdate(url *domain.URL) *dto.URLResponseUpdate {
	return &dto.URLResponseUpdate{
		URL: url.URL,
	}
}
