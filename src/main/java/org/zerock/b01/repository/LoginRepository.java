package org.zerock.b01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerock.b01.domain.User;

public interface LoginRepository extends JpaRepository<User, String> {
}
