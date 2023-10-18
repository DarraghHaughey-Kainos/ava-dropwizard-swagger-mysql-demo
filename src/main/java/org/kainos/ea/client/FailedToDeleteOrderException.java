package org.kainos.ea.client;

public class FailedToDeleteOrderException extends Exception {

    @Override
    public String getMessage() {
        return "Failed to delete order from database";
    }

}
