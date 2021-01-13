package com.qweed.qweed.interfaces;

import com.qweed.qweed.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

}