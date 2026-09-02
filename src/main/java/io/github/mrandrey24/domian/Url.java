package io.github.mrandrey24.domian;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
public class Url extends PanacheEntity {
    @Column(nullable = false)
    public String url;

    @Column(nullable = false, unique = true, name = "short_code")
    public String shortCode;

    @CreationTimestamp
    public Date createdAt;

    @UpdateTimestamp
    public Date updatedAt;

    @Column(nullable = false)
    public Integer accessCount = 0;

    public Url () {}

    public Url(String url, String shortCode, Date createdAt, Date updatedAt, Integer accessCount) {
        this.url = url;
        this.shortCode = shortCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.accessCount = accessCount;
    }

    public void incrementAccessCount() {
        this.accessCount += 1;
    }
}
