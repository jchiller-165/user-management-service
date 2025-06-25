insert into `user_management`.`company_info`
(`id`, `company_name`, `address`, `city`,`state`, `country`, `postal_code`, `phone_number`, `website`) VALUES
(1, 'Tech Innovations Inc.', '123 Tech Lane', 'Silicon Valley', 'California', 'USA', '94043', '+1-650-123-4567', 'www.techinnovations.com');

insert into `user_management`.`contact_info`
(`id`, `first_name`, `last_name`, `contact_type`, `email`, `address`, `phone_number`, `city`, `state`, `postal_code`, `country`) VALUES
(1, 'Alice', 'Johnson', 'User', 'alice@email.com', '456 Elm Street', '+1-650-987-6543', 'San Francisco', 'California', '94105', 'USA'),
(2, 'delete', 'me', 'User', 'deleteme@email.com', '456 Elm Street', '+1-650-987-6543', 'San Francisco', 'California', '94105', 'USA');

insert into `user_management`.`user_info` (`id`, `username`, `role`, `company_id`, `contact_id`) VALUES
(1, 'alice_johnson', 'ADMIN', 1, 1),
(999, 'delete_me', 'ADMIN', 1, 2);
