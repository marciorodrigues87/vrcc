use vrcc;

CREATE TABLE `property` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `x` INTEGER NOT NULL,
  `y` INTEGER NOT NULL,
  `title` VARCHAR(1024) NOT NULL,
  `price` INTEGER NOT NULL,
  `description` VARCHAR(2048) NOT NULL,
  `beds` INTEGER NOT NULL,
  `baths` INTEGER NOT NULL,
  `squareMeters` INTEGER NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `property_province` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `property_id` INTEGER NOT NULL,
  `name` VARCHAR(512) NOT NULL,
  PRIMARY KEY (`id`)
);

ALTER TABLE `property_province` ADD FOREIGN KEY (property_id) REFERENCES `property` (`id`);
