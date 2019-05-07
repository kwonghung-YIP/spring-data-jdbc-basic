package org.hung.pojo;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("my_order_item_table")
public class OrderItem {

	
	private long productId;
	
	private double unitPrice;
	
	@Column("qty")
	private int quantity;

}
