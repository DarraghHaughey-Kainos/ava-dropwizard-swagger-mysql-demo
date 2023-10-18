package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class OrderUpdateRequest {

    private Date dispatchDate;

    public Date getDispatchDate() {
        return dispatchDate;
    }
    public void setDispatchDate(Date dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    @JsonCreator
    public OrderUpdateRequest(
            @JsonProperty("dispatchDate") Date dispatchDate
            ) {
        this.dispatchDate = dispatchDate;
    }

}
