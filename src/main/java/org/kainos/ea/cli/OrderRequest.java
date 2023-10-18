package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class OrderRequest {

    private int customerId;

    private Date orderDate;

    private Date dispatchDate;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDispatchDate() {
        return dispatchDate;
    }
    public void setDispatchDate(Date dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    @JsonCreator
    public OrderRequest(
            @JsonProperty("customerId") int customerId,
            @JsonProperty("orderDate") Date orderDate
            ) {
        this.customerId = customerId;
        this.orderDate = orderDate;
    }

}
