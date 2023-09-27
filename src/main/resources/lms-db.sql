INSERT INTO `member` (id, name, address, contact)
VALUES ('104ccff3-c584-4782-a582-8a06479b4600','Pubudu Janith','Horana','071-7845123'),
       ('2714641a-301e-43d5-9d31-ad916d075700','Supun Sudeera','Kaluthara','077-8525693'),
       ('2714641a-301e-43d5-9d31-ad916d075800','Kasun Subasinghe','Panadura','072-4512369');

INSERT INTO `book` (isbn, title, author, copies)
VALUES ('978-3-16-148410-0','Patterns of Enterprise Application Architecture','Martin Fowler',1),
       ('978-3-16-148410-1','Application Architecture','Microsoft',2),
       ('978-3-16-148410-2','Clean Code','Robert Martin',3),
       ('978-3-16-148410-3','MongoDB Specification','MongoDB Inc',1),
       ('978-3-16-148410-4','Introduction to Cloud Computing ','Martin Robert',4),
       ('978-3-16-148410-5','ECMAScript Specification 2022','ECMA Body',1),
       ('978-3-16-148410-6','Java Language Specification','James Gosling',2),
       ('978-3-16-148410-7','Effective Java 3','Oracle Team',1);

INSERT INTO `issue-note` (id, date, member_id)
VALUES (1,'2023-01-10','104ccff3-c584-4782-a582-8a06479b4600'),
       (2,'2023-01-11','2714641a-301e-43d5-9d31-ad916d075700');

INSERT INTO `issue-item` (issue_id, isbn)
VALUES (1,'978-3-16-148410-0'),
       (1,'978-3-16-148410-1'),
       (2,'978-3-16-148410-2'),
       (2,'978-3-16-148410-3');

INSERT INTO `return-note` (date, issue_id, isbn)
VALUES ('2023-01-14',1,'978-3-16-148410-0'),
       ('2023-01-15',2,'978-3-16-148410-2');