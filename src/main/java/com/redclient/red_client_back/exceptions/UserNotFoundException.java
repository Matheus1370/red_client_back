package com.redclient.red_client_back.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("Usuário não existe");
    }
}
