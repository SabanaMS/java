package com.goldys.userservice.controller;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation. A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized
 * format. Starting from Spring 4 and above, we can use @RestController annotation which
 * is equivalent to using @Controller and @ResponseBody annotation
 *
 * Please note that the default path to use this controller should be "/api/v1/userservice"
 */

public class UserController {
    /*
     * Constructor Autowiring should be implemented for the Service Layer for Programs and
     * SecurityTokenGenerator. Please note that we should not create any object
     * using the new keyword. Autowiring should be also implemented for
     * UserServiceProxy interface which is used for implementing client side load
     * balancer.
     */



    /* API Version: 1.0
     * Define a handler method which will register a new user by reading the Serialized
     * User object from request body and save the user in database.
     * Please note that the email has to be unique.
     *
     * This handler method should return any one of the status messages basis on
     * different situations:
     * 1. 201(CREATED - In case of successful creation of the user
     * 2. 409(CONFLICT) - In case of duplicate email
     *
     * This handler method should map to the URL "/api/v1/userservice" using HTTP POST
     * method".
     */



    /* API Version: 1.0
     * Define a handler method which will update a specific user by reading the
     * Serialized object from request body and save the updated user in
     * user table in database and handle UserNotFoundException as well.
     *
     * This handler method should return any one of the status
     * messages basis on different situations:
     * 1. 200(OK) - If the user is updated successfully.
     * 2. 404(NOT FOUND) - If the user with specified email is not found.
     *
     * This handler method should map to the URL "/api/v1/userservice" using HTTP PUT
     * method
     */


    /* API Version: 1.0
     * Define a handler method which will authenticate a user by reading the Serialized user
     * object from request body containing the username and password. The username and password should be validated
     * before proceeding ahead with JWT token generation. The user credentials will be validated against the database entries.
     * The error should be return if validation is not successful. If credentials are validated successfully, then JWT
     * token will be generated. The token should be returned back to the caller along with the API response.
     * This handler method should return any one of the status messages basis on different
     * situations:
     * 1. 200(OK) - If the login is successful.
     * 2. 404(NOT FOUND) - If the user with specified email is not found.
     * 3. 401(UNAUTHORIZED) - If the credentials are incorrect
     *
     * This handler method should map to the URL "/api/v1/userservice/login" using HTTP POST
     * method.
     *
     */



    /* API Version: 1.0
     * Define a handler method which will validate a user by reading the email from the request
     * URL. The token should be returned back to the caller along with the API response.
     * This handler method should return the following status message:
     * 1. 200(OK) - both in case the authorization is successful or failure.
     *
     * This handler method should map to the URL "/api/v1/userservice/{email}" using HTTP GET
     * method where email needs to be replaced with user email.
     * This handler method should call validateUser() method of UserService, which
     * will return true if the user authorization is successful, otherwise, it should
     * return false.
     *
     */


}
