package org.hung.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table("my_order_address_table")
@Data
public class Address {

	@Column("addr_id")
	private @Id Long id;
	
	@Column("addr_line1")
	private String line1;
	
	@Column("addr_line2")
	private String line2;
	
	@Column("addr_line3")
	private String line3;
	
	@Column("addr_line4")
	private String line4;
	
	private String cityCode;
	
	private String countryCode;
	
}
