CREATE TABLE `role` (
	`id` bigint(10) NOT NULL AUTO_INCREMENT,
	`description` varchar(300) NOT NULL,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`enabled` bool NOT NULL DEFAULT true,
	PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
	`id` bigint(10) NOT NULL AUTO_INCREMENT,
	`name` varchar(300) NOT NULL,
	`email` varchar(300) NOT NULL,
	`birth_date` TIMESTAMP NOT NULL,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`enabled` bool NOT NULL DEFAULT true,
	`id_role` bigint(10),
	PRIMARY KEY (`id`)
);

CREATE TABLE `reputation_user` (
	`id_user` bigint(10) NOT NULL,
	`score` int NOT NULL,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `question` (
	`id` bigint(10) NOT NULL AUTO_INCREMENT,
	`id_user` bigint(10) NOT NULL,
	`id_flag` bigint(10) NOT NULL,
	`comment` varchar(500) NOT NULL,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

CREATE TABLE `flags` (
	`id` bigint(10) NOT NULL AUTO_INCREMENT,
	`description` varchar(300) NOT NULL,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

CREATE TABLE `answer` (
	`id` bigint(10) NOT NULL AUTO_INCREMENT,
	`id_user` bigint(10) NOT NULL,
	`id_question` bigint(10) NOT NULL,
	`comment` varchar(500) NOT NULL,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

CREATE TABLE `vote_answer` (
	`id` bigint(10) NOT NULL AUTO_INCREMENT,
	`id_answer` bigint(10) NOT NULL,
	`id_user` bigint(10) NOT NULL,
	`score` int NOT NULL,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

ALTER TABLE `user` ADD CONSTRAINT `user_fk0` FOREIGN KEY (`id_role`) REFERENCES `role`(`id`);

ALTER TABLE `reputation_user` ADD CONSTRAINT `reputation_user_fk0` FOREIGN KEY (`id_user`) REFERENCES `user`(`id`);

ALTER TABLE `question` ADD CONSTRAINT `question_fk0` FOREIGN KEY (`id_user`) REFERENCES `user`(`id`);

ALTER TABLE `question` ADD CONSTRAINT `question_fk1` FOREIGN KEY (`id_flag`) REFERENCES `flags`(`id`);

ALTER TABLE `answer` ADD CONSTRAINT `answer_fk0` FOREIGN KEY (`id_user`) REFERENCES `user`(`id`);

ALTER TABLE `answer` ADD CONSTRAINT `answer_fk1` FOREIGN KEY (`id_question`) REFERENCES `question`(`id`);

ALTER TABLE `vote_answer` ADD CONSTRAINT `vote_answer_fk0` FOREIGN KEY (`id_answer`) REFERENCES `answer`(`id`);

ALTER TABLE `vote_answer` ADD CONSTRAINT `vote_answer_fk1` FOREIGN KEY (`id_user`) REFERENCES `user`(`id`);
