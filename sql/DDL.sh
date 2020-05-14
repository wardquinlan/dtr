#!/bin/sh

DATABASE=dtr
USER=dtr
psql --username=$USER $DATABASE << EOF

DROP TABLE IF EXISTS SERIES_DATA;

CREATE TABLE SERIES_DATA(
	SERIES_ID VARCHAR(50) PRIMARY KEY,
	DATESTAMP DATE NOT NULL,
	IDX INTEGER); -- 0..78
EOF
