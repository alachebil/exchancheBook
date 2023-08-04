package com.workshop.bouali.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserP,Long> {

    @Query("SELECT u from UserP u WHERE u.email=?1")
    Optional<UserP> findUserByEmail(String email);
}

