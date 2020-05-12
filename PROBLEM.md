# Goldy's Gym - Part 2 

## Problem Statement

Goldy's Gym has started business in 2018 as a specialized fitness center. It started operations from a 200 sqm floor area in the suburbs of Boston. Because of its uniquely designed fitness regimes, it was able to quickly raise memberships and currently operates with nine centers and aggressively planning to open multiple centers across the country.
Goldy's Gym wants to digitize its operation in a phased manner. They have already has an application which has the following features: 

- The system allows integration with other fitness apps and portals as well as with their own Single Page Application (SPA) as it is based on the standard REST 2.0 API. 

- As the system is predicted to be highly evolvable based on their business requirements. Therefore, they have built the system on the Microservices architecture.

- The Gym Admin team at Goldy's Gym can add the fitness programs they offer, remove any old program details from the portal or even update the program details through the system.

- Third Party fitness portals, with whom Goldy's gym has tied up, are able to see the list of fitness programs available, select the details of a particular program and share their interest for it so that the marketing team can get in touch with them to resolve their queries and proceed with the payment.

- Users who visits Goldy's Gym Web application, are able to register themselves and login to the application, find the current offerings, view the details of it, and finally share their interest for the same.  

- The Marketing team are able to see the list of users who have shared their interest for the fitness programs, call them back and close the ticket with their remarks.

- All layers of the application are tested by writing automated test cases.

- To reduce redundant calls to the database, the Development Team has implemented caching at the service layer for reducing the load as well as improving the response time of queries.

- To make the API robust, the Development Team has handled the exceptions and errors that can occur in the application.

- Logs are essential data that an application generates during its lifetime. Logs provide invaluable insights on how the application is working. Hence, logging have been implemented using AOP and SL implemented and aggregated.

- Considering that the API will be integrating with multiple Third Party fitness apps, it is important to ensure compatibility and hence, at least two versions of the API needs to be created(for listing and checking details only), one with minimal information and fields(ideal for mobile apps) and the other with more detailed information,which has been implemented for Gym offerings.

The problem to solve for Goldy's Gym is make the application more robust, manageable and secure and reducing the interdependencies among the microservices.


## Proposed Solution
In this phase, Goldy's Gym needs a solution for their marketing campaign. 
The solution should help to reach out to users over the web and gather enquiry to its offered programs while ensuring security and high availability. 


## High Level Requirements

As the application is created based on Microservices Architecture, hence as of now each one of them can have a non-standardized authentication and authorization mechanism and also it needs to showcase the direct addresses of each microservices to the third party APIs or even SPA front end applications. This makes the microservices bound to a specific address and port number. Also, in case there are multiple instances of the same microservices, then routing the request will not be possible. Hence, an API gateway is needed which will intercept all requests and will route them to the appropriate microservice instance. Also, the JWT based authentication logic will have to be implemented only in the gateway only and all microservice specific implementation will have to be removed. 

The API gateway will be querying the service registry for service discovery to find the instances of the required microservices at runtime, as all of the microservices will be registered with the service registry upon getting started.

As there are multiple microservices, hence managing the configurations of each microservices will be a tedious task. This will get even more complicated when more services will get added to the application. Hence, a centralized repository of microservice configuration can be created by using **Spring Cloud Config**. This will run as an individual microservice and all other microservices will query this microservice on startup to get the details of their configuration which includes environment variables and other configurations. This can also be used to created various profiles for the application for various environment such as dev, staging and production. 

as of now, the EnquiryService allows the following tasks:

- adding a new enquiry (done by a potential customer)
- seeing the list of the enquiries( by customer care executives, managers)
- updating an enquiry with remarks and status.

However, this strategy does not give the managers at the call centres an overall view about how many cases are open and who is assigned to them and productivity calculation is impacted. Hence, in this step, EnquiryService will be broken further into one more microservice - TicketService. Each time a customer puts an enquiry, a new ticket will have to be generated. When a call centre executive will address it and put the remarks, his login ID will be captured. to break the tight coupling between these two microservices, it will be event driven through the use of RabbitMQ. Each time a new enquiry is generated, the ticket will have to be generated automatically. 

To ensure that the system captures only valid login IDs and also to check whether the role is `Executive` while the call centre executives update the status, the email ID entered will be validated against UserService. In case the UserService is not available, a default value will be entered. Circuit breaker pattern is going to be ideal in this case.

To ensure high availability and load balancing, we need to create a cluster of the microservice instances and manage the load between them.

The application needs a distributed tracing system. It helps gather timing data needed to troubleshoot latency problems in service architectures. It will enable features including both the collection and lookup of this data.


### There would be three roles in this application. They would be allowed to perform activities based on their role: 
  - **Customer**: View details of the fitness programs and show interest by providing contact details
  - **Executive**: View details of the fitness programs, customers who has shown interest, working on these tickets, and close the same
  - **GymAdmin**: Manage fitness programs

## Microservices

- **UserService**: Responsible for managing user accounts. (Base code already shared)
- **GymService**: Responsible for storing, retrieving, and deleting fitness programs as well as see the details of the same. (Base code already shared)
- **EnquiryService**: Responsible for storing details of customers who have shown interest.

- **TicketService**: Responsible for generating ticket for each enquiry for the marketing team(Customer Care Executives) to act, add their remarks on it, and close the tickets.

- **ConfigService**: Responsible for working as a centralized configuration server.

- **EurekaService**: This will be the service registry with which all the microservice instances will get registered.

- **ApiGatewayService**: Responsible for working a gateway for all requests for all microservices and to authenticate the user(if required).


## Tech Stack

- Java 11
- Spring Boot 
- Spring Framework 5
- MySQL
- MongoDB
- JUnit 5 and Mockito
- RabbitMQ
- Logback
- JWT
- Netflix Eureka
- Netflix Zuul
- Feign
- Zipkin
- Hystrix
- AWS Services



## Important Information

To avoid local installation, we will use dockerized versions of Zipkin and RabbitMQ. Please use the following commands to start the containers:

### RabbitMQ

``` docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management```


### Zipkin

``` docker run -d -p 9411:9411 openzipkin/zipkin ```