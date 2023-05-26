package com.abelkelly.RequestSchema;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@EqualsAndHashCode
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private String uuid = UUID.randomUUID().toString();

    @Column
    private String token;

    @Column
    private ZonedDateTime expiration;

}
