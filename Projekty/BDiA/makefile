# DOCKER
DC=docker-compose

up:
	@echo "Starting docker containers..."
	@$(DC) up --remove-orphans -d

up-main:
	@echo "Starting main PostgreSQL..."
	@$(DC) up -d main-postgres

down:
	@echo "Stopping all containers..."
	@$(DC) down --remove-orphans

down-main:
	@echo "Stopping main PostgreSQL..."
	@$(DC) stop main-postgres

logs-main:
	@$(DC) logs -f main-postgres

ps:
	@$(DC) ps

docker-clean:
	@echo "Removing all containers and volumes..."
	@$(DC) down -v
	@sudo rm -rf ${HOME}/.docker-volumes/2025-letni-bdia

psql-main:
	@docker exec -it 2025-letni-bdia-main-postgres psql -U postgres -d bdia
