### Order

```Java
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
```

```sql
create table my_customer_table (
	id int not null primary key auto_increment,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	email varchar(100) not null,
	date_of_birth date
);
```
