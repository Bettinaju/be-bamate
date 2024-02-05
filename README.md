# BaMate Backend

This repository contains BaMate's backend: a REST API build with Java Spring Boot and JPA.
All data is persisted in a PostgreSQL database, which is provided as a local Docker Container.

## How to start BaMate Backend locally
#### Requirements
- Java 17
- Docker and [Docker Compose](https://docs.docker.com/compose/install/) (both included in the normal [Docker Desktop](https://docs.docker.com/desktop/) installation)
- IntelliJ (or a comparable IDE that supports Java, Maven and Spring)
- Functioning Command Line Interface (i.e. the Terminal)
#### Database Docker Container
If you are already comfortable with Docker, execute the `bamate-backend/docker-compose.yml` file using your preferred environment.
Otherwise, follow these steps:
1. Make sure the Docker Daemon is running (e.g. by starting Docker Desktop)
2. Open your terminal and navigate to the repository's root `cd bamate-backend`
3. Spin up the database container using `docker compose up`
4. Wait a few minutes for the build to finish (you can see the container status in your open Terminal or Docker Desktop's Dashboard)

#### Local Spring Boot
- Just start up the service using your IDE's default run configuration
  - Within IntelliJ, this can be done using the provided default `Spring Boot` run configuration


## API Details
- The spring service runs on [`localhost:5431`](http://localhost:5431)
- All endpoints are documented in the Swagger Docs at 
[`localhost:5431/swagger-ui/index.html`](http://localhost:5431/swagger-ui/index.html)
- All routes except `/auth` and doc-specific endpoints are JWT-protected
- Swapping out the database instance (i.e. for a remotely hosted one) can be done by swapping out the datasource under
`src/main/resources/application.properties`

## Database Details
- The `docker-compose.yml` file specifies all parameters and environment variables for the local database:
  - The container is built from the official PostgreSQL image in version 12
  - It is using a persistent volume called `bamate-data`
  - The username and password are set to `bamate`, the database is named `bamate-db`
  - It is running on port 5432 and also forwarded to `localhost:5432`
- To inspect the database directly, you can connect to `localhost:5432/bamate-db` using either [PGadmin](https://www.pgadmin.org/) 
or [IntelliJ's data source tool](https://www.jetbrains.com/help/idea/connecting-to-a-database.html) 