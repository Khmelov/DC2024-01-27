CREATE TABLE IF NOT EXISTS tbl_editor(
   id serial PRIMARY KEY,
   login VARCHAR (64) UNIQUE NOT NULL,
   password VARCHAR (256) NOT NULL,
   firstname VARCHAR (64) NOT NULL,
   lastname VARCHAR (64) NOT NULL
);
