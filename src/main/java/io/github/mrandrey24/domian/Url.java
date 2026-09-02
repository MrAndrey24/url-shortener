package io.github.mrandrey24.domian;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "urls")
public class Url extends PanacheEntity {
    @Column(nullable = false)
    public String url;

    @Column(nullable = false, unique = true, name = "short_code")
    public String shortCode;

    @CreationTimestamp
    public Instant createdAt;

    @UpdateTimestamp
    public Instant updatedAt;

    @Column(nullable = false)
    public Integer accessCount = 0;

    public Url () {}

    public Url(String url, String shortCode, Instant createdAt, Instant updatedAt, Integer accessCount) {
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
