package com.abelkelly.Token;

import com.abelkelly.Models.AppUser;
import com.abelkelly.Roles.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tokens")
public class Token {

    @jakarta.persistence.Id
    @GeneratedValue
    private Integer Id;

    private String key;

//    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;


    private boolean expired;

    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "userId")
    private AppUser users;
}
