package com.trimble.carstarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trimble.carstarter.model.User;

public interface UserRepository extends JpaRepository<User, Long> {}
