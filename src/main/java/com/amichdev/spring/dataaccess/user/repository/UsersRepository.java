package com.amichdev.spring.dataaccess.user.repository;

import com.amichdev.spring.dataaccess.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
