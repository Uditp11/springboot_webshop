# springboot_webshop

Coursework for the M.Sc. Distributed Applications course at Hochschule Fulda. It is a small web shop that grew over the weekly exercises.

## Stack

From `pom.xml` and `my-svelte-frontend/package.json`:

- Java 23 and Spring Boot 3.4.0
- Spring Web, Spring Data JPA and the Spring Data JDBC starter
- H2 in-memory database
- Thymeleaf for the server-rendered pages
- Svelte 5 with Vite for the small client in `my-svelte-frontend/`

## What the exercises cover

- A product catalogue stored in H2 through a JPA repository and seeded with ten products at startup (`config/LoadProductDatabase.java`). It supports search by name, filter by color and a paginated view with three products per page.
- Thymeleaf pages to list, add, view and delete products (`/catalog`, `/catalog-paginated`, `/products/new`, `/products/{id}`).
- JSON endpoints for products (`/products`) and users (`/users`). Users are kept in a list in memory.
- A shopping cart that checks stock against an in-memory inventory, converts between EUR and USD at fixed rates, and applies a percentage voucher set in `application.properties`.
- Facade classes in `Facade/` that sit between the controllers and the services, and an `OrderAdapter` that finalises an order and calls a simulated e-mail service on checkout.
- A JSON catalogue endpoint at `/saas/catalog` for the Svelte client, with CORS allowed for `http://localhost:5173`. An `ApiKeyFilter` checks the `X-API-KEY` header on paths that start with `/SaaS`.

## How to run

You need JDK 23, the version set in `pom.xml`.

```
./mvnw spring-boot:run
```

On Windows use `mvnw.cmd spring-boot:run`. The shop starts at http://localhost:8080/ and the H2 console is at http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:testdb`, user `sa`, empty password).

To run the tests:

```
./mvnw test
```

To start the Svelte client while the shop is running:

```
cd my-svelte-frontend
npm install
npm run dev
```
