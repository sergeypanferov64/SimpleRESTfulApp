package ru.spanferov.restserver.messages;

import java.util.Date;

public class GetStatisticRequest {
    
    private Boolean status;
    private Date ts;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }
    
}
