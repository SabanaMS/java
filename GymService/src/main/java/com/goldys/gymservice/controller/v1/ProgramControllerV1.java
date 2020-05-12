package com.goldys.gymservice.controller.v1;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation. A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized
 * format. Starting from Spring 4 and above, we can use @RestController annotation which
 * is equivalent to using @Controller and @ResponseBody annotation
 *
 * Please note that the default path to use this controller should be "/api/v1/gymservice"
 */
public class ProgramControllerV1 {

    /*
     * Constructor Autowiring should be implemented for the Service Layer for Programs. Please note that we
     * should not create any object using the new keyword. Autowiring should be also
     * implemented for GymServiceProxy interface which is used for implementing
     * client side load balanced.
     */




    /* API Version: 1.0
     * Define a handler method which will get us all programs.
     *
     * This handler method should return any one of the status messages basis on
     * different situations:
     * 1. 200(OK) - If all programs found successfully.
     *
     * This handler method should map to the URL "/api/v1/gymservice" using HTTP GET
     * method.
     */




    /* API Version: 1.0
     * Define a handler method which will get us the program by a programCode.
     *
     * This handler method should return any one of the status messages basis on
     * different situations:
     * 1. 200(OK) - If the program found successfully.
     * 2. 404(NOT FOUND) - If the news with specified programCode is not found.
     *
     *
     * This handler method should map to the URL "/api/v1/gymservice/{programCode}" using HTTP GET
     * method, where "programCode" should be replaced by a programCode without {}
     */




    /* API Version: 1.0
     * Define a handler method which will create a new program by reading the Serialized
     * program object from request body and save the program in database.
     * Please note that the programCode has to be unique and autogenerated.
     *
     * This handler method should return any one of the status messages basis on
     * different situations:
     * 1. 201(CREATED - In case of successful creation of the program
     * 2. 409(CONFLICT) - In case of duplicate programCode
     *
     * This handler method should map to the URL "/api/v1/gymservice" using HTTP POST
     * method".
     */



    /* API Version: 1.0
     * Define a handler method which will update a specific program by reading the
     * Serialized object from request body and save the updated program in
     * program table in database and handle ProgramNotFoundException as well.
     *
     * This handler method should return any one of the status
     * messages basis on different situations:
     * 1. 200(OK) - If the program is updated successfully.
     * 2. 404(NOT FOUND) - If the program with specified programCode is not found.
     *
     * This handler method should map to the URL "/api/v1/gymservice" using HTTP PUT
     * method.
     */



    /* API Version: 1.0
     * Define a handler method which will delete a specific program by programCode.
     * It will handle ProgramNotFoundException as well, in case there is no program
     * available with that programCode.
     *
     * This handler method should return any one of the status
     * messages basis on different situations:
     * 1. 200(OK) - If the program is updated successfully.
     * 2. 404(NOT FOUND) - If the program with specified programCode is not found.
     *
     * This handler method should map to the URL "/api/v1/gymservice/{programCode}" using HTTP DELETE
     * method, where "programCode" should be replaced by a programCode without {}
     */


}
