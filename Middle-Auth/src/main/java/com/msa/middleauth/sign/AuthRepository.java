package com.msa.middleauth.sign;

import com.msa.middleauth.sign.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
    @Query("select u from User u where u.user_id = :user_id")
    User findByUser_id(String user_id);
}
