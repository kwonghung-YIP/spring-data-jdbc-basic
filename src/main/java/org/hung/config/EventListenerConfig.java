package org.hung.config;

import java.util.Optional;

import org.hung.pojo.Customer;
import org.hung.pojo.Order;
import org.hung.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.AfterLoadEvent;

@Configuration
public class EventListenerConfig {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Bean
	public ApplicationListener<AfterLoadEvent> entityEventListener() {
		
		return event -> {
			Object entity = event.getEntity();
			if (entity instanceof Order) {
				Order order = (Order)entity;
				Optional<Customer> customer = customerRepo.findById(order.getCustomerId());
				order.setCustomer(customer.get());
			}
		};
		
	}	

}
