create table FORMULA
(
    ID                 INTEGER auto_increment
        primary key,
    DESCRIPTION        VARCHAR(255),
    DISCIPLINE         VARCHAR(255),
    JSON_CONFIGURATION CLOB,
    NAME               VARCHAR(255),
    NUMBER_OF_ROUNDS   INTEGER
);

INSERT INTO PUBLIC.FORMULA (ID, DESCRIPTION, DISCIPLINE, JSON_CONFIGURATION, NAME, NUMBER_OF_ROUNDS)
VALUES (1,
        'Dwie rundy: eliminacyjna i finałowa. Dwie drogi eliminacyjne i jedna finałowa - każdy zawodnik wspina się po obydwu. 8 zawodników w finale.',
        'LEAD',
        '[{"sequenceNumber":0,"name":"Eliminacyje","numberOfRoutes":2,"maxParticipants":2147483647,"startsGenerationMethod":"LEAD_CLASSIC_ELIMINATIONS","resultCalculatingFunction":"?"},{"sequenceNumber":1,"name":"Finał","numberOfRoutes":1,"maxParticipants":8,"startsGenerationMethod":"LEAD_CLASSIC_FINAL","resultCalculatingFunction":"?"}]',
        'Standardowe prowadzenie', 2);
