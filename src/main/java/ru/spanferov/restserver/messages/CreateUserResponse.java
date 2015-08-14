package ru.spanferov.restserver.messages;

public class CreateUserResponse {
    
    private Integer id;
    
    public CreateUserResponse(int id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    
}
