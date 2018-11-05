create database sell;
use  sell;
drop table product_info;
create table product_info (
	product_id varchar(32) not null ,
    product_name varchar(64) not null comment 'Product Name',
    product_price decimal(8,2) not null comment 'Price',
    product_stock int not null comment 'Stock',
    product_description varchar(64) comment 'Description',
    product_icon varchar(512) comment 'Icon',
    product_status tinyint(3) not null default 0 comment 'Status 1 means out of stock',
    category_type int not null comment 'Category',
    create_time timestamp  default current_timestamp comment 'Create Time',
    update_time timestamp  default current_timestamp on update current_timestamp comment 'Update Time',
    primary key(product_id)
)comment 'Product Table';


INSERT INTO product_info(product_id,product_name,product_price,product_stock,product_description,product_icon,product_status,category_type) VALUES ('A0001','SHIRMP',20,100,'TRY IT!','https://static01.nyt.com/images/2016/05/28/dining/28COOKING-CLASSIC-SHRIMP-SCAMPI1/28COOKING-CLASSIC-SHRIMP-SCAMPI1-videoSixteenByNineJumbo1600.jpg',0,1);
INSERT INTO product_info(product_id,product_name,product_price,product_stock,product_description,product_icon,product_status,category_type) VALUES ('A0002','PORK',10,100,'TRY IT!','https://thestayathomechef.com/wp-content/uploads/2017/11/Easy-Baked-Pork-Chops-4-small.jpg',0,1);
INSERT INTO product_info(product_id,product_name,product_price,product_stock,product_description,product_icon,product_status,category_type) VALUES ('A0003','BEEF',30,100,'TRY IT!','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538509217057&di=b52fec48daf8a0f394aecc5ed9e62605&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fblog%2F201411%2F01%2F20141101145207_cxdYm.jpeg',0,1);
INSERT INTO product_info(product_id,product_name,product_price,product_stock,product_description,product_icon,product_status,category_type) VALUES ('A0004','FISH',25,100,'TRY IT!','https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Tilapia_zillii_Kineret.jpg/290px-Tilapia_zillii_Kineret.jpg',0,1);

INSERT INTO product_info(product_id,product_name,product_price,product_stock,product_description,product_icon,product_status,category_type) VALUES ('B0001','BROCCOLI',5,100,'TRY IT!','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538509267014&di=7c88cbd6049e504cc68c8ca699582229&imgtype=0&src=http%3A%2F%2Fwww.cqtimes.cn%2FUploads%2FPicture%2F2017-02-19%2F5d761404-e7d5-4afa-a545-3b27698648a0.jpg',0,2);
INSERT INTO product_info(product_id,product_name,product_price,product_stock,product_description,product_icon,product_status,category_type) VALUES ('B0002','CUCUMBER',3,100,'TRY IT!','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538509586630&di=10d6d0b88b323dc4b6fd3987d07a3d62&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20170627%2F7874648c6a704791ba23d36de3ce0517_th.jpg',0,2);
INSERT INTO product_info(product_id,product_name,product_price,product_stock,product_description,product_icon,product_status,category_type) VALUES ('B0003','CARROT',3,100,'TRY IT!','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2906974492,1423554313&fm=200&gp=0.jpg',0,2);
INSERT INTO product_info(product_id,product_name,product_price,product_stock,product_description,product_icon,product_status,category_type) VALUES ('B0004','LETTUCE',4,100,'TRY IT!','https://images-na.ssl-images-amazon.com/images/I/41CGtIyWgML._SX355_.jpg',0,2);

INSERT INTO product_info(product_id,product_name,product_price,product_stock,product_description,product_icon,product_status,category_type) VALUES ('C0001','CHIPS',15,100,'TRY IT!','https://mikespizza.gofoodpng.biz/wp-content/uploads/2017/02/Chips1.jpg',0,3);
INSERT INTO product_info(product_id,product_name,product_price,product_stock,product_description,product_icon,product_status,category_type) VALUES ('C0002','COKE',10,100,'TRY IT!','https://goodhousekeeping.fetcha.co.za/wp-content/uploads/2016/09/coke.jpg',0,3);
INSERT INTO product_info(product_id,product_name,product_price,product_stock,product_description,product_icon,product_status,category_type) VALUES ('C0003','ICE CREAM',18,100,'TRY IT!','https://pics.drugstore.com/prodimg/419677/900.jpg',0,3);
INSERT INTO product_info(product_id,product_name,product_price,product_stock,product_description,product_icon,product_status,category_type) VALUES ('C0004','BEER',20,100,'TRY IT!','https://i.ytimg.com/vi/vP50g1KGoTo/maxresdefault.jpg',0,3);


drop table product_category;
create table product_category (
	category_id int auto_increment,
    category_name varchar(64) not null comment 'Category Name',
    category_type int not null comment 'Category Type',
    create_time timestamp  default current_timestamp comment 'Create Time',
    update_time timestamp  default current_timestamp on update current_timestamp comment 'Update Time',
    primary key(category_id),
    unique key uqe_category_type (category_type)
)comment 'Category Table';


INSERT INTO product_category(category_id,category_name,category_type) VALUES (0001,'MEAT',1);
INSERT INTO product_category(category_id,category_name,category_type) VALUES (0002,'VEGETABLES',2);
INSERT INTO product_category(category_id,category_name,category_type) VALUES (0003,'SNACK',3);



drop table order_master;
create table order_master (
	order_id varchar(32) not null,
    buyer_name varchar(32) not null comment 'Buyer Name',
    buyer_phone varchar(32) not null comment 'Buyer Phone',
    buyer_address varchar(128) not null comment 'Buyer Address',
    buyer_openid varchar(64) not null comment 'Buyer ID',
    order_amount decimal(8,2) not null comment 'Order Total Amount',
    order_status tinyint(3) not null default 0 comment 'Order Status, default 0 means new order',
    pay_status tinyint(3) not null default 0 comment 'Pay Status, default 0 means new order',
    create_time timestamp  default current_timestamp comment 'Create Time',
    update_time timestamp  default current_timestamp on update current_timestamp comment 'Update Time',
    primary key (order_id),
    key idx_buyer_openid (buyer_openid)
)comment 'Order Master Table';

INSERT INTO order_master(order_id,buyer_name,buyer_phone,buyer_address,buyer_openid,order_amount,order_status,pay_status) VALUES ('000001','TOM','123-456-789','200 UNIVERITY WEST','TOM000001',100,0,0);
INSERT INTO order_master(order_id,buyer_name,buyer_phone,buyer_address,buyer_openid,order_amount,order_status,pay_status) VALUES ('000002','GREY','123-456-123','201 UNIVERITY WEST','GREY000001',80,0,0);





drop table order_detail;
create table order_detail(
	detail_id varchar(32) not null,
    order_id varchar(32) not null,
    product_id varchar(32) not null,
    product_name varchar(64) not null comment 'Product Name',
    product_price decimal(8,2) not null comment 'Product Price',
    product_quantity int not null comment 'Product Quantity',
    product_icon varchar(512) comment 'Product Icon',
    create_time timestamp  default current_timestamp comment 'Create Time',
    update_time timestamp  default current_timestamp on update current_timestamp comment 'Update Time',
    primary key (detail_id),
    key idx_order_id (order_id)
) comment 'Order Detail Table';

INSERT INTO order_detail(detail_id,order_id,product_id,product_name,product_price,product_quantity,product_icon) VALUES('000001','000001','A0001','SHIRMP',20,3,'https://static01.nyt.com/images/2016/05/28/dining/28COOKING-CLASSIC-SHRIMP-SCAMPI1/28COOKING-CLASSIC-SHRIMP-SCAMPI1-videoSixteenByNineJumbo1600.jpg');
INSERT INTO order_detail(detail_id,order_id,product_id,product_name,product_price,product_quantity,product_icon) VALUES('000002','000001','B0001','BROCCOLI',5,4,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538509267014&di=7c88cbd6049e504cc68c8ca699582229&imgtype=0&src=http%3A%2F%2Fwww.cqtimes.cn%2FUploads%2FPicture%2F2017-02-19%2F5d761404-e7d5-4afa-a545-3b27698648a0.jpg');
INSERT INTO order_detail(detail_id,order_id,product_id,product_name,product_price,product_quantity,product_icon) VALUES('000003','000001','C0004','BEER',20,1,'https://i.ytimg.com/vi/vP50g1KGoTo/maxresdefault.jpg');

INSERT INTO order_detail(detail_id,order_id,product_id,product_name,product_price,product_quantity,product_icon) VALUES('000004','000002','A0001','SHIRMP',20,2,'https://static01.nyt.com/images/2016/05/28/dining/28COOKING-CLASSIC-SHRIMP-SCAMPI1/28COOKING-CLASSIC-SHRIMP-SCAMPI1-videoSixteenByNineJumbo1600.jpg');
INSERT INTO order_detail(detail_id,order_id,product_id,product_name,product_price,product_quantity,product_icon) VALUES('000005','000002','B0001','BROCCOLI',5,4,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538509267014&di=7c88cbd6049e504cc68c8ca699582229&imgtype=0&src=http%3A%2F%2Fwww.cqtimes.cn%2FUploads%2FPicture%2F2017-02-19%2F5d761404-e7d5-4afa-a545-3b27698648a0.jpg');
INSERT INTO order_detail(detail_id,order_id,product_id,product_name,product_price,product_quantity,product_icon) VALUES('000006','000002','C0004','BEER',20,1,'https://i.ytimg.com/vi/vP50g1KGoTo/maxresdefault.jpg');




#create table seller_info(
#	seller_id varchar(32) not null,
#    username varchar(32) not null,
#    password varchar(32) not null,
#    openid varchar(64) not null comment 'OpenId',
#    create_time timestamp default current_timestamp comment 'Create Time',
#    update_time timestamp default current_timestamp on update current_timestamp comment 'Update Time',
#    primary key (seller_id)
#)comment 'Seller Info';





drop table user_info;
create table user_info(
	id varchar(32) not null,
    active boolean not null comment 'Active',
    address varchar(128) not null comment 'Address',
    email varchar(64) not null comment 'Email',
    name varchar(64) not null comment 'Name',
    password varchar(64) not null comment 'Password',
    phone varchar(64) not null comment 'Phone',
    role varchar(64) not null comment 'Role',
    create_time timestamp default current_timestamp comment 'Create Time',
    update_time timestamp default current_timestamp on update current_timestamp comment 'Update Time',
    primary key(id)
)comment 'User';

INSERT INTO user_info(id,active,address,email,name,password,phone,role) VALUES (2147483641, true, '3200 West Road', 'customer1@email.com', 'customer1', '123', '123456789', 'ROLE_CUSTOMER');
INSERT INTO user_info(id,active,address,email,name,password,phone,role) VALUES (2147483642, true, '2000 John Road', 'manager1@email.com', 'manager1', '123', '987654321', 'ROLE_MANAGER');
INSERT INTO user_info(id,active,address,email,name,password,phone,role) VALUES (2147483643, true, '222 East Drive ', 'employee1@email.com', 'employee1', '123', '123123122', 'ROLE_EMPLOYEE');
INSERT INTO user_info(id,active,address,email,name,password,phone,role) VALUES (2147483645, true, '3100 Western Road A', 'customer2@email.com', 'customer2', '123', '2343456', 'ROLE_CUSTOMER');




