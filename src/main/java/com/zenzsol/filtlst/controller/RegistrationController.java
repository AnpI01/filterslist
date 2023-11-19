package com.zenzsol.filtlst.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zenzsol.filtlst.data.db.MessageDataHandler;
import com.zenzsol.filtlst.data.db.RegistrationDataHandler;
import com.zenzsol.filtlst.data.dto.Message;
import com.zenzsol.filtlst.data.dto.Register;
import com.zenzsol.filtlst.data.entity.Messages;
import com.zenzsol.filtlst.data.entity.Registration;

import jakarta.validation.Valid;

@Controller
public class RegistrationController {

	private static final Logger log = Logger.getLogger(RegistrationController.class.getName());

	@Autowired
	private RegistrationDataHandler dataHandler;
	@Autowired
	private MessageDataHandler messageDataHandler;
	
	@GetMapping("/error")
	public String showError(ModelMap model) {
		return "/zv/common/error";
	}

	@GetMapping("/registerstore")
	public String showRegisterStore(ModelMap model) {
		model.put("register", new Register());
		return "/zv/content/registerstore";
	}

	@PostMapping("/registerstore")
	public String registerStore(@Valid Register register, BindingResult result) {
		log.info("reg store ");
		if (!result.hasErrors()) {
			try {
				dataHandler.save(createRegistration(register));
			} catch (Exception e) {
				result.addError(new ObjectError("message", "Failed to save store info."));
				log.info("registerStore exception: " + e);
			}
			register.setSubmitted(true);

		}
		return "/zv/content/registerstore";
	}

	@GetMapping("/company/aboutus")
	public String showAboutUs(ModelMap model) {

		return "/zv/common/aboutus";
	}

	@GetMapping("/company/privacy")
	public String showPrivacy(ModelMap model) {

		return "/zv/common/privacy";
	}

	@GetMapping("/company/termsofuse")
	public String showTC(ModelMap model) {

		return "/zv/common/termcons";
	}

	@GetMapping("/contact")
	public String showContactUs(ModelMap model) {
		model.put("message", new Message());
		return "/zv/content/contactus";
	}

	@PostMapping("/contact")
	public String contactUs(@Valid Message message, BindingResult result) {

		if (!result.hasErrors()) {
			try {
				messageDataHandler.save(createMessage(message));
			} catch (Exception e) {
				result.addError(new ObjectError("message", "Failed to send the message."));
				log.info("contactUs exception: " + e);
			}
			message.setSubmitted(true);

		}
		return "/zv/content/contactus";
	}

	private Registration createRegistration(Register reg) {
		Date date = Date.valueOf(LocalDate.now());
		Registration registration = new Registration();
		registration.setEmail(reg.getEmail());
		registration.setStorename(reg.getName());
		registration.setUrl(reg.getUrl());
		registration.setDate(date);
		return registration;
	}
	
	private Messages createMessage(Message msg) {
		Date date = Date.valueOf(LocalDate.now());
		Messages message = new Messages();
		message.setEmail(msg.getEmail());
		message.setName(msg.getName());
		message.setMessage(msg.getMessage());
		message.setDate(date);
		return message;
	}
}