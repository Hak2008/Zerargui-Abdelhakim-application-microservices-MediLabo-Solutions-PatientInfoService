CREATE TABLE `patient` (
                                       `id` int NOT NULL AUTO_INCREMENT,
                                       `firstName` varchar(255) NOT NULL,
                                       `lastName` varchar(255) NOT NULL,
                                       `dateOfBirth` date NOT NULL,
                                       `gender` varchar(10) NOT NULL,
                                       `address` varchar(255) DEFAULT NULL,
                                       `phoneNumber` varchar(20) DEFAULT NULL,
                                       PRIMARY KEY (`id`)
            )

