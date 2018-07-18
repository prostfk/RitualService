package by.prostrmk.ritualServices.model.repository;

import by.prostrmk.ritualServices.model.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    Order findOrderById(String id);
    List<Order> findAll();

}
