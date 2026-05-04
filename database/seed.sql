USE inventory_db;

SET @user1_id    = UUID();
SET @product1_id = UUID();
SET @product2_id = UUID();
SET @product3_id = UUID();
SET @product4_id = UUID();

INSERT INTO users (id, first_name, last_name, email, phone_number, username, password) VALUES
(@user1_id, 'admin',  'lastname', 'test@email.com',  '111-111-1111', 'admin',  'password');


INSERT INTO products (id, user_id, name, description, sku, price, quantity) VALUES
(@product1_id, @user1_id, 'Apple',  'Crisp and sweet Fuji apples, sold per unit.', 'FRUIT-001', 1.50, 100),
(@product2_id, @user1_id, 'Banana', 'Ripe yellow bananas, sold per unit.',          'FRUIT-002', 0.75, 150),
(@product3_id, @user1_id, 'Orange', 'Juicy navel oranges, sold per unit.',          'FRUIT-003', 2.00,  80);
