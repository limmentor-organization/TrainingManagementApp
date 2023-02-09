CREATE TABLE IF NOT EXISTS roles (
    code INT(3) NOT NULL,
    value VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id INT(3) NOT NULL auto_increment,
    role_code INT(3) NOT NULL,
    name VARCHAR(255) NOT NULL,
    password CHAR(60) NOT NULL,
    email VARCHAR(255) NOT NULL,
    FOREIGN KEY(role_code) REFERENCES roles(code) ON DELETE CASCADE,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS physical_details (
    user_id INT(3) NOT NULL,
    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
    weight DECIMAL(4, 1) NOT NULL,
    recorded_date DATE(10) NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE,
    PRIMARY KEY(created_at, user_id)
);