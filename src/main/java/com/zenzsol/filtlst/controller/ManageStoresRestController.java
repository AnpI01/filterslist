package com.zenzsol.filtlst.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zenzsol.filtlst.data.db.MessageDataHandler;
import com.zenzsol.filtlst.data.db.RegistrationDataHandler;
import com.zenzsol.filtlst.data.db.StoreDataHandler;
import com.zenzsol.filtlst.data.db.UserDataHandler;
import com.zenzsol.filtlst.data.entity.Messages;
import com.zenzsol.filtlst.data.entity.Registration;
import com.zenzsol.filtlst.data.entity.Store;

@RestController
public class ManageStoresRestController {
	
	private static final Logger log = Logger.getLogger(RegistrationController.class.getName());

	@Autowired
	private RegistrationDataHandler regDataHandler;
	@Autowired
	private MessageDataHandler messageDataHandler;
	@Autowired
	private StoreDataHandler storeDataHandler;
	@Autowired
	private UserDataHandler userDataHandler;
	
	 @PutMapping("/save/store")
	  public String addStore(@RequestBody Store store) {
		 
		 try {
			 storeDataHandler.save(store);
		} catch (Exception e) {
			log.info("addStore exception: " + e);
			return "0";
		}
	    return "1";
	  }
	 
	 @GetMapping("/get/storeRegReq")
	  public List<Registration> getStoreRegistrationRequests(@RequestParam("date") String date, 
			  @RequestParam("uid") String uid, @RequestParam("pwd") String pwd) {
		 
		 List<Registration> regReq;
		 
		 try {
			
			 if(!userDataHandler.findById(uid).get().getPwd().equals(pwd)) {
				 throw new Exception("Invalid credentials");
			 }
			 
			 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			 java.sql.Date dbdate = new java.sql.Date(df.parse(date).getTime());
			 
			// regReq = regDataHandler.findAll();
			 regReq = regDataHandler.findByDate(dbdate);
	
		} catch (Exception e) {
			log.info("getStoreRegistrationRequests exception: " + e);
			regReq =  new ArrayList<Registration>();
		}
		return regReq;
	  }
	 
	 @GetMapping("/get/messages")
	  public List<Messages> getMessages(@RequestParam("date") String date) {
		 List<Messages> msgs;
		 
		 try {
			 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			 java.sql.Date dbdate = new java.sql.Date(df.parse(date).getTime());
			 
			 msgs = messageDataHandler.findByDate(dbdate);
		} catch (Exception e) {
			log.info("getMessages exception: " + e);
			msgs =  new ArrayList<Messages>();
		}
		return msgs;
	  }
}
