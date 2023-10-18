package org.kainos.ea.client;

public class FailedToCreateProductException extends Exception {

    @Override
    public String getMessage() {
        return "Could not create product";
    }

}
