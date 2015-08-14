package ru.spanferov.restserver.messages;

public class ChangeStatusRequest {
    
    private Boolean newStatus;

    public Boolean getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Boolean newStatus) {
        this.newStatus = newStatus;
    }
    
}
