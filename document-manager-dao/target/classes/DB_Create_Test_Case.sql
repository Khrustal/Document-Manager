CREATE TABLE if not exists "user"(
    id serial PRIMARY KEY,
    login varchar(70) NOT NULL UNIQUE,
    password varchar(70) NOT NULL,
    email varchar(70) NOT NULL UNIQUE,
    admin boolean NOT NULL
);

CREATE TABLE if not exists document_types(
    id serial primary key,
    type_name varchar(50) NOT NULL UNIQUE
);

CREATE TYPE storable_types AS ENUM ('DOCUMENT', 'DIRECTORY');
CREATE TYPE statuses AS ENUM ('ON_MODERATION', 'OLD', 'CURRENT');
CREATE TYPE priorities AS ENUM ('LOW', 'MEDIUM', 'HIGH');

CREATE TABLE if not exists storable(
    id serial PRIMARY KEY,
    parent_id int,
    name varchar(70) NOT NULL,
    author_id int NOT NULL,
    type storable_types NOT NULL,
    free_access boolean NOT NULL,
    status statuses NOT NULL,
    creation_DT timestamp NOT NULL,
    description text,
    priority priorities,
    document_type int,
    ancestor_id int,
    FOREIGN KEY (author_id) REFERENCES "user"(id),
    FOREIGN KEY (parent_id) REFERENCES storable(id),
    FOREIGN KEY (document_type) REFERENCES document_types(id),
    FOREIGN KEY (ancestor_id) REFERENCES storable(id)
);

CREATE TABLE if not exists file(
    id serial PRIMARY KEY,
    name varchar(70),
    type varchar(20),
    path varchar(300),
    document_id int,
    FOREIGN KEY (document_id) REFERENCES storable(id)
);

CREATE TABLE if not exists editors(
    user_id int,
    storable_id int,
    FOREIGN KEY (user_id) REFERENCES "user"(id),
    FOREIGN KEY (storable_id) REFERENCES storable(id)
);

CREATE TABLE if not exists moderators(
    user_id int,
    storable_id int,
    FOREIGN KEY (user_id) REFERENCES "user"(id),
    FOREIGN KEY (storable_id) REFERENCES storable(id)
);

CREATE TABLE if not exists readers(
    user_id int,
    storable_id int,
    FOREIGN KEY (user_id) REFERENCES "user"(id),
    FOREIGN KEY (storable_id) REFERENCES storable(id)
);

CREATE TABLE if not exists mail_configuration(
    work_status boolean
);

INSERT INTO "user" (login, password, email, admin) VALUES ('admin', 'admin', 'admin@mail.ru', true);
INSERT INTO "user" (login, password, email, admin) VALUES ('ivan', 'ivan', 'ivanov@mail.ru', false);
INSERT INTO "user" (login, password, email, admin) VALUES ('petrov', 'petrov', 'petrov@mail.ru', false);

INSERT INTO document_types (type_name) VALUES ('Fax');
INSERT INTO document_types (type_name) VALUES ('Order');
INSERT INTO document_types (type_name) VALUES ('Topic');

INSERT INTO storable (parent_id, name, author_id, type, free_access, status,
                      creation_DT, description, priority, document_type, ancestor_id)
VALUES (null, 'About cats', 2, 'DOCUMENT', true, 'CURRENT', CURRENT_TIMESTAMP,
        'Topic about cats', 'LOW', 1, null);

INSERT INTO storable (parent_id, name, author_id, type, free_access, status,
                      creation_DT, description, priority, document_type, ancestor_id)
VALUES (null, 'Animals', 2, 'DIRECTORY', true, 'CURRENT', CURRENT_TIMESTAMP,
        null, null, null, null);

INSERT INTO moderators (user_id, storable_id) VALUES (2, 1);
INSERT INTO editors (user_id, storable_id) VALUES (2, 1);
INSERT INTO readers (user_id, storable_id) VALUES (2, 1);
INSERT INTO moderators (user_id, storable_id) VALUES (2, 2);
INSERT INTO editors (user_id, storable_id) VALUES (2, 2);
INSERT INTO readers (user_id, storable_id) VALUES (2, 2);

INSERT INTO storable (parent_id, name, author_id, type, free_access, status,
                      creation_DT, description, priority, document_type, ancestor_id)
VALUES (2, 'Cats', 2, 'DIRECTORY', true, 'CURRENT', CURRENT_TIMESTAMP,
        null, null, null, null);

INSERT INTO moderators (user_id, storable_id) VALUES (2, 3);
INSERT INTO editors (user_id, storable_id) VALUES (2, 3);
INSERT INTO readers (user_id, storable_id) VALUES (2, 3);
INSERT INTO moderators (user_id, storable_id) VALUES (2, 3);
INSERT INTO editors (user_id, storable_id) VALUES (2, 3);
INSERT INTO readers (user_id, storable_id) VALUES (2, 3);

INSERT INTO storable (parent_id, name, author_id, type, free_access, status,
                      creation_DT, description, priority, document_type, ancestor_id)
VALUES (3, 'Cats in Ancient Egypt', 2, 'DOCUMENT', true, 'CURRENT', CURRENT_TIMESTAMP,
        'Topic about cats in Ancient Egypt', 'LOW', 1, null);

INSERT INTO file (name, type, path, document_id) VALUES ('Black_cat', 'png', 'C:\Documents\Cats', 4);

INSERT INTO moderators (user_id, storable_id) VALUES (2, 4);
INSERT INTO editors (user_id, storable_id) VALUES (2, 4);
INSERT INTO readers (user_id, storable_id) VALUES (2, 4);
INSERT INTO moderators (user_id, storable_id) VALUES (2, 4);
INSERT INTO editors (user_id, storable_id) VALUES (2, 4);
INSERT INTO readers (user_id, storable_id) VALUES (2, 4);

INSERT INTO mail_configuration (work_status) VALUES (true);

