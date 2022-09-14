package com.qa.account.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Account doesn't exist with this Id")
public class AccountNotFoundException extends Exception{

}
