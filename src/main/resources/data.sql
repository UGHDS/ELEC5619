/*User Data*/
INSERT INTO user (id, email, first_name, last_name, password, phone, type, status) VALUES (1, 'owner1@gmail.com', 'Own', 'Jones', '12345678', '04123456', 'owner', 'active');
INSERT INTO user (id, email, first_name, last_name, password, phone, type, status) VALUES (2, 'qwer@gamil.com', 'hong', 'hong', '12345678', '043215', 'organiser', 'pending');
INSERT INTO user (id, email, first_name, last_name, password, phone, type, status) VALUES (3, 'hhh@qq.com', 'ha', 'ha', '12345678', '042299', 'organiser', 'pending');
INSERT INTO user (id, email, first_name, last_name, password, phone, type, status) VALUES (4, 'open@qq.com', 'xiao', 'xiao', '12345678', '0458799', 'organiser', 'pending');
INSERT INTO user (id, email, first_name, last_name, password, phone, type, status) VALUES (5, 'qwppp@qq.com', 'zhuo', 'er', '12345678', '0123456', 'organiser', 'pending');
INSERT INTO user (id, email, first_name, last_name, password, phone, type, status) VALUES (7, 'admin@qq.com', 'admin', 'admin', '12345678', '888888', 'admin', 'active');
INSERT INTO user (id, email, first_name, last_name, password, phone, type, status) VALUES (8, 'ownertest@qq.com', 'ownerrrrr', 'test', '12345678', '04242222', 'owner', 'active');
INSERT INTO user (id, email, first_name, last_name, password, phone, type, status) VALUES (9, 'organisertest@qq.com', 'organiserrrrr', 'test', '12345678', '042433333', 'organiser', 'active');

/*Venue*/
INSERT INTO elec5619.venue (id, latitude, longitude, user_id, description, picture, state, street, suburb, venue_name) VALUES (1, -33.8785839, 151.2014858, 9, 'test description', 'ChinaTown1.png', 'NSW 2007', 'Cricketers Arms Rd', 'Prospect', 'Blacktown Markets');
INSERT INTO elec5619.venue (id, latitude, longitude, user_id, description, picture, state, street, suburb, venue_name) VALUES (2, -33.8785839, 151.2014858, 3, 'test description2', 'ChinaTown2.png', 'NSW 2008', '443 Smithfield Rd', 'Prairiewood', 'Fairfield Markets');

/*Venue Date*/
INSERT INTO venue_date (date_slot, venue_id) VALUES ('2023-09-25', 1);

INSERT INTO venue_date (date_slot, venue_id) VALUES ('2023-09-24', 2);


/*Stall*/
INSERT INTO stall (venue_id, stall_id, price) VALUES (1,'C_01','100');
INSERT INTO stall (venue_id, stall_id, price) VALUES (1,'C_02','200');
INSERT INTO stall (venue_id, stall_id, price) VALUES (1,'C_03','300');
INSERT INTO stall (venue_id, stall_id, price) VALUES (1,'C_04','400');
INSERT INTO stall (venue_id, stall_id, price) VALUES (1,'C_05','500');

INSERT INTO stall (venue_id, stall_id, price) VALUES (2,'B_01','500');
INSERT INTO stall (venue_id, stall_id, price) VALUES (2,'B_02','100');


/*Stall Date*/
INSERT INTO stall_date (date_slot, stall_id, venue_id, id, status) VALUES ('2023-09-25', 1, 1, 6, 'Available');
INSERT INTO stall_date (date_slot, stall_id, venue_id, id, status) VALUES ('2023-09-25', 2, 1, 7, 'Available');
INSERT INTO stall_date (date_slot, stall_id, venue_id, id, status) VALUES ('2023-09-25', 3, 1, 8, 'Available');
INSERT INTO stall_date (date_slot, stall_id, venue_id, id, status) VALUES ('2023-09-25', 4, 1, 9, 'Available');
INSERT INTO stall_date (date_slot, stall_id, venue_id, id, status) VALUES ('2023-09-25', 5, 1, 10, 'Available');
INSERT INTO stall_date (date_slot, stall_id, venue_id, id, status) VALUES ('2023-09-24', 6, 2, 11, 'Available');
INSERT INTO stall_date (date_slot, stall_id, venue_id, id, status) VALUES ('2023-09-24', 7, 2, 12, 'Available');


/*Booking*/
INSERT INTO booking (id, booking_time, stall_date_id, user_id, status) VALUES (1, '2023-09-25 12:35:47.011265', 8, 9, 'Booked');
INSERT INTO booking (id, booking_time, stall_date_id, user_id, status) VALUES (2, '2023-09-25 12:35:50.743700', 7, 9, 'Cancelled');
INSERT INTO booking (id, booking_time, stall_date_id, user_id, status) VALUES (3, '2023-09-25 12:36:09.904322', 9, 9, 'Booked');


