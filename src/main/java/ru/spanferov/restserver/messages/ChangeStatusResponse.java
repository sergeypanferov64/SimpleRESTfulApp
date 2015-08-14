package ru.spanferov.restserver.messages;

public class ChangeStatusResponse {
    
    private Integer id;
    private Boolean prevStatus;
    private Boolean newStatus;

    public ChangeStatusResponse(int id, boolean prevStatus, boolean newStatus){
        this.id = id;
        this.prevStatus = prevStatus;
        this.newStatus = newStatus;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getPrevStatus() {
        return prevStatus;
    }

    public void setPrevStatus(Boolean prevStatus) {
        this.prevStatus = prevStatus;
    }

    public Boolean getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Boolean newStatus) {
        this.newStatus = newStatus;
    }
    
}
