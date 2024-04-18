DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS phones;

CREATE TABLE users (user_id VARCHAR(50) PRIMARY KEY,
 name VARCHAR(30),
 email VARCHAR(20),
 password VARCHAR(20),
 created_date TIMESTAMP,
 last_update_date TIMESTAMP,
 last_login TIMESTAMP,
 state BOOLEAN,
 token VARCHAR(100)
);

CREATE TABLE phones (phone_id VARCHAR(50) PRIMARY KEY,
 user_id VARCHAR(50),
 number VARCHAR(10),
 city_code VARCHAR(10),
 country_code VARCHAR(10),
 foreign key (user_id) references users(user_id)
);