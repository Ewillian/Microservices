package com.usercore.userCore.Repositories;

import com.usercore.userCore.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
