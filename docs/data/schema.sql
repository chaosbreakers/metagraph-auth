/*
Navicat MySQL Data Transfer

Source Server         : local-mysql
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : metagraph

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-05-23 16:29:19
*/


CREATE TABLE IF NOT EXISTS users (
  id                 BIGINT(20)   NOT NULL AUTO_INCREMENT,
  username           VARCHAR(100) NOT NULL,
  password           VARCHAR(255),
  enabled            BIT(1)                DEFAULT FALSE,
  create_time timestamp NULL DEFAULT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE INDEX username_index (username),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS authorities (
  id                 BIGINT(20)   NOT NULL AUTO_INCREMENT,
  username           VARCHAR(100) NOT NULL,
  authority          VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS groups (
  id                 BIGINT(20)   NOT NULL AUTO_INCREMENT,
  group_name         VARCHAR(100) NOT NULL,
  UNIQUE INDEX group_name_index (group_name),
  create_time timestamp NULL DEFAULT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS group_members (
  id                 BIGINT(20)   NOT NULL AUTO_INCREMENT,
  group_id           BIGINT(20),
  username           VARCHAR(100) NOT NULL,
  UNIQUE INDEX g_u_index (group_id, username),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS group_authorities (
  id                 BIGINT(20)   NOT NULL AUTO_INCREMENT,
  group_id           BIGINT(20),
  authority          VARCHAR(100) NOT NULL,
  UNIQUE INDEX g_a_index (group_id, authority),
  PRIMARY KEY (id)
);


-- JdbcClientDetailsService
-- BaseClientDetails
-- insert into oauth_client_details (client_secret, resource_ids, scope,
-- authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity,
-- refresh_token_validity, additional_information, autoapprove, client_id)
CREATE TABLE IF NOT EXISTS oauth_client_details (
  client_id               VARCHAR(255) PRIMARY KEY,
  client_secret           VARCHAR(255),
  resource_ids            VARCHAR(255),
  scope                   VARCHAR(255),
  authorized_grant_types  VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities             VARCHAR(255),
  access_token_validity   INTEGER,
  refresh_token_validity  INTEGER,
  additional_information  VARCHAR(4096),
  autoapprove             VARCHAR(255),
  create_time timestamp NULL DEFAULT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS oauth_client_token (
  token_id          VARCHAR(255),
  token             BLOB,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name         VARCHAR(255),
  client_id         VARCHAR(255),
  create_time timestamp NULL DEFAULT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- JdbcTokenStore
-- insert into oauth_access_token (token_id, token, authentication_id, user_name, client_id, authentication, refresh_token)
-- Types.VARCHAR, Types.BLOB, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BLOB, Types.VARCHAR
CREATE TABLE IF NOT EXISTS oauth_access_token (
  authentication_id VARCHAR(255) PRIMARY KEY,
  token_id          VARCHAR(255),
  token             BLOB,
  authentication    BLOB,
  user_name         VARCHAR(255),
  client_id         VARCHAR(255),
  refresh_token     VARCHAR(255),
  create_time timestamp NULL DEFAULT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- JdbcTokenStore
-- insert into oauth_refresh_token (token_id, token, authentication)
-- Types.VARCHAR, Types.BLOB, Types.BLOB
CREATE TABLE IF NOT EXISTS oauth_refresh_token (
  token_id       VARCHAR(255),
  token          BLOB,
  authentication BLOB,
  create_time timestamp NULL DEFAULT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

# JdbcApprovalStore
# insert into oauth_approvals (expiresAt,status,lastModifiedAt,userId,clientId,scope) values (?,?,?,?,?,?)
CREATE TABLE IF NOT EXISTS oauth_approvals (
  userId         VARCHAR(255),
  clientId       VARCHAR(255),
  scope          VARCHAR(255),
  status         VARCHAR(10),
  expiresAt      TIMESTAMP,
  lastModifiedAt TIMESTAMP
);

# JdbcAuthorizationCodeServices
CREATE TABLE IF NOT EXISTS oauth_code (
  code           VARCHAR(255),
  authentication BLOB,
  create_time timestamp NULL DEFAULT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
