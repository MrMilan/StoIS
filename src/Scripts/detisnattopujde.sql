SELECT p.personid from Persons AS p
JOIN  Users AS u ON p.personid = u.persons_personid
