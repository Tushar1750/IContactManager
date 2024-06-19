package com.smart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class MyOrder {

	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	private Long myOrderId;
	
	private String orderId;
	
	private String amount;
	
	private String receipt;
	
	private String status;
	
	@ManyToOne
	private User user;
	
	private String paymentId;
}
