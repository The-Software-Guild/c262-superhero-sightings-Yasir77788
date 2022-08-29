DROP DATABASE IF EXISTS superHeroSightingsDB;
CREATE DATABASE superHeroSightingsDB;

USE superHeroSightingsDB;

CREATE TABLE  Hero(
    heroId INT PRIMARY KEY AUTO_INCREMENT,
    heroName VARCHAR (25) NOT NULL,
    heroDescription VARCHAR(100) NOT NULL,
    superPower VARCHAR(25) NOT NULL,
    isHero Boolean NOT NULL
);


CREATE TABLE Location (
    locationId INT PRIMARY KEY AUTO_INCREMENT,
    locationName VARCHAR(25) NOT NULL,
    locationDescription VARCHAR(100) NOT NULL,
    street VARCHAR(100) NOT NULL,
    city VARCHAR(30) NOT NULL,
    state CHAR(2) NOT NULL,
    zipcode CHAR(5),
    locationLat CHAR(10) NOT NULL,
    locationLong CHAR(10) NOT NULL
);

CREATE TABLE Organization (
    orgId INT PRIMARY KEY AUTO_INCREMENT,
    orgName VARCHAR(50) NOT NULL,
    orgDescription VARCHAR(100) NOT NULL,
    orgPhone CHAR(15),
    orgEmail VARCHAR(50),
    isHeroOrg Boolean NOT NULL,
    locationId INT NOT NULL,

    FOREIGN KEY fk_location_org(locationId) REFERENCES Location(locationId)
);

CREATE TABLE Hero_Org_Bridge (
    heroId INT NOT NULL,
    orgId INT NOT NULL,

    primary key (heroId, orgId),
    FOREIGN KEY fk_hero_ref (HeroId) REFERENCES Hero(heroId),
    FOREIGN KEY fk_org_ref (orgId) REFERENCES Organization(orgId)

);

CREATE TABLE Sighting (

    sightingId INT PRIMARY KEY AUTO_INCREMENT,
    sightingDate DATE NOT NULL,

    heroId INT NOT NULL,
    locationId INT NOT NULL,

    FOREIGN KEY fk_sight_Location(locationId) REFERENCES Location(locationId),
    FOREIGN KEY fk_sight_Hero(heroId) REFERENCES Hero(heroId)
);
