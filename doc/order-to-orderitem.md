# The Order-to-OrderItem (one-to-many) relationship

### Order and OrderItem entities

```Java
@Table("my_order_table")
public class Order {
   ...
   @Column(value="order_ref",keyColumn="item_no")
   private List<OrderItem> items = new ArrayList<OrderItem>();
   
   public OrderItem addItem(Product product, int quantity) {
      OrderItem item = new OrderItem();
      ...
      this.items.add(item);
      ...
```
```Java
@Table("my_order_item_table")
public class OrderItem {
   ...
```

### Order table

The Order object is mapped to the *my_order_table* table, with ref column as the primary key.
```sql
create table my_order_table (
   ref int not null primary key auto_increment,
   ...
```

### Product table
```sql
create table my_product_table (
   id int not null primary key auto_increment,
   ...
```

### OrderItem table
```sql
create table my_order_item_table (
   order_ref int not null,
   item_no int not null,
   product_id int not null,
   ...
   primary key(order_ref, product_id),
   foreign key fk_item_to_order(order_ref) references my_order_table(ref)
   ...
   foreign key fk_item_to_product(product_id) references my_product_table(id)
   ...
```

### Select SQL

### Insert SQL

### Update SQL

### Delete SQL


