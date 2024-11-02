CREATE TABLE IF NOT EXISTS tbl_issue(
   id serial PRIMARY KEY,
   editor_id integer NOT NULL,
   title VARCHAR (64) UNIQUE NOT NULL,
   content VARCHAR (2048) NOT NULL,
   created timestamp,
   modified timestamp,

   FOREIGN KEY (editor_id) REFERENCES tbl_editor(id)
);
