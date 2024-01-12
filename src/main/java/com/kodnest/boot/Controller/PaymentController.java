package com.kodnest.boot.Controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kodnest.boot.Entity.User;
import com.kodnest.boot.Service.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
	@Autowired
	UserService service;
	
	@GetMapping("/pay")
	public String pay()
	{
		return "pay";
	}

	@SuppressWarnings("finally")
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder(HttpSession session)
	{
		int amount=5000;
		Order order=null;
		try
		{
			RazorpayClient razorpay=new RazorpayClient("rzp_test_8KUbTHsvy5MlXr", "4RrE8hRsjD2QtwiEG6qSazE3");
			
			JSONObject orderRequest=new JSONObject();
			orderRequest.put("amount", amount*100);
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "order_rcptid_11");
			
			order=razorpay.orders.create(orderRequest);
			
			String mail=(String)session.getAttribute("email");
			
			User user=service.getUser(mail);
			user.setPremium(true);
			service.updateUser(user);

		} catch (RazorpayException e) {
			e.printStackTrace();
		}
		finally {
			return order.toString();
		}
		
	}
}
