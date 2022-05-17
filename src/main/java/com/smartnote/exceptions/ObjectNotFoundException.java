package com.smartnote.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Object not found")
public class ObjectNotFoundException extends Exception
{
    static final long serialVersionUID = -3387516993334229948L;


    public ObjectNotFoundException(String message)
    {
        super(message);
    }

}
