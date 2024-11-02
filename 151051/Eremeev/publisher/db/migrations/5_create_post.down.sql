CREATE TABLE IF NOT EXISTS tbl_post(
   id serial PRIMARY KEY,
   issue_id integer NOT NULL,
   content VARCHAR (2048) NOT NULL,

   FOREIGN KEY (issue_id) REFERENCES tbl_issue(id)
);
