CREATE TABLE IF NOT EXISTS Event (
    eventId int(5) NOT NULL AUTO_INCREMENT,
    eventName varchar(50) DEFAULT NULL,
    startDate varchar(50) DEFAULT NULL,
    endDate  varchar(50) DEFAULT NULL,
    organizer varchar(50) DEFAULT NULL,
    PRIMARY KEY(eventId)
    );