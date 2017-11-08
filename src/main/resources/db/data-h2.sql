insert into role(id, code, name, status, creator, create_time, last_modifier, last_modify_time)
values (1, 'admin', '超级管理员', 1, 'system', '2017-11-11 11:11:11', 'system', '2017-11-11 11:11:11');

insert into role_authority(role_id, code) values (1, 'OP_001');
insert into role_authority(role_id, code) values (1, 'OP_002');
insert into role_authority(role_id, code) values (1, 'OP_003');

insert into user(id, username, password, role_id, name, status, creator, create_time, last_modifier, last_modify_time)
values (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, 'admin', 1, 'system', '2017-11-11 11:11:11', 'system', '2017-11-11 11:11:11');
