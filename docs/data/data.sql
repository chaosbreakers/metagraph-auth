INSERT INTO users (username, password, enabled)
VALUES
  ('admin', 'b8f57d6d6ec0a60dfe2e20182d4615b12e321cad9e2979e0b9f81e0d6eda78ad9b6dcfe53e4e22d1', TRUE),
  ('user', 'd6dfa9ff45e03b161e7f680f35d90d5ef51d243c2a8285aa7e11247bc2c92acde0c2bb626b1fac74', TRUE);

INSERT INTO authorities (username, authority)
VALUES
  ('admin', 'ROLE_ADMIN'),
  ('admin', 'ROLE_USER'),
  ('user','ROLE_USER');

INSERT INTO oauth_client_details (client_id, client_secret, authorized_grant_types, authorities, scope, resource_ids, access_token_validity)
VALUES ('myClientId', 'myClientSecret', 'password,implicit,authorization_code,client_credentials,refresh_token',
        'ROLE_ADMIN,ROLE_USER', 'read,write', 'oauth2-resource', '1800');

INSERT INTO oauth_client_details (client_id, resource_ids, scope, authorized_grant_types, authorities, access_token_validity)
VALUES
  ('my-trusted-client', 'oauth2-resource', 'read,write,trust', 'password,authorization_code,refresh_token,implicit',
   'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 60);

INSERT INTO oauth_client_details (client_id, resource_ids, scope, authorized_grant_types, authorities, web_server_redirect_uri)
VALUES ('my-client-with-registered-redirect', 'oauth2-resource', 'read,trust', 'authorization_code', 'ROLE_CLIENT',
        'http://anywhere?key=value');

INSERT INTO oauth_client_details (client_id, client_secret, resource_ids, scope, authorized_grant_types, authorities)
VALUES ('my-client-with-secret', 'secret', 'oauth2-resource', 'read', 'password,client_credentials', 'ROLE_CLIENT');
