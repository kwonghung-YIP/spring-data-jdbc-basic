# Order-to-Customer (Embedded Object) relationship

A customer can place many orders, therefore the relationship between customer and order should be one-to-many, and customer class should be aggregate root instead of order class. But thinking of you are working on a sale report, you focus on the order record and customer only a part of the order infomation, therefore, customer becomes an embedded object with the order class.

### Order and Customer entities
 
The customerId property is mapped to customer_id column, which is a foreign key to customer record. The customer property is an object reference that marked with @Transient annotation, which means this field will be ignored by the repository. The setCustomer function assign value to both properties when given a customer object.

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

```Java
@Table("my_customer_table")
public class Customer {

   private @Id Long Id;
   ...
```

### Order and Customer tables

The customer_id column in my_order_table is a foreign key to my_customer_table.

```sql
create table my_order_table (
   ...
   customer_id int not null,
   ...
   foreign key fk_order_to_customer(customer_id) references my_customer_table(id)
   ...
```

```sql
create table my_customer_table (
   id int not null primary key auto_increment,
   ...
```

### Load Customer object into Order with EventListener

Now Order and Customer are 2 aggregate roots and they have its own repository, for convenitence, we would like to inject the customer object into order class whenever it load from the OrderRepository. The EventListener beload bind the AfterLoadEvent of Order entity and inject the customer object into it.

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


