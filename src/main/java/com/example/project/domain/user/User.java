package com.example.project.domain.user;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user")
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Setter
    private String password;
    private String email;

    @Setter
    private String role;

    private String provider;   //공급자(google, naver ...)
    private String providerId;

    @Builder
    public User(String name, String password, String email, String role, String provider, String providerId){
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }


}