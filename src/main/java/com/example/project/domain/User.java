package com.example.project.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Entity
@Table(
        name = "user"
)
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "user_id"
    )
    private Long id;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "email",
            nullable = false
    )
    private String email;
    @Column(
            name = "provider",
            nullable = false
    )
    private String provider;
    @Column(
            name = "providerId",
            nullable = true,
            unique = true
    )
    private String providerId;

    public User(String name, String email, String provider, String providerId) {
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
    }

    public User update(String name, String email) {
        this.name = name;
        this.email = email;
        return this;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    protected User() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getProvider() {
        return this.provider;
    }

    public String getProviderId() {
        return this.providerId;
    }

    public static class UserBuilder {
        private String name;
        private String email;
        private String provider;
        private String providerId;

        UserBuilder() {
        }

        public UserBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public UserBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public UserBuilder provider(final String provider) {
            this.provider = provider;
            return this;
        }

        public UserBuilder providerId(final String providerId) {
            this.providerId = providerId;
            return this;
        }

        public User build() {
            return new User(this.name, this.email, this.provider, this.providerId);
        }

        public String toString() {
            return "User.UserBuilder(name=" + this.name + ", email=" + this.email + ", provider=" + this.provider + ", providerId=" + this.providerId + ")";
        }
    }
}
