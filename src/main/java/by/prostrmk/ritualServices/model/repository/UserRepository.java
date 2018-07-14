package by.prostrmk.ritualServices.model.repository;

import by.prostrmk.ritualServices.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findUserByUsername(String username);
    User findUserByMail(String mail);
    User findUserByMobilePhone(String mobilePhone);
    User findUserByUsernameLikeIgnoreCase(String username);
    List<User> findUsersByMessageLikeIgnoreCase(String message);


}
