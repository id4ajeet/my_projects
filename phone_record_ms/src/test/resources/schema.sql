create table phone_book (
	number bigint constraint phone_book_pk primary key,
	name varchar,
	email varchar
);

create table call_data
(
	id serial constraint call_data_pk primary key,
	uuid varchar,
	domain_name varchar,
	caller_name varchar,
	caller_number bigint,
	destination_number bigint,
	direction varchar,
	call_start varchar,
	ring_start varchar,
	answer_start varchar,
	call_end varchar,
	duration int,
	recording varchar,
	click_to_call bool,
	click_to_call_data varchar,
	action varchar
);

CREATE SEQUENCE call_data_id_seq
  START WITH 1
  INCREMENT BY 1;

create table sync_data (
	id serial constraint sync_data_pk primary key,
	sync_date date
);

CREATE SEQUENCE sync_data_id_seq
  START WITH 1
  INCREMENT BY 1;

CREATE SEQUENCE hibernate_sequence
  START WITH 1
  INCREMENT BY 1;

-- insert first record
INSERT into sync_data (sync_date) VALUES (current_timestamp);

CREATE OR REPLACE VIEW cdr_report AS
select uuid, sum(HANGUP)/60 as successful_calls_minutes, sum(ANSWERED) as answered_call_count, sum(UNMATCHED) as unmatched_call_count, sum(TOTAL_CALL) as total_call
from (
         select uuid, duration as HANGUP, 0 as ANSWERED, 0 as UNMATCHED, 1 as TOTAL_CALL
         from call_data
         where action = 'HANGUP'

         union

         select uuid, 0 as HANGUP,  1 as ANSWERED, 0 as UNMATCHED, 1 as TOTAL_CALL
         from call_data
         where action = 'ANSWERED'

         union

         select uuid, 0 as HANGUP,  0 as ANSWERED, 1 as UNMATCHED, 1 as TOTAL_CALL
         from call_data
         where action in ('RING','ORIGINATE')
     ) as c group by c.uuid;
