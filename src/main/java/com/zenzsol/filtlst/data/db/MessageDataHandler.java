package com.zenzsol.filtlst.data.db;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenzsol.filtlst.data.entity.Messages;

public interface MessageDataHandler extends JpaRepository<Messages, Long> {
	ArrayList<Messages> findByDate(Date date);
}
