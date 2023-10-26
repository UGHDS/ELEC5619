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
INSERT INTO elec5619.venue (id, latitude, longitude, user_id, description, picture, state, street, suburb, venue_name) VALUES (1, -33.8054155, 150.9011621, 9, 'Blacktown Markets is where the garage sales, op shops, farmers, growers, producers and various vendors come together!

Whether you’re looking to go op shopping, snag a bargain from one of the garage sales, pick up some fresh fruit, veggies and produce or just enjoy a lively outdoor marketplace in Sydney’s last remaining drive-in theatre, Blacktown Markets is the place to be.', 'https://images.toopa.com/82121_87526244013_IMG_1386.JPG', 'NSW 2007', 'Cricketers Arms Rd', 'Prospect', 'Blacktown Markets');
INSERT INTO elec5619.venue (id, latitude, longitude, user_id, description, picture, state, street, suburb, venue_name) VALUES (2, -33.8675706, 150.9012203, 3, 'Fairfield Showground is renowned as the venue of the Fairfield Markets which have been attracting many thousands of shoppers each weekend since the Seventies. Our markets are the largest in Sydney’s West. Friendly Family Fun is the motto of these markets and the fun is guaranteed.

Every Saturday from 9AM to 4PM up to 600 undercover stalls are filled with everything from childrens’ toys to clothes, sporting gear to jewellery, household items to car accessories, workshop tools to pool products and grocery items to fresh fruit and vege’s. While the adults look for the bargains the kids can use up their energy on the jumping castle, carnival rides, merry go round or pony rides. And, when it is time for a relaxing break there are plenty of places to pick up mouth watering kebabs, burgers, Asian delicacies, ice creams, donuts, cold drinks or coffee.', 'https://www.fairfieldshowground.com.au/wp-content/uploads/2017/12/3-1.jpg', 'NSW 2008', '443 Smithfield Rd', 'Prairiewood', 'Fairfield Markets');

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
INSERT INTO stall_date (date_slot, stall_id, venue_id, id, status) VALUES ('2023-09-25', 3, 1, 8, 'Booked');
INSERT INTO stall_date (date_slot, stall_id, venue_id, id, status) VALUES ('2023-09-25', 4, 1, 9, 'Booked');
INSERT INTO stall_date (date_slot, stall_id, venue_id, id, status) VALUES ('2023-09-25', 5, 1, 10, 'Available');
INSERT INTO stall_date (date_slot, stall_id, venue_id, id, status) VALUES ('2023-09-24', 6, 2, 11, 'Available');
INSERT INTO stall_date (date_slot, stall_id, venue_id, id, status) VALUES ('2023-09-24', 7, 2, 12, 'Available');


/*Booking*/
INSERT INTO booking (id, booking_time, stall_date_id, user_id, status) VALUES (1, '2023-09-25 12:35:47.011265', 8, 1, 'Booked');
INSERT INTO booking (id, booking_time, stall_date_id, user_id, status) VALUES (2, '2023-09-25 12:35:50.743700', 7, 1, 'Cancelled');
INSERT INTO booking (id, booking_time, stall_date_id, user_id, status) VALUES (3, '2023-09-25 12:36:09.904322', 9, 1, 'Booked');


