-- Create schema
CREATE SCHEMA IF NOT EXISTS project;

-- Create sequence for roles (start from 6 to allow 5 predefined inserts)
CREATE SEQUENCE IF NOT EXISTS project.roles_seq START WITH 6 INCREMENT BY 1;

-- Create table: roles
CREATE TABLE project.roles (
    id INTEGER PRIMARY KEY DEFAULT nextval('project.roles_seq'),
    name VARCHAR(255)
);

-- Insert predefined roles
INSERT INTO project.roles (id, name) VALUES
 (1, 'ADMIN'),
 (2, 'USER'),
 (3, 'JUNIOR'),
 (4, 'SENIOR'),
 (5, 'CLIENT');

-- Create table: users
CREATE TABLE project.users (
   id UUID PRIMARY KEY,
   family_id UUID,
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   email VARCHAR(255),
   password VARCHAR(255)
);

-- Create join table: user_role
CREATE TABLE project.user_role (
   user_id UUID NOT NULL,
   role_id INTEGER NOT NULL,
   PRIMARY KEY (user_id, role_id),
   FOREIGN KEY (user_id) REFERENCES project.users(id) ON DELETE CASCADE,
   FOREIGN KEY (role_id) REFERENCES project.roles(id) ON DELETE CASCADE
);

-- Create table: tasks
CREATE TABLE project.tasks (
   id UUID PRIMARY KEY,
   title VARCHAR(255) NOT NULL,
   description TEXT NOT NULL,
   deadline DATE NOT NULL,
   status VARCHAR(255) NOT NULL,
   creator_id UUID NOT NULL,
   assignee_id UUID,
   FOREIGN KEY (creator_id) REFERENCES project.users(id) ON DELETE CASCADE,
   FOREIGN KEY (assignee_id) REFERENCES project.users(id) ON DELETE SET NULL
);
