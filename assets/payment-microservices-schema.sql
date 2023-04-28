CREATE TABLE `users` (
  `id` integer PRIMARY KEY,
  `user_id` varchar(255),
  `username` varchar(255),
  `phone_number` varchar(255),
  `created_at` timestamp
);

CREATE TABLE `orders` (
  `id` integer PRIMARY KEY,
  `order_id` varchar(255),
  `status` varchar(255),
  `total` double,
  `user_id` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `payments` (
  `id` integer PRIMARY KEY,
  `customer_payment_id` varchar(255),
  `user_id` varchar(255),
  `order_id` varchar(255),
  `amount` double,
  `payment_method` varchar(255),
  `payment_provider` varchar(255),
  `status` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `wallets` (
  `id` integer PRIMARY KEY,
  `user_id` varchar(255),
  `balance` double,
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `wallet_transactions` (
  `id` integer PRIMARY KEY,
  `wallet_id` int,
  `transaction_type` varchar(255),
  `customer_payment_id` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE UNIQUE INDEX `idx_user_id` ON `users` (`user_id`);

CREATE UNIQUE INDEX `idx_order_id` ON `orders` (`order_id`);

CREATE UNIQUE INDEX `idx_createdAt)` ON `orders` (`created_at`);

CREATE UNIQUE INDEX `idx_customer_payment_id` ON `payments` (`customer_payment_id`);

CREATE UNIQUE INDEX `idx_order_id` ON `payments` (`order_id`);

CREATE UNIQUE INDEX `idx_user_id` ON `payments` (`user_id`);

CREATE UNIQUE INDEX `idx_createdAt` ON `payments` (`created_at`);

ALTER TABLE `wallet_transactions` ADD FOREIGN KEY (`wallet_id`) REFERENCES `wallets` (`id`);
