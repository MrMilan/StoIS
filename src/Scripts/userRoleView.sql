SELECT p from Persons AS p
--JOIN  Users AS u ON p.personid =  u.persons_personid
WHERE p.canceled=false AND p.archived=false AND p.personid <> (SELECT p.personid from Persons AS p
JOIN  Users AS u ON p.personid =  u.persons_personid);
