package com.tms.user.service.Exceptions;

public class ResourceNotFoundException extends  RuntimeException{

    public ResourceNotFoundException(){
        super("Resource nt found on server!!");
    }

    public ResourceNotFoundException(String message){
        super(message);

    }


}
