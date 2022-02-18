INSERT INTO CATEGORY (ID, NAME, SHORT_NAME, FROM_AGE, TO_AGE)
VALUES (1, 'Dziecko młodsze', 'DM', 0, 9),
       (2, 'Dziecko', 'D', 10, 11),
       (3, 'Młodzik', 'Mł', 12, 13),
       (4, 'Junior młodszy', 'JM', 14, 15),
       (5, 'Junior', 'J', 16, 17),
       (6, 'Młodzieżowiec', 'M', 18, 19),
       (7, 'Senior', 'S', 20, 1000);

INSERT INTO FORMULA (ID, NAME, DESCRIPTION, DISCIPLINE, NUMBER_OF_ROUNDS, JSON_CONFIGURATION)
VALUES (1,
        'Standardowe prowadzenie',
        'Dwie rundy: eliminacyjna i finałowa. Dwie drogi eliminacyjne i jedna finałowa - każdy zawodnik wspina się po obydwu. 8 zawodników w finale.',
        'LEAD',
        2,
        '[' ||
        '{"sequenceNumber":0,"name":"Eliminacyje","numberOfRoutes":2,"maxParticipants":2147483647,"startsGenerationMethod":"LEAD_CLASSIC_ELIMINATIONS","resultCalculatingFunction":"?"},' ||
        '{"sequenceNumber":1,"name":"Finał","numberOfRoutes":1,"maxParticipants":8,"startsGenerationMethod":"LEAD_CLASSIC_FINAL","resultCalculatingFunction":"?"}' ||
        ']');