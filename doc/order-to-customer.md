# Order-to-Customer (Embedded Object) relationship

### Order entity
 
To establish the one-to-one relationship from order to address, the deliveryAddress property is given to Order class which is a reference to an Address object.

```Java
@Table("my_order_table")
public class Order {
   ...
   @Column("customer_id")
   private long customerId;
   
   @Transient
   private Customer customer;
   ...
   public void setCustomer(Customer customer) {
      this.customerId = (customer==null)?null:customer.getId();
      this.customer = customer;
   }
   ...
```

The Order object is mapped to the *my_order_table* table, with ref column as the primary key.
```sql
create table my_order_table (
   ...
   customer_id int not null,
   ...
   foreign key fk_order_to_customer(customer_id) references my_customer_table(id)
   ...
```

### Customer entity

The Address class contains no information about Order.
```Java
@Table("my_customer_table")
public class Customer {

   private @Id Long Id;
   ...
```

The my_order_address_table table that mapped to Address entity, which has a foreign key order_ref refer back to the my_order_table.
```sql
create table my_customer_table (
   id int not null primary key auto_increment,
   ...
```

### Customize the column name of the foreign key field in address table

By default, the spring data jdbc will map the column name of the FK same as the refer table, in our case, should be the my_order_table. To override this, we defined a namingStrategy bean as following:

```java
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
```


### Select SQL

### Insert SQL

### Update SQL

### Delete SQL

