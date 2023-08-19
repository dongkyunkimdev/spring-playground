# spring-playground
Playground for Java and Spring Framework

# Run Configuration
- flyway: `core:flywayClean core:flywayMigrate`
- test : `clean core:flywayClean core:flywayMigrate test --continue`
- all service build : `clean bootJar -x test`

# 실행하기
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) 설치
- 프로젝트 root 경로에서 ```docker-compose -f infra/docker/playground.local.yml up -d``` 명령어 실행
- Run Configuration 에서 구성한 flyway 실행