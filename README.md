# user-rest-service
This is user-rest-service for managing user data.
This service exposes a user signup api.  This will save the user
and create a Authentication account using the Authentication API.
This service requires a API key.

On user signup the `user-rest-service` -> (calls) `authentication-rest-service`.

## Run locally

```
mvn spring-boot:run  -Dspring-boot.run.arguments="--POSTGRES_USERNAME=dummy \
                      --POSTGRES_PASSWORD=dummy \
                      --POSTGRES_DBNAME=account \
                      --POSTGRES_SERVICE=localhost:5432
                      --apiKey=123 --DB_SSLMODE=DISABLE"
```
 
 
## Build Docker image

Build docker image using included Dockerfile.


`docker build -t ghcr.io/<username>/user-rest-service:latest .` 

## Push Docker image to repository

`docker push ghcr.io/<username>/user-rest-service:latest`

## Deploy Docker image locally

`docker run -e POSTGRES_USERNAME=dummy \
 -e POSTGRES_PASSWORD=dummy -e POSTGRES_DBNAME=account \
  -e POSTGRES_SERVICE=localhost:5432 \
 -e apiKey=123 -e DB_SSLMODE=DISABLE
 --publish 8080:8080 ghcr.io/<username>/user-rest-service:latest`


## Installation on Kubernetes
Use my Helm chart here @ [sonam-helm-chart](https://github.com/sonamsamdupkhangsar/sonam-helm-chart):

```
helm install user-rest-service sonam/mychart -f values-backend.yaml --version 0.1.15 --namespace=yournamespace
```

##Instruction for port-forwarding database pod
```
export PGMASTER=$(kubectl get pods -o jsonpath={.items..metadata.name} -l application=spilo,cluster-name=project-minimal-cluster,spilo-role=master -n yournamesapce); 
echo $PGMASTER;
kubectl port-forward $PGMASTER 6432:5432 -n backend;
```

###Login to database instruction
```
export PGPASSWORD=$(kubectl get secret <SECRET_NAME> -o 'jsonpath={.data.password}' -n backend | base64 -d);
echo $PGPASSWORD;
export PGSSLMODE=require;
psql -U <USER> -d projectdb -h localhost -p 6432

```
###Send post request to create user account
```
 curl -X POST -json '{"firstName": "dummy", "lastName": "lastnamedummy", "email": "yakApiKey", "authenticationId": "dummy123", "password": "12", "apiKey": "APIKEY"}' https://user-rest-service.sonam.cloud/signup
```

### Code Coverage
Code coverage report is generated by jacoco.  Code coverage report is uploaded to CodeCov.  

[![Coverage](.github/badges/jacoco.svg)](https://github.com/sonamsamdupkhangsar/user-rest-service/actions/workflows/deploy.yml)

### User Rest Service Interaction


```mermaid
flowchart TD
    Start --> Stop
```

```mermaid 
sequenceDiagram
    participant Alice
    participant Bob
    Alice->>John: Hello John, how are you?
    loop Healthcheck
        John->>John: Fight against hypochondria
    end
    Note right of John: Rational thoughts <br/>prevail!
    John-->>Alice: Great!
    John->>Bob: How about you?
    Bob-->>John: Jolly good!
```

```mermaid 
sequenceDiagram
    participant Client
    participant UserRestService
    participant AuthenticationRestService
    Client->>UserRestService: user signup
    loop Check unique user
        UserRestService->>UserRestService: Check email and authenticationId 
    end
    Note right of UserRestService: check user data for unique user signup
    UserRestService ->> AuthenticationRestService: register user authentication
    AuthenticationRestService ->> UserRestService: http status ok on creation
    UserRestService ->> Client: http status ok on user creation success
    UserRestService ->> dummy-rest-service: Hello man
```

### hello
```mermaid
  graph TD;
      A-->B;
      A-->C;
      B-->D;
      C-->D;
```