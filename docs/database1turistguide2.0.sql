DROP DATABASE IF EXISTS database1;
CREATE DATABASE database1;

USE database1;

DROP TABLE IF EXISTS City;
CREATE TABLE City (
	city_id	INT AUTO_INCREMENT,
	city_name	TEXT,
	PRIMARY KEY(city_id)
);

DROP TABLE IF EXISTS Tag;
CREATE TABLE Tag (
	tag_id	INT AUTO_INCREMENT,
	tag_name	TEXT,
	PRIMARY KEY(tag_id)
);

DROP TABLE IF EXISTS TouristAttraction;
CREATE TABLE TouristAttraction (
	touristAttraction_id	INT AUTO_INCREMENT,
    touristAttraction_name	TEXT,
	touristAttraction_description	TEXT,
	city_id	INT,
	PRIMARY KEY(touristAttraction_id),
	FOREIGN KEY(city_id) REFERENCES City(city_id)
);

DROP TABLE IF EXISTS TouristAttraction_Tags;
CREATE TABLE TouristAttraction_Tags (
	touristAttraction_id	INT,
	tag_id	INT,
	PRIMARY KEY(touristAttraction_id,tag_id),
	FOREIGN KEY(touristAttraction_id) REFERENCES TouristAttraction(touristAttraction_id) ON DELETE CASCADE,
	FOREIGN KEY(tag_id) REFERENCES Tag(tag_id)
);

INSERT INTO City (city_name) VALUES ('København');
INSERT INTO City (city_name) VALUES ('Billund');
INSERT INTO City (city_name) VALUES ('Hellerup');
INSERT INTO City (city_name) VALUES ('Klampenborg');

INSERT INTO Tag (tag_name) VALUES ('Statue');
INSERT INTO Tag (tag_name) VALUES ('Seværdighed');
INSERT INTO Tag (tag_name) VALUES ('Forlystelsespark');
INSERT INTO Tag (tag_name) VALUES ('Underholdning');
INSERT INTO Tag (tag_name) VALUES ('Restaurant');
INSERT INTO Tag (tag_name) VALUES ('Koncert');
INSERT INTO Tag (tag_name) VALUES ('Teater');
INSERT INTO Tag (tag_name) VALUES ('Natur');
INSERT INTO Tag (tag_name) VALUES ('Museum');
INSERT INTO Tag (tag_name) VALUES ('Sport');
INSERT INTO Tag (tag_name) VALUES ('Park');
INSERT INTO Tag (tag_name) VALUES ('Design');
INSERT INTO Tag (tag_name) VALUES ('Natur');
INSERT INTO Tag (tag_name) VALUES ('Arkitektur');
INSERT INTO Tag (tag_name) VALUES ('Monumenter');
INSERT INTO Tag (tag_name) VALUES ('Kunst');
INSERT INTO Tag (tag_name) VALUES ('Historie');

INSERT INTO TouristAttraction (touristAttraction_name,touristAttraction_description,city_ID) VALUES ('Den lille havfrue','Statue af den lille havfrue',1);
INSERT INTO TouristAttraction (touristAttraction_name,touristAttraction_description,city_ID) VALUES ('Rundetårn','Et højt, rundt tårn',1);
INSERT INTO TouristAttraction (touristAttraction_name,touristAttraction_description,city_ID) VALUES ('Tivoli','Gammel, dansk forlystelsespark',1);
INSERT INTO TouristAttraction (touristAttraction_name,touristAttraction_description,city_ID) VALUES ('Dyrehavsbakken','En forlystelsespark og en park ude i naturen', 2);

INSERT INTO TouristAttraction_Tags VALUES (1,1);
INSERT INTO TouristAttraction_Tags VALUES (1,3);
INSERT INTO TouristAttraction_Tags VALUES (2,2);
INSERT INTO TouristAttraction_Tags VALUES (2,1);
INSERT INTO TouristAttraction_Tags VALUES (3,1);
INSERT INTO TouristAttraction_Tags VALUES (3,1);
INSERT INTO TouristAttraction_Tags VALUES (4,5);
INSERT INTO TouristAttraction_Tags VALUES (4,7);


