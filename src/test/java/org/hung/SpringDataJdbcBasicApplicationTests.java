package org.hung;

import java.time.LocalDateTime;
import java.util.Optional;

import org.hung.pojo.Address;
import org.hung.pojo.Customer;
import org.hung.pojo.Order;
import org.hung.pojo.Product;
import org.hung.repo.CustomerRepository;
import org.hung.repo.OrderRepository;
import org.hung.repo.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJdbcTest()
public class SpringDataJdbcBasicApplicationTests {

	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void test1() {
		Optional<Customer> customer = customerRepo.findById(1l);
		
		Optional<Product> product1 = productRepo.findById(1l);
		
		Optional<Product> product2 = productRepo.findById(2l);
		
		
		Address address = new Address();
		address.setLine1("line#1");
		address.setLine2("line#2");
		address.setLine3("line#3");
		address.setLine4("line#4");
		
		Order order, order2 = null;
		order = new Order();
		order.setCustomer(customer.get());
		order.setPlaceDate(LocalDateTime.now());
		order.setStatus("N");
		order.setDeliveryAddress(address);
		order.addItem(product1.get(), 10);
		try {
			order2 = orderRepo.save(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Optional<Order> order1 = orderRepo.findById(order2.getRef());
		
		order1.get().getItems().get(0).setQuantity(10);
		order1.get().addItem(product2.get(), 99);
		
		orderRepo.save(order1.get());
	}

}
