package org.kainos.ea.client;

public class FailedToUpdateOrderException extends Exception {

    @Override
    public String getMessage() {
        return "Failed to update order in database";
    }

}
