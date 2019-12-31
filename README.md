# Novel-Import-System

## Funcations
> Start this project and browse 

http://localhost:8080/

> Import data file

On top-left of the page, there's a button to choose file,and you can select the import mode:
* Upsert
* Truncate

> Show data list

There's a table on the middle of the page, it shows all novel info records in the system.
And when you click one row in the table, the info shows in the form below down.

> Manipulate data

There's a form titled by "Novel Info Edit" which could do:
* Create
* Update
* Delete


## Database Schema
> writer_info table

<pre><code>
CREATE TABLE writer_info (
  id      INTEGER PRIMARY KEY,
  name    VARCHAR(64) NOT NULL,
  email   VARCHAR(64) NOT NULL
);
</code></pre>

> novel_info table
<pre><code>
CREATE TABLE novel_info (
  id          INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  writer_id    INTEGER NOT NULL references writer_info(id),
  novel_name   VARCHAR(64) NOT NULL,
  novel_type   VARCHAR(64) NOT NULL,
  novel_status   VARCHAR(64) NOT NULL,
  novel_desc   VARCHAR(64) NOT NULL
);
</code></pre>

## Assumptions
> writer info in system:

<pre><code>
INSERT INTO writer_info (id, name, email) VALUES
  (1001,'Frank1','frank1@abc.com'),
  (1002,'Frank2','frank2@abc.com'),
  (1003,'Frank3','frank3@abc.com'),
  (1004,'Frank4','frank4@abc.com'),
  (1005,'Frank5','frank5@abc.com');
</code></pre>

> just check writer_id before insert record into novel_info

> if there're some invalid records in the data file, program will ignore them.

> data file structure for import

<pre><code>
File extension: .csv
Fields separator: ,
Fields:
    id,writerId,novelName,novelType,novelStatus,novelDesc
</code></pre>
Note: "id" can be null ( to add new records, but the separator must exists )
