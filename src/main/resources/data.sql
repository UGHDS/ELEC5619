/*User Data*/
INSERT INTO user (id, email, first_name, last_name, password, phone, type, status) VALUES (1, 'owner1@gmail.com', 'Own', 'Jones', '12345678', '04123456', 'owner', 'active');
INSERT INTO user (id, email, first_name, last_name, password, phone, type, status) VALUES (2, 'qwer@gamil.com', 'hong', 'hong', '12345678', '043215', 'organiser', 'pending');
INSERT INTO user (id, email, first_name, last_name, password, phone, type, status) VALUES (3, 'hhh@qq.com', 'ha', 'ha', '12345678', '042299', 'organiser', 'pending');
INSERT INTO user (id, email, first_name, last_name, password, phone, type, status) VALUES (4, 'open@qq.com', 'xiao', 'xiao', '12345678', '0458799', 'organiser', 'pending');
INSERT INTO user (id, email, first_name, last_name, password, phone, type, status) VALUES (5, 'qwppp@qq.com', 'zhuo', 'er', '12345678', '0123456', 'organiser', 'pending');
INSERT INTO user (id, email, first_name, last_name, password, phone, type, status) VALUES (7, 'admin@qq.com', 'admin', 'admin', '12345678', '888888', 'admin', 'active');
INSERT INTO user (id, email, first_name, last_name, password, phone, type, status) VALUES (8, 'ownertest@qq.com', 'ownerrrrr', 'test', '12345678', '04242222', 'owner', 'active');
INSERT INTO user (id, email, first_name, last_name, password, phone, type, status) VALUES (9, 'organisertest@qq.com', 'organiserrrrr', 'test', '12345678', '042433333', 'organiser', 'active');

/*Venue Data*/
INSERT INTO venue (id, user_id, description, latitude, longitude, picture, state, street, suburb) VALUES (1,9,'test description',-33.8785839,151.2014858,'ChinaTown1.png','state1','street1','2007');
INSERT INTO venue (id, user_id, description, latitude, longitude, picture, state, street, suburb) VALUES (2,3,'test description2',-33.8785839,151.2014858,'ChinaTown2.png','state2','street2','2008');

/*Stall Data*/
INSERT INTO stall (venue_id, id, price) VALUES (1,'C_01','100');
INSERT INTO stall (venue_id, id, price) VALUES (1,'C_02','200');
INSERT INTO stall (venue_id, id, price) VALUES (1,'C_03','300');
INSERT INTO stall (venue_id, id, price) VALUES (1,'C_04','400');
INSERT INTO stall (venue_id, id, price) VALUES (1,'C_05','500');

INSERT INTO stall (venue_id, id, price) VALUES (2,'B_01','500');
INSERT INTO stall (venue_id, id, price) VALUES (2,'B_02','100');


