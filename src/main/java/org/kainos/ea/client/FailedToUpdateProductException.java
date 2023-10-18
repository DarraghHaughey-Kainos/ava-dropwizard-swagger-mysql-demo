package org.kainos.ea.client;

public class FailedToUpdateProductException extends Exception {

    @Override
    public String getMessage() {
        return "Failed to update product in database";
    }


}
