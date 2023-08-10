package com.example.project.domain;

public class UserBuilder {
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
