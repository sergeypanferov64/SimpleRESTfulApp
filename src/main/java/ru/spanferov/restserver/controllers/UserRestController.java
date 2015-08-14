package ru.spanferov.restserver.controllers;

import ru.spanferov.restserver.messages.ChangeStatusResponse;
import ru.spanferov.restserver.messages.GetStatisticRequest;
import ru.spanferov.restserver.messages.ChangeStatusRequest;
import ru.spanferov.restserver.messages.CreateUserResponse;
import ru.spanferov.restserver.messages.GetStatisticResponse;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.spanferov.restserver.domain.User;
import ru.spanferov.restserver.dao.UserRepository;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserRepository userRepo;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll() {
        return (List<User>) userRepo.findAll();
    }

    
    @RequestMapping(value = "/statistic", method = RequestMethod.POST)
    public GetStatisticResponse getStatistic(@RequestBody GetStatisticRequest req) {

        GetStatisticResponse resp = new GetStatisticResponse();

        // get current timestamp for response
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        resp.setTimestamp(currentTimestamp);
        
        if(req.getStatus() != null){
            if(req.getTs() != null){
                resp.setUsers(userRepo.findAllChangedAfterTimestampByStatus(req.getTs(), req.getStatus()));
            } else
                resp.setUsers(userRepo.findAllByStatus(req.getStatus()));
        }
        else if(req.getTs() != null)
            resp.setUsers(userRepo.findAllChangedAfterTimestamp(req.getTs()));
        else
            resp.setUsers( (List<User>) userRepo.findAll());
        
        return resp;
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public User get(@PathVariable Integer id) {
        return userRepo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public CreateUserResponse create(@RequestBody User user) {
        return new CreateUserResponse(userRepo.save(user).getId());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable Integer id) {
        userRepo.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public ChangeStatusResponse update(@PathVariable Integer id, @RequestBody ChangeStatusRequest req) {

        User update = userRepo.findOne(id);
        boolean prevStatus = update.getStatus();
        update.setStatus(req.getNewStatus());
        User updated = userRepo.save(update);
        
        // delay:
        try {
            Thread.sleep(7500);
        } catch (InterruptedException ex) {
            Logger.getLogger(UserRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ChangeStatusResponse resp = new ChangeStatusResponse(id, prevStatus, updated.getStatus());
        return resp;
    }

}
