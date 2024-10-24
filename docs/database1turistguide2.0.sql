DROP DATABASE IF EXISTS database1;
CREATE DATABASE database1;

USE database1;

DROP TABLE IF EXISTS city;
CREATE TABLE City (
	ID	INT AUTO_INCREMENT,
	Name	TEXT,
	PRIMARY KEY(ID)
);

DROP TABLE IF EXISTS Tag;
CREATE TABLE Tag (
	ID	INT AUTO_INCREMENT,
	Name	TEXT,
	PRIMARY KEY(ID)
);

DROP TABLE IF EXISTS TouristAttraction;
CREATE TABLE TouristAttraction (
	ID	INT AUTO_INCREMENT,
    Name	TEXT,
	Description	TEXT,
	City_ID	INT,
	PRIMARY KEY(ID),
	FOREIGN KEY(City_ID) REFERENCES City(ID)
);

DROP TABLE IF EXISTS TouristAttraction_Tags;
CREATE TABLE TouristAttraction_Tags (
	Attraction_ID	INT,
	Tag_ID	INT,
	PRIMARY KEY(Attraction_ID,Tag_ID),
	FOREIGN KEY(Attraction_ID) REFERENCES TouristAttraction(ID) ON DELETE CASCADE,
	FOREIGN KEY(Tag_ID) REFERENCES Tag(ID)
);

INSERT INTO City (Name) VALUES ('København');
INSERT INTO City (Name) VALUES ('Billund');
INSERT INTO City (Name) VALUES ('Hellerup');
INSERT INTO City (Name) VALUES ('Klampenborg');
INSERT INTO Tag (Name) VALUES ('Statue');
INSERT INTO Tag (Name) VALUES ('Seværdighed');
INSERT INTO Tag (Name) VALUES ('Underholdning');
INSERT INTO Tag (Name) VALUES ('Arkitektur');
INSERT INTO Tag (Name) VALUES ('Forlystelsespark');
INSERT INTO Tag (Name) VALUES ('Natur');

