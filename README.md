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

### Important instructions for Participants
> - We expect you to write the assignment on your own by following through the guidelines, learning plan, and the practice exercises
> - The code must not be plagirized, the mentors will randomly pick the submissions and may ask you to explain the solution
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
 
