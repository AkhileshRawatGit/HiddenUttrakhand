package com.akhilesh.project.HiddenUkWeb.repository;

import com.akhilesh.project.HiddenUkWeb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
