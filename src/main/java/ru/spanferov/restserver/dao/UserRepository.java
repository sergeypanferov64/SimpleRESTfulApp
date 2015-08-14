package ru.spanferov.restserver.dao;

import java.util.Date;
import ru.spanferov.restserver.domain.User;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u where u.status = ?1")
    List<User> findAllByStatus(Boolean status);

    @Query("select u from User u where u.updttm > ?1")
    List<User> findAllChangedAfterTimestamp(Date ts);

    @Query("select u from User u where u.updttm > ?1 and u.status = ?2")
    List<User> findAllChangedAfterTimestampByStatus(Date ts, Boolean status);

}
