## Introduction

## The Order-to-Address (one-to-one) relationship

#### Order entity

[Spring Data JDBC reference](https://docs.spring.io/spring-data/jdbc/docs/1.0.8.RELEASE/reference/html/#jdbc.entity-persistence.types), 
To establish the one-to-one relationship from order to address, the Order class has a deliveryAddress property which refer to an Address object, and ref property is identity for Order class.

```Java
@Table("my_order_table")
public class Order {
   private @Id final Long ref;
   ...
   private Address deliveryAddress;
   ...  
```

In the my_order_table, we defined the ref column as the primary key for my_order_table table.
```sql
create table my_order_table (
   ref int not null primary key auto_increment,
   ...
```

#### Address entity

```Java
@Table("my_order_address_table")
public class Address {
   @Column("addr_id")
   private @Id Long Id;
   ...
```
```sql
create table my_order_table_address_table (
   addr_id int not null primary key auto_increment,
   order_ref int not null,
   ...
   foregin key fk_address_to_order(order_ref) reference my_order_table(ref)
   ...
```

#### Customer the name of mapping field

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

## The Order-to-OrderItem (one-to-many) relationship

```Java
@Data
public class Order {
...
  private Address deliveryAddress;
```

## The Order-to-Customer (Embedded Object) relationship
