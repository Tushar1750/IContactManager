package com.smart.controller;

import java.security.Principal;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.helper.Message;
import com.smart.model.User;
import com.smart.repository.UserRepository;
import com.smart.service.EmailService;

@Controller
public class ForgotController {

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// email id form open handler
	@RequestMapping("/forgot")
	public String openEmailForm() {
		return "forgot_email_form";
	}

	// email id form open handler
	@PostMapping("/send-otp")
//	@ResponseBody
	public String sendOTP(@RequestParam("email") String email, HttpSession session) {
		System.out.println("Email : " + email);

		// generating otp of 4 digit
		Random rnd = new Random();
//		 1000
		int otp = rnd.nextInt(9999);
		System.out.println("OTP " + otp);
//	    String otp = String.format("%04d", number);	    

		System.out.println("otp is : " + otp);
		boolean result = this.emailService.sendEmail(email, "OTP from SCM", "Your otp is " + otp);
		if (result) {
			session.setAttribute("myotp", otp);
			session.setAttribute("email", email);
			session.setAttribute("message", new Message("OTP have sent to your Email ID", "success"));
			// session.setAttribute("message", new Message("Your contact is added !! Add
			// more..","success") );
			System.out.println("after mail sent");
			return "verify_otp";
		} else {
			// session.setAttribute("message", "check your email id!!");
			session.setAttribute("message", new Message("check your email id", "danger"));
			return "forgot_email_form";
		}
	}

	// verify otp
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam("otp") Integer otp, HttpSession session) {
		Integer myotp = (int) session.getAttribute("myotp");
		String email = (String) session.getAttribute("email");
		System.out.println("your otp is " + otp);
		System.out.println("Myotp is " + myotp);

		if (otp.equals(myotp)) {
			System.out.println("in if");
			// password change form
			return "password_change_form";
		} else {
			System.out.println("in else-if");
//			session.setAttribute("message", "You have entered wrong otp !!");
			session.setAttribute("message", new Message("Enter a valid OTP", "danger"));
			return "verify_otp";
		}
	}

	// change password..handler
	@PostMapping("/new-password")
	public String changePassword(@RequestParam("newpassword") String newPassword, HttpSession session) {
//			System.out.println("Old pass : "+oldPassword);
		System.out.println("in new password handler");
		System.out.println("New pass : " + newPassword);

//			String userName = principal.getName();
		String userName = (String) session.getAttribute("email");
		User currentUser = this.userRepository.getUserByUserName(userName);
		System.out.println(currentUser.getPassword());

		// change the password
		currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
		this.userRepository.save(currentUser);
		session.setAttribute("message", new Message("Your password is successfully changed", "success"));

		return "redirect:/user/index";
	}

}
