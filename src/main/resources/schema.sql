CREATE TABLE writer_info (
  id      INTEGER PRIMARY KEY,
  name    VARCHAR(64) NOT NULL,
  email   VARCHAR(64) NOT NULL
);

CREATE TABLE novel_info (
  id          INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  writer_id    INTEGER NOT NULL references writer_info(id),
  novel_name   VARCHAR(64) NOT NULL,
  novel_type   VARCHAR(64) NOT NULL,
  novel_status   VARCHAR(64) NOT NULL,
  novel_desc   VARCHAR(64) NOT NULL
);

