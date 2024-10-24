DROP DATABASE IF EXISTS database1;
CREATE DATABASE database1;

USE database1;

DROP TABLE IF EXISTS city;
CREATE TABLE City (
	ID	INTEGER,
	Name	TEXT,
	PRIMARY KEY(ID)
);

DROP TABLE IF EXISTS Tag;
CREATE TABLE Tag (
	ID	INTEGER,
	Name	TEXT,
	PRIMARY KEY(ID)
);

DROP TABLE IF EXISTS TouristAttraction;
CREATE TABLE TouristAttraction (
	Name	TEXT,
	Description	TEXT,
	City_ID	INTEGER,
	Price	INTEGER,
	ID	INTEGER,
	PRIMARY KEY(ID),
	FOREIGN KEY(City_ID) REFERENCES City(ID)
);

DROP TABLE IF EXISTS TouristAttraction_Tags;
CREATE TABLE TouristAttraction_Tags (
	Attraction_ID	INTEGER,
	Tag_ID	INTEGER,
	PRIMARY KEY(Attraction_ID,Tag_ID),
	FOREIGN KEY(Attraction_ID) REFERENCES TouristAttraction(ID),
	FOREIGN KEY(Tag_ID) REFERENCES Tag(ID)
);

INSERT INTO City VALUES (1,'København');
INSERT INTO City VALUES (2,'Billund');
INSERT INTO City VALUES (3,'Hellerup');
INSERT INTO City VALUES (4,'Klampenborg');
INSERT INTO Tag VALUES (1,'Statue');
INSERT INTO Tag VALUES (2,'Seværdighed');
INSERT INTO Tag VALUES (3,'Underholdning');
INSERT INTO Tag VALUES (4,'Arkitektur');
INSERT INTO Tag VALUES (5,'Forlystelsespark');
INSERT INTO Tag VALUES (6,'Natur');
INSERT INTO TouristAttraction VALUES ('Den lille havfrue','Statue af den lille havfrue',1,0,1);
INSERT INTO TouristAttraction VALUES ('Rundetårn','Et højt, rundt tårn',1,100,2);
INSERT INTO TouristAttraction VALUES ('Tivoli','Gammel, dansk forlystelsespark',1,169,3);
INSERT INTO TouristAttraction VALUES ('Dyrehavsbakken','En forlystelsespark og en park ude i naturen',4,0,4);
INSERT INTO TouristAttraction VALUES ('Legoland','Forlystelsespark med legoklodser',2,200,5);
INSERT INTO TouristAttraction_Tags VALUES (1,1);
INSERT INTO TouristAttraction_Tags VALUES (3,5);
INSERT INTO TouristAttraction_Tags VALUES (3,3);
INSERT INTO TouristAttraction_Tags VALUES (5,5);
INSERT INTO TouristAttraction_Tags VALUES (5,3);
INSERT INTO TouristAttraction_Tags VALUES (4,5);
INSERT INTO TouristAttraction_Tags VALUES (4,3);
INSERT INTO TouristAttraction_Tags VALUES (4,6);
INSERT INTO TouristAttraction_Tags VALUES (2,2);
INSERT INTO TouristAttraction_Tags VALUES (2,4);
COMMIT;
