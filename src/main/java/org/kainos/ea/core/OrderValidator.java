package org.kainos.ea.core;

import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.OrderUpdateRequest;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.InvalidOrderException;

import java.time.LocalDate;

public class OrderValidator {

    public String isValidOrder(OrderRequest order) throws InvalidOrderException {

        if(order.getCustomerId() < 1){
            throw new InvalidOrderException();

        }

        return null;

    }


    public boolean isValidOrderUpdate(OrderUpdateRequest order) {

        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        if(order.getDispatchDate().toLocalDate().isBefore(oneYearAgo)){
            return false;
        }

        return true;

    }
}
