SELECT r.roleid,r.rolename FROM Roles AS r
LEFT JOIN persons_has_roles AS phr on phr.roles_roleid=r.roleid
WHERE phr.persons_personid= (SELECT p.personid FROM Persons AS p
LEFT JOIN Users AS u on u.persons_personid=p.personid
WHERE u.usersid=1);
