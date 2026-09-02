package io.github.mrandrey24.service;

import io.github.mrandrey24.domian.Url;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.security.SecureRandom;

import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.delete;
import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.find;

@ApplicationScoped
public class UrlService {

    private static final String CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_CODE_LENGTH = 6;
    private static final SecureRandom RANDOM = new SecureRandom();

    @Transactional
    public Url createUrl(String url) {
        String shortCode;

        do {
            shortCode = generateShortCode();
        } while (find("shortCode", shortCode).firstResultOptional().isPresent());


        Url entity = new Url();
        entity.url = url;
        entity.shortCode = shortCode;
        entity.persist();

        return entity;

    }


    @Transactional
    public Url updateUrl(String code, String newUrl) {
        Url entity = findByCode(code);
        entity.url = newUrl;
        entity.persist();
        return entity;
    }

    @Transactional
    public Url findByCode(String code) {
        Url entity = find("shortCode", code).firstResult();

        if (entity == null) {
            throw new NotFoundException("Url not found");
        }

        entity.incrementAccessCount();
        return entity;
    }

    @Transactional
    public void deleteByCode(String code) {
        delete("shortCode", code);
    }




    private String generateShortCode() {
        StringBuilder sb = new StringBuilder(SHORT_CODE_LENGTH);
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            int index = RANDOM.nextInt(CHARSET.length());
            sb.append(CHARSET.charAt(index));
        }
        return sb.toString();
    }
}
