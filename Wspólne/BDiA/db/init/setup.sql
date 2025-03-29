DROP SCHEMA public;
CREATE SCHEMA bdia;

-- Drop users if they exist (useful for resetting)
DROP USER IF EXISTS bdia_owner;

-- Create users with login capability
CREATE USER bdia_owner WITH PASSWORD '54321' LOGIN;

ALTER DATABASE bdia OWNER TO bdia_owner;

GRANT ALL PRIVILEGES ON             DATABASE bdia TO bdia_owner;
GRANT ALL PRIVILEGES ON               SCHEMA bdia TO bdia_owner;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA bdia TO bdia_owner;

ALTER USER bdia_owner SET search_path TO bdia;
