delete FROM test.purchases
where Id > 0;

insert into test.purchases(Id, Session_id, Note_text, Is_bought)
values
(1, 'A3967D16FCEAD5E938C318FBEE9BAD54', 'sweets', true),
(2, 'A3967D16FCEAD5E938C318FBEE9BAD54', 'coffee', false),
(3, 'A3967D16FCEAD5E938C318FBEE9BAD54', 'milk', false),
(4, 'A3967D16FCEAD5E938C318FBEE9BAD54', 'cookies', true);