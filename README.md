# Простое Spring приложение с использованием CI/CD, проверки покрытия кода тестами и мониторинга

Проект для закрепления материала по CI/CD, полностью покрытый тестами с мониторингом.


![Static Badge](https://img.shields.io/badge/java-17-green)
![Static Badge](https://img.shields.io/badge/maven-3.8-green)
![Static Badge](https://img.shields.io/badge/spring-3.2.6-green)
![Static Badge](https://img.shields.io/badge/postgres-15-green)
![Static Badge](https://img.shields.io/badge/jacoco-0.8-green)
![Static Badge](https://img.shields.io/badge/openapi-3-green)
![Static Badge](https://img.shields.io/badge/sonar-latest-green)
![Static Badge](https://img.shields.io/badge/grafana-latest-green)
![Static Badge](https://img.shields.io/badge/prometheus-latest-green)

![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/waterwa1ker/GithubActions/github-ci-cd.yml)
![GitHub commit activity](https://img.shields.io/github/commit-activity/m/waterwa1ker/GithubActions)
![Docker Image Size](https://img.shields.io/docker/image-size/adelibragimov/java-backend)
![GitHub License](https://img.shields.io/github/license/waterwa1ker/GithubActions)



## Требования

* Open JDK 17
* Apache Maven 3.8

## Запуск 

Перед запуском необходимо **настроить переменные окружения** в .env.

### Docker Compose

Сначала скомпилируем наше приложение в .jar для построение образа нашего приложения:

```bash
mvn clean package
```

Для запуска приложения с помощью Docker:

```bash
docker compose up -d
```

## Routes

![openapi-screen](./images/openapi-screen.png)

Для отображения Swagger необходимо пройти по **localhost:8080/swagger-ui/index.html**.

## Тестирование

Все тесты находятся в src/test. Тестирование с помощью JUnit и Mockito. Тестируются только **service** и **controller** пакеты.

Для **запуска** тестов:

```bash
mvn clean test
```

Для отображения покрытия тестов с помощью JaCoCo:

```bash
mvn clean verify jacoco:report
open target/site/jacoco/index.html
```

![jacoco-screen](./images/jacoco-screen.png)

Для более красивого и подробного покрытия кода используется SonarQube:

```bash
mvn sonar:sonar
```

SonarQube находится на localhost:9000. Необходимо авторизоваться в сервисе:

**login**=admin

**password**=admin

После этого вас попросят поменять пароль.

![sonar-screen](./images/sonar-screen.png)

## CI/CD

Всю информацию о джобах можно найти в .github/workflows.

При пуше в *develop* запускаются стандартные проверки джобы на build, test и coverage. Coverage должен составлять **не менее 80%**. Этот параметр можно поменять в pom.xml.

При пуше в *release* ко всем джобам выше добавляется deploy на dockerhub. В Github Actions **необходимо настроить секреты** DOCKERHUB_USERNAME, DOCKERHUB_TOKEN для успешного деплоя.

## Мониторинг

## Автор

[Ибрагимов Адель](https://t.me/LifeLikeBoxOfChocolates)
