# Goldy's Gym - Part 2

# See PROBLEM.md for requirements.

## Objective

The Objective of this assignment are the following:
- Perform routing with API Gateway
- Use configuration server to centralize distributed configurations
- Use service registry for service discovery
- Create cluster of service instances and manage load between them
- Add fault tolerance using circuit breakers
- Perform asynchronous event-driven messaging across distributed services
- Securing Microservices
- Perform Logging and Log aggregation in Microservices

#### To use this as a boilerplate for your new project, you can follow these steps

1. Clone the base boilerplate in the folder **<ASSIGNMENT-NAME-PLACEHOLDER>** of your local machine
     
    `git clone <REPO-URL>`

2. Navigate to <ASSIGNMENT-NAME-PLACEHOLDER> folder

    `cd <ASSIGNMENT-NAME-PLACEHOLDER>`

3. Remove its remote or original reference

     `git remote rm origin`

4. Create a new repo in gitlab named `<ASSIGNMENT-NAME-PLACEHOLDER>` as private repo

5. Add your new repository reference as remote

     `git remote add origin https://gitlab-<ORG-NAME>.stackroute.in/{{yourusername}}/<ASSIGNMENT-NAME-PLACEHOLDER>`

     **Note: {{yourusername}} should be replaced by your username from gitlab**

5. Check the status of your repo 
     
     `git status`

6. Use the following command to update the index using the current content found in the working tree, to prepare the content staged for the next commit.

     `git add .`
 
7. Commit and Push the project to git

     `git commit -a -m "Initial commit | or place your comments according to your need"`

     `git push -u origin master`

8. Check on the git repo online, if the files have been pushed

#### To run the application, you can follow these steps:

This application required RabbitMQ and Zipkin Server. For simplicity, we will run
them using Docker Containers. Hence, docker should be installed on the development 
environment. Hence, it is strongly suggested that the following commands should be
executed in order to get this started.

To Run RabbitMQ from Docker:
```
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

To Run OpenZipkin from Docker:
```
docker run -d -p 9411:9411 openzipkin/zipkin
```


The application consists of the following microservices:
- ConfigService (contains the externalized configuration for all microservices)
- EurekaService (works as the Service Registry using Netflix Eureka)
- ApiGatewayService (works as the API Gateway using Netflix Zuul)
- UserService (uses MySQL for data persistence)
- GymService (uses MongoDB for data persistence)
- EnquiryService (uses MongoDB for data persistence and RabbitMQ to generate message)
- TicketService (uses MongoDB for data persistence and RabbitMQ to retrieve message)

the steps are as follows:

1. MySQL Database URL, Username, Password and MongoDB URI are set through 
   environment variables. You need to set the following environment variables:
   - MYSQL_URL
   - MYSQL_USERNAME
   - MYSQL_PASSWORD
   - MONGO_URI
   
   In Linux environment, you can set the environment variables using the following commands:
   
   ```
   export MYSQL_URL=jdbc:mysql://localhost:3306/userdb
   export MYSQL_USERNAME=root
   export MYSQL_PASSWORD=password
   export MONGO_URI=mongodb://localhost:27017/goldysgym
   ```
`Note:`
 the values are indicative only, which should be replaced by correct value as per
 the environment being used.

To run the application, you will need to start the individual microservices. 

To Start `ConfigService`:

- ```cd ConfigService```

- ```mvn spring-boot:run```

To Start `EurekaService`:

- ```cd EurekaService```

- ```mvn spring-boot:run```

To Start `ApiGatewayService`:

- ```cd ApiGatewayService```

- ```mvn spring-boot:run```

To Start `UserService`:

- ```cd UserService```

- ```mvn spring-boot:run```


To Start `GymService`:

- ```cd GymService```

- ```mvn spring-boot:run```

To Start `EnquiryService`:

- ```cd EnquiryService```

- ```mvn spring-boot:run```

To Start `TicketService`:

- ```cd TicketService```

- ```mvn spring-boot:run```




### Important instructions for Participants
> - We expect you to write the assignment on your own by following through the guidelines, learning plan, and the practice exercises
> - The code must not be plagiarized, the mentors will randomly pick the submissions and may ask you to explain the solution
> - The code must be properly indented, code structure maintained as per the boilerplate and properly commented
> - Follow through the problem statement shared with you

### MENTORS TO BEGIN REVIEW YOUR WORK ONLY AFTER ->
> - You add the respective Mentor as a Reporter/Master into your Assignment Repository
> - You have checked your Assignment on the Automated Evaluation Tool - Hobbes (Check for necessary steps in your Boilerplate - README.md file. ) and got the required score - Check with your mentor about the Score you must achieve before it is accepted for Manual Submission.
> - Intimate your Mentor on Slack and/or Send an Email to learner.support@stackroute.in - with your Git URL - Once you done working and is ready for final submission.


### Further Instructions on Release

*** Release 0.1.0 ***

- Right click on the Assignment select Run As -> spring boot app to run your Assignment.
- Right click on the Assignment select Run As -> JUnit Test to run your Assignment.
 
