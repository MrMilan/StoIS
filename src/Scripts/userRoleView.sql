SELECT r.roleid,r.rolename from Persons AS p
JOIN  Users AS u ON p.personid =  u.persons_personid
JOIN Persons_has_roles AS phr ON p.personid = phr.persons_personid
JOIN Roles AS r ON r.roleid = phr.roles_roleid
WHERE u.usersid=1;
