package com.abelkelly.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query("""
            select t from Token t inner join users u on t.users.id = u.id
            where u.id = :userId and (t.expired = false or t.revoked=false)
            """)
    List<Token> findAllValidTokensByUsers(Integer userId);

    Optional<Token> findByKey(String key);

//    Optional<Token> findByRefreshToken(String key);
}
