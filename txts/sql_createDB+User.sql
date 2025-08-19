CREATE DATABASE issue_tracker;

CREATE USER issue_user WITH PASSWORD 'whatever.ogiZwJKhM8Pii76T';
GRANT ALL PRIVILEGES ON DATABASE issue_tracker TO issue_user;

-- grant ownership of schema public to your user
ALTER SCHEMA public OWNER TO issue_user;

-- give full privileges on schema
GRANT ALL PRIVILEGES ON SCHEMA public TO issue_user;

-- also allow table creation
GRANT ALL PRIVILEGES ON DATABASE issue_tracker TO issue_user;