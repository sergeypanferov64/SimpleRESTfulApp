package ru.spanferov.restserver.messages;

import java.sql.Timestamp;
import java.util.List;
import ru.spanferov.restserver.domain.User;

public class GetStatisticResponse {
    
    private List<User> users;
    private Timestamp timestamp;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    
}
