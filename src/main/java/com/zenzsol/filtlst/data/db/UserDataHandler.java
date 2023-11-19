package com.zenzsol.filtlst.data.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenzsol.filtlst.data.entity.User;

public interface UserDataHandler extends JpaRepository<User, String> {
}
