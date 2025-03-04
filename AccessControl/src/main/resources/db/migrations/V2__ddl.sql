INSERT INTO role (name) VALUES
    ('user'),
    ('ban'),
    ('admin');

INSERT INTO path (method, name) VALUES
    ('PATCH', '/users/**/roles'),
    ('GET', '/permissions/**'),
    ('GET', '/urls'),
    ('DELETE', '/urls/**'),
    ('GET', '/user'),
    ('GET', '/users'),
    ('POST', '/users');