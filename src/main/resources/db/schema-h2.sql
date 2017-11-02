CREATE TABLE sys_user(  
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  version BIGINT COMMENT '版本',
  username VARCHAR(50) COMMENT '用户名',
  password VARCHAR(64) COMMENT '密码',
  name VARCHAR(100) COMMENT '姓名',
  mobile VARCHAR(11) COMMENT '电话',
  email VARCHAR(50) COMMENT '邮箱',
  status TINYINT(1) COMMENT '状态',
  creator VARCHAR(50) COMMENT '创建人',
  create_time DATETIME COMMENT '创建时间',
  last_modifier VARCHAR(50) COMMENT '最后修改人',
  last_modify_time DATETIME COMMENT '最后修改时间',
  PRIMARY KEY (id)
);
