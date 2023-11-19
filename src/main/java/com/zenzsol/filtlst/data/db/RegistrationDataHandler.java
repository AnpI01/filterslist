package com.zenzsol.filtlst.data.db;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenzsol.filtlst.data.entity.Registration;

public interface RegistrationDataHandler extends JpaRepository<Registration, String> {
	ArrayList<Registration> findByDate(Date date);
}
