INSERT INTO roles (code, value) VALUES (0, 'ROLE_ADMIN');
INSERT INTO roles (code, value) VALUES (1, 'ROLE_GENERAL');

INSERT INTO users (id, role_code, name, password, email) VALUES (0, 0, 'user1', '$2a$10$Lp6BBS6ezhs68OBzFdY9Jui9rqnPSKV.iyy2mu7VjyqR/f0fS.VBq', 'email01@com');
INSERT INTO users (id, role_code, name, password, email) VALUES (1, 1, 'user2', '$2a$10$Lp6BBS6ezhs68OBzFdY9Jui9rqnPSKV.iyy2mu7VjyqR/f0fS.VBq', 'email02@com');

INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (67.2, '2022-08-21', CURRENT_TIMESTAMP, 0);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (66.8, '2022-08-22', '2022-07-18 12:34:56', 0);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (68.0, '2022-08-23', '2022-07-19 12:34:56', 0);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (68.4, '2022-08-24', '2022-07-20 12:34:56', 0);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (67.6, '2022-08-25', '2022-07-21 12:34:56', 0);

INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (67.6, '2022-08-26', '2022-07-22 12:34:56', 0);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (67.6, '2022-08-27', '2022-07-23 12:34:56', 0);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (67.6, '2022-08-28', '2022-08-23 12:34:56', 0);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (67.6, '2022-08-29', '2022-09-23 12:34:56', 0);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (68.6, '2022-08-30', '2022-08-13 12:34:56', 0);

INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (68.0, '2022-08-31', '2022-10-19 12:34:56', 0);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (68.0, '2022-09-01', '2022-06-27 12:34:56', 0);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (0.0, '2022-09-02', '2022-05-01 12:34:56', 0);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (0.0, '2022-09-03', '2022-05-02 12:34:56', 0);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (0.0, '2022-09-04', '2022-05-03 12:34:56', 0);

INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (0.0, '2022-09-05', '2022-04-09 12:34:56', 0);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (0.0, '2022-09-06', '2022-04-11 12:34:56', 0);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (0.0, '2022-09-07', '2022-04-13 12:34:56', 0);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (0.0, '2022-09-08', '2022-04-15 12:34:56', 0);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (0.0, '2022-09-09', '2022-04-17 12:34:56', 0);

INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (0.0, '2022-09-10', '2022-04-19 12:34:56', 0);

INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (101.1, '2022-04-10', '2022-07-24 12:34:57', 1);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (102.1, '2022-04-10', '2022-07-25 12:34:58', 1);
INSERT INTO physical_details (weight, recorded_date, created_at, user_id) VALUES (103.1, '2022-04-11', '2022-07-26 12:34:58', 1);