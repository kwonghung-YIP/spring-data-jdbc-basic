package org.hung.pojo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Table("my_order_table")
@AllArgsConstructor
public class Order {

	private @Id final Long ref;
	
	@Column("customer_id")
	private long customerId;
	
	@Transient
	private Customer customer;
	
	@Column("place_date")
	private LocalDateTime placeDate;
	
	@Transient
	private Double total;
	
	private String status;
	
	private Address deliveryAddress;
	
	@CreatedBy
	@Column("created_by")
	private String createdBy;
	
	@CreatedDate
	@Column("created_ts")
	private Date createdTime;
	
	@LastModifiedBy
	@Column("last_upd_by")
	private String lastModBy;
	
	@LastModifiedDate
	@Column("last_upd_ts")
	private Date lastModTime;
	
	public Order() {
		this.ref = null;
	}
	
	public Order withRef(Long ref) {
		Order other = new Order(ref,customerId,customer,placeDate,total,status,deliveryAddress,createdBy,createdTime,lastModBy,lastModTime,items);
		return other;
	}
	
	public void setCustomer(Customer customer) {
		this.customerId = (customer==null)?null:customer.getId();
		this.customer = customer;
	}
	
	@Column(value="order_ref",keyColumn="item_no")
	private List<OrderItem> items = new ArrayList<>();
	
	public OrderItem addItem(Product product, int quantity) {
		OrderItem item = new OrderItem();
		item.setProductId(product.getId());
		item.setUnitPrice(product.getUnitPrice());
		item.setQuantity(quantity);
		
		items.add(item);
		
		return item;
	}
}
