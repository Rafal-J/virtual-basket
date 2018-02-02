create table baskets (
  basket_id INT(11) NOT NULL AUTO_INCREMENT,
  open boolean, user_id INT(11),
  primary key (basket_id)
);

create table items (
  item_id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255), price DECIMAL(19,2),
  discount_at_qty INT(11),
  discounted_price DECIMAL(19,2),
  primary key(item_id)
);

create table basket_items (
  basket_item_id INT(11) NOT NULL AUTO_INCREMENT,
  qty INT(11),
  basket_id INT(11),
  item_id INT(11),
  primary key(basket_item_id),
  FOREIGN KEY (basket_id) REFERENCES baskets(basket_id),
  FOREIGN KEY (item_id) REFERENCES items(item_id)
);