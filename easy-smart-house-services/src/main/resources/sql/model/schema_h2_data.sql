-- IMAGES
INSERT INTO IMAGES(ID, FILE_NAME, FILE_CONTENT) VALUES(1, '001-safebox.png', FILE_READ('images/001-safebox.png'));
INSERT INTO IMAGES(ID, FILE_NAME, FILE_CONTENT) VALUES(2, '002-thermostat.png', FILE_READ('images/002-thermostat.png'));
INSERT INTO IMAGES(ID, FILE_NAME, FILE_CONTENT) VALUES(3, '003-window.png', FILE_READ('images/003-window.png'));
INSERT INTO IMAGES(ID, FILE_NAME, FILE_CONTENT) VALUES(4, '004-computer.png', FILE_READ('images/004-computer.png'));
INSERT INTO IMAGES(ID, FILE_NAME, FILE_CONTENT) VALUES(5, '005-router.png', FILE_READ('images/005-router.png'));
INSERT INTO IMAGES(ID, FILE_NAME, FILE_CONTENT) VALUES(6, '006-water.png', FILE_READ('images/006-water.png'));
INSERT INTO IMAGES(ID, FILE_NAME, FILE_CONTENT) VALUES(7, '007-oven.png', FILE_READ('images/007-oven.png'));
INSERT INTO IMAGES(ID, FILE_NAME, FILE_CONTENT) VALUES(8, '008-server.png', FILE_READ('images/008-server.png'));
INSERT INTO IMAGES(ID, FILE_NAME, FILE_CONTENT) VALUES(9, '009-coffee-machine.png', FILE_READ('images/009-coffee-machine.png'));
INSERT INTO IMAGES(ID, FILE_NAME, FILE_CONTENT) VALUES(10, '010-blender.png', FILE_READ('images/010-blender.png'));
INSERT INTO IMAGES(ID, FILE_NAME, FILE_CONTENT) VALUES(11, '011-air-conditioner.png', FILE_READ('images/011-air-conditioner.png'));
--

-- SPACES
INSERT INTO SPACES(ID, NAME, IMAGE) VALUES(1, 'Guest room', 3);
INSERT INTO SPACES(ID, NAME, IMAGE) VALUES(2, 'Kitchen', 7);
INSERT INTO SPACES(ID, NAME, IMAGE) VALUES(3, 'Meeting room', 9);
--

-- SENSORS
INSERT INTO SENSORS VALUES (1, 'EC000801AC673410', 'tempSensor1', 'Temperature sensor outside', 'true', 'TemperatureSensor', 1);
--

-- ACTUATORS
INSERT INTO ACTUATORS VALUES (2, '6900000002402B05', 'tableLampSwitch', 'Настольная лампа', 'true', 'switchActuator', 2);
--


-- USERS
INSERT INTO USERS VALUES (1, 'mike', '$2a$10$bTyMDqHNK7NUm4qrwvvLG.bb6n.hdE3.o6Xd9mZhmjybH6Z1I0csq', 'true', 'Mike', 'Rusakovich', 'mikhail.complete@gmail.com');
INSERT INTO AUTHORITIES VALUES (1, 'USER');

INSERT INTO USERS VALUES (2, 'mike2', '$2a$10$bTyMDqHNK7NUm4qrwvvLG.bb6n.hdE3.o6Xd9mZhmjybH6Z1I0csq', 'true', 'Mike', 'Rusakovich', 'mikhail.complete.admin@gmail.com');
INSERT INTO AUTHORITIES VALUES (2, 'ADMIN');
--