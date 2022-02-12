INSERT INTO COMPETITION (ID, NAME, COMPETITION_TYPE, GENDER, CATEGORY_ID)
VALUES
    (1, 'Puchar Polski', 'LEAD', 'MALE', '4'),
    (2, 'Puchar Polski', 'LEAD', 'FEMALE', '4');


INSERT INTO CONTESTANT (ID, NAME, LAST_NAME, START_NUMBER, CLUB_NAME, BIRTH_DATE, COMPETITION_ID)
VALUES
    (1, 'Krzysztof', 'Pałka', 2137, 'AZS AGH', '2000-08-26', 1),
    (2, 'Andrzej', 'Krzywda', 9706, 'AZS AGH', '2000-08-26', 1),
    (3, 'Kinga', 'Ociepka', 3452, 'Korona Kraków', '2000-08-26', 2),
    (4, 'Aleksandra', 'Kotwas', 2355, 'AZS AWF', '2000-08-26', 2);