# Charter communications - Programming Assignment By ISHOWR UPRETI

## Getting Started

- Java version 11 or greater is required.
- Recommend installing latest version of Maven.

1. Clone this repo
2. Execute Following Command and Request from post man from insomnia in order to get output:
   
    - Use `mvn spring-boot:run` from terminal to run the app.
    - Hit `POST` request on url   `localhost:8080/api/v1/customers` with payload:`
      [
      {
      "name": "Ishwor-1"
      },
      {
      "name": "Ishwor-2"
      },
      {
      "name": "Ishwor-3"
      },
      {
      "name": "Ishwor-4"
      }]`
    - hit `GET` Request on : `localhost:8080/api/v1/customers`
    - hit `POST` request on URL: `localhost:8080/api/v1/customers/1/purchases` with payload like:`[
       {
       "amount": 120.00,
       "timestamp": "2022-10-05T02:41:37.909240026Z"
       }]` where `1` on path variable denotes `customer_id` we can add multiple  purchase with following payload:
      `{
      "amount": 200.00,
      "timestamp": "2022-12-14T02:41:37.909240026Z"
      },
      {
      "amount": 150.00,
      "timestamp": "2023-01-02T02:41:37.909240026Z"
      },
      {
      "amount": 170.00,
      "timestamp": "2022-01-07T02:41:37.909240026Z"
      }` for the targeted customer id as path variable.
    -  To get All reward Points or customer specific reward point can hit `GET` request on following URL:
       `localhost:8080/api/v1/rewards` or `localhost:8080/api/v1/rewards?customerId=1`.
       

## Built With
* [Maven](https://maven.apache.org/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.1/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.1/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [JUnit5](https://junit.org/junit5/)
* [mockito](https://site.mockito.org/)


