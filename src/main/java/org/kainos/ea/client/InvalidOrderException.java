package org.kainos.ea.client;

public class InvalidOrderException extends Exception {

    @Override
    public String getMessage() {
        return "Failed to find order in database";
    }

}
