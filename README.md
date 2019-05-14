## Introduction

## The Order-to-DeliveryAddress (one-to-one) relationship

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

```sql
create table my_order_table (
   ref int not null primary key auto_increment,
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

### Customer the name of mapping field

## The Order-to-OrderItem (one-to-many) relationship

```Java
@Data
public class Order {
...
  private Address deliveryAddress;
```

## The Order-to-Customer (Embedded Object) relationship
