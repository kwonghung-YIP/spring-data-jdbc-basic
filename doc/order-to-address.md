# The Order-to-Address (one-to-one) relationship

An order must have a delivery address for sending the products to customer, in this example, the order-to-address is a one-to-one relationship.

### Order and Address entities

To model this one-to-one relationship, the Order class has a deliveryAddress property which is a reference to an Address Object, on the other hand, the Address class has no information about the order. The ref property in Order class and the id property in Address class are marked with @Id annotnation that means they are the identity property, and the @Table annotnation map both classes to table my_order_table and my_order_address_table respectively.

```Java
@Table("my_order_table")
public class Order {
   private @Id final Long ref;
   ...
   private Address deliveryAddress;
   ...  
```

```Java
@Table("my_order_address_table")
public class Address {
   @Column("addr_id")
   private @Id Long Id;
   ...
```

### Database table design

In opposite of the entity model, the order table contains no information about address, but the address table has the order_ref column which is a foreign key refer to order record as its parent.

```sql
create table my_order_table (
   ref int not null primary key auto_increment,
   ...
```

```sql
create table my_order_address_table (
   addr_id int not null primary key auto_increment,
   order_ref int not null,
   ...
   foregin key fk_address_to_order(order_ref) reference my_order_table(ref)
   ...
```

### Tell the repository the column of the foreign key column

To let the repository knows that order_ref column is a foriegn key, we have to define a namingStrategy bean, and override the getReverseColumnName as following. By default, the repository will map the column name of the foreign key as same as the referring table, such as my_order_table, in our case.

```java
@Configuration
public class MyJdbcRepoConfig {
   ...
   @Bean
   public NamingStrategy namingStrategy() {
      return new NamingStrategy() {
         @Override
         public String getReverseColumnName(RelationalPersistentProperty property) {
            if (Address.class.equals(property.getRawType() && "deliveryAddress".equals(property.getName())) {
               return "order_ref";
            ...
      }
```


### Select SQL

### Insert SQL

### Update SQL

### Delete SQL

