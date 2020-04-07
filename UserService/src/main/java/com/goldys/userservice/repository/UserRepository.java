package com.goldys.userservice.repository;

import com.goldys.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public User findByEmailAndPassword(String email, String password);

    public User findByEmailAndRole(Object any, Object any1);
}
