CREATE TABLE IF NOT EXISTS tbl_post (
   id bigint,
   issue_id bigint,
   content text,
   state text,
   PRIMARY KEY (id, issue_id)
);
