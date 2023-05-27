package com.codeonmars.usersms.repository;

import com.codeonmars.usersms.model.user.UserEntity;
import com.codeonmars.usersms.model.user.projection.SimpleUserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String credential);

    Optional<UserEntity> findByEmail(String email);

    @Query(value = """
            SELECT u.email, u.username FROM UserEntity u where
            u.email = :email""")
    Optional<SimpleUserProjection> getSimpleDataByEmail(@Param("email") String email);

    Optional<UserEntity> findByUsernameAndEmail(String username, String email);
}
