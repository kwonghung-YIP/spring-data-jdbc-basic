## Introduction

## The Order-to-DeliveryAddress (one-to-one) relationship

```Java
@Data
@Table("my_order_table")
public class Order {
...
  private Address deliveryAddress;
...  

@Data
@Table("my_order_address_table")
public class Address {

```


```sql
create table my_order_table (

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
