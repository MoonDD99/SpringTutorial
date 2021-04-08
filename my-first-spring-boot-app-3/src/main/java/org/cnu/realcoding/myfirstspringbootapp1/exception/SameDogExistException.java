package org.cnu.realcoding.myfirstspringbootapp1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SameDogExistException extends RuntimeException{
}