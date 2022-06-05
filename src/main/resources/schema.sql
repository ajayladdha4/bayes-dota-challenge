DROP TABLE IF EXISTS dim_buy_events;
CREATE TABLE dim_buy_events(
	id bigint auto_increment,
	match_id bigint,
	timestamp varchar(20),
	hero varchar(100),
	item varchar(50)
);

DROP TABLE IF EXISTS dim_killed_events;
CREATE TABLE dim_killed_events(
	id bigint auto_increment,
	match_id bigint,
	timestamp varchar(20),
	hero varchar(100),
	killed_hero varchar(100)
);

DROP TABLE IF EXISTS dim_spells_events;
CREATE TABLE dim_spells_events(
	id bigint auto_increment,
	match_id bigint,
	timestamp varchar(20),
	hero varchar(100),
	spell varchar(100),
	casted_on_hero varchar(100),
	level int
);

DROP TABLE IF EXISTS dim_damage_events;
CREATE TABLE dim_damage_events(
	id bigint auto_increment,
	match_id bigint,
	timestamp varchar(20),
	hero varchar(100),
	damaged_hero varchar(100),
	damaged_weapon varchar(100),
	damage_score int,
	score_before_damage int,
	score_after_damage int
);

DROP TABLE IF EXISTS dim_ingest_match;
CREATE TABLE dim_ingest_match(
	id bigint auto_increment,
	timestamp varchar(200),
	file_content varchar(200),
	valid  int,
	invalid  int,
	not_intrested int,
	status varchar(100),
	error varchar(2000)
);