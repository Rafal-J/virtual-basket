create table baskets (basket_id INT(11) NOT NULL AUTO_INCREMENT, open boolean, user_id INT(11), primary key (basket_id));
create table items (item_id INT(11) NOT NULL AUTO_INCREMENT, name VARCHAR(255), price DECIMAL(19,2), discount_at_qty INT(11), discounted_price DECIMAL(19,2), primary key(item_id));
create table basket_items (basket_item_id INT(11) NOT NULL AUTO_INCREMENT, qty INT(11), basket_id INT(11), item_id INT(11),
 primary key(basket_item_id), FOREIGN KEY (basket_id) REFERENCES baskets(basket_id), FOREIGN KEY (item_id) REFERENCES items(item_id));

insert into baskets values(1, true, 24);
insert into baskets values(2, true, 25);

insert into items values(1, "farba", 12, 14,  10);
insert into items values(2, "pędzel", 9.50, 8, 7.99);
insert into items values(3, "szpachla", 10, 5,  7);

insert into basket_items values(1,33,1,1);
insert into basket_items values(2,22,1,2);
insert into basket_items values(3,1,1,3);

insert into basket_items values(4,4,2,2);