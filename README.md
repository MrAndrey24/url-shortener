# url-shortening-service

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

A high-performance, cloud-native RESTful API for shortening long URLs, tracking link access metrics, and managing shortened links. Built with **Quarkus**, **Java 21**, and **Hibernate ORM with Panache**.

Inspired by the [roadmap.sh URL Shortening Service Project](https://roadmap.sh/projects/url-shortening-service).

---

## ✨ Features

- **URL Shortening**: Generates unique, short codes for long URLs with duplicate collision checks.
- **Analytics & Tracking**: Records redirect count (`accessCount`) and tracks timestamps (`createdAt`, `updatedAt`).
- **RESTful Endpoints**: Full CRUD capabilities for URL management.
- **Data Validation**: Request payload verification via Jakarta Validation annotations.
- **In-Memory Storage**: Zero-configuration setup using an H2 in-memory database.

---

## 🛠️ Tech Stack

- **Java 21 & JDK 21**
- **Quarkus** (RESTEasy Reactive, Jackson)
- **Hibernate ORM with Panache** (Active Record Pattern)
- **H2 Database** (In-Memory)
- **Jakarta Validation**


## 🧪 Testing the API with Swagger UI

When running the application in development mode (`./mvnw quarkus:dev`), you can test all API endpoints interactively via Swagger UI:

👉 **Swagger UI Interface:** <http://localhost:8080/q/swagger-ui/>

*(OpenAPI specification JSON is also generated automatically at <http://localhost:8080/q/openapi>).*

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory. Be aware that it’s not an _über-jar_ as
the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/url-shortening-service-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.
