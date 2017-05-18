
create table `user`
(
	`user_id` int not null auto_increment,
	`username` varchar(50) not null,
	`password` varchar(50) not null,
	`email` varchar(100),
	`phone` varchar(20),
	
	`create_time` datetime not null default now(),
    `update_time` datetime not null default now(),
	
	`status` tinyint not null default 1,	#1������-1ɾ��
    
    constraint pk_user primary key(user_id)
);


create table `file`
(
    `file_id` int not null auto_increment,
    `parent_id` int not null default 0,
    `file_name` nvarchar(100) not null,
    `file_path` nvarchar(200) not null,
    
    `file_type` tinyint not null,	#1,�ļ���
    `suffix` nvarchar(50),
    
    `create_time` datetime not null default now(),
    `update_time` datetime not null default now(),
    
    `status` tinyint not null default 1,	#1�¼ӣ�2������-1ɾ��
	`user_id` int,
    
    constraint pk_file primary key(file_id)
);

create table `file_history`
(
    `file_history_id` int not null auto_increment,
    `file_id` int not null,
    `parent_id` int not null,
    `file_name` nvarchar(100) not null,
    `file_path` nvarchar(200) not null,
    
    `file_type` tinyint not null,
    `suffix` nvarchar(50),
    
    `create_time` datetime not null default now(),
    
    `operate_type` tinyint not null,	#1��ӣ�2���£�3ɾ��
	`user_id` int,
    
    constraint pk_file_history primary key(file_history_id)
);