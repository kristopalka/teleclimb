INSERT INTO COMPETITION (ID, NAME, FORMULA_ID, GENDER, CATEGORY_ID)
VALUES (1, 'Puchar Polski', 1, 'MALE', 4),
       (2, 'Puchar Polski', 1, 'FEMALE', 4);


INSERT INTO PARTICIPANT (ID, NAME, LAST_NAME, RANKING_POSITION, START_NUMBER, CLUB_NAME, BIRTH_DATE, COMPETITION_ID)
VALUES (1, 'Krzysztof', 'Pałka', 2, 2137, 'AZS AGH', '2000-08-26', 1),
       (2, 'Andrzej', 'Krzywda', 4, 9706, 'AZS AGH', '2002-08-26', 1),
       (3, 'Mikołaj', 'Cheretyk', 7, 9706, 'Korona Kraków', '2004-12-26', 1),
       (4, 'Kinga', 'Ociepka', 10, 3452, 'Korona Kraków', '2007-08-26', 2),
       (5, 'Aleksandra', 'Kotwas', 1, 2355, 'AZS AWF', '2010-08-26', 2),
       (6, 'Kinga', 'Pałka', 5442, 7, 'Pałac młodzieży Tarnów', '2015-08-26', 2);

INSERT INTO ROUTE (ID, NAME, DESCRIPTION, DISCIPLINE)
VALUES (1, 'MA', 'Męska eliminacyjna A', 'LEAD'),
       (2, 'MB', 'Męska eliminacyjna B', 'LEAD'),
       (3, 'MF', 'Męska finałowa', 'LEAD');