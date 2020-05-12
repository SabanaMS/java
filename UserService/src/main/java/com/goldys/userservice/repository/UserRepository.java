package com.goldys.userservice.repository;

import com.goldys.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * This class is implementing the JpaRepository interface for User.
 * Annotate this class with @Repository annotation
 */
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmailAndPassword(String email, String password);

    User findByEmailAndRole(String email, String role);

}
