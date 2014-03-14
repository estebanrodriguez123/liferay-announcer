create table rivetlogic_ann_Completed (
	USER_ID VARCHAR(75) not null,
	LAYOUT_PK VARCHAR(75) not null,
	ARTICLES_SET_ID VARCHAR(75) null,
	primary key (USER_ID, LAYOUT_PK)
);

create table rivetlogic_ann_NotCompleted (
	USER_ID VARCHAR(75) not null,
	LAYOUT_PK VARCHAR(75) not null,
	PANEL_CLOSE_DATE DATE null,
	primary key (USER_ID, LAYOUT_PK)
);