package com.goldys.gymservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/* Custom Exception already created and to be used */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Specified Program not found")
public class ProgramNotFoundException extends Exception {
}
