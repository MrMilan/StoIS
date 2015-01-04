SELECT p from Persons AS p
JOIN Persons_has_roles AS phr ON p.personid = phr.persons_personid
WHERE (p.canceled=false AND p.archived=false) AND phr.roles_roleid <> 4
--AND p.personid <> (SELECT q.personid from Persons AS q JOIN  Users AS m ON q.personid = m.persons_personid);

--JOIN  Users AS u ON p.personid =  u.persons_personid
--JOIN Persons_has_roles AS phr ON p.personid = phr.persons_personid
--AND phr.roles_roleid <> 4
