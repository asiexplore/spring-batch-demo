package com.example.springbatchdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbatchdemo.entity.UserEntity;

public interface UsersRepository extends JpaRepository<UserEntity, Long> {
}