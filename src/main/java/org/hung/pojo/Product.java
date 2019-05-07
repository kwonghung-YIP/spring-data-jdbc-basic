package org.hung.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("my_product_table")
public class Product {

	private @Id long id;
	
	@Column("short_desc")
	private String description;
	
	private Double unitPrice;
	
}
