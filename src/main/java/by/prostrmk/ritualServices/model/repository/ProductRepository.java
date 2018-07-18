package by.prostrmk.ritualServices.model.repository;

import by.prostrmk.ritualServices.model.entity.Product;
import by.prostrmk.ritualServices.model.entity.TypeOfProduct;
import by.prostrmk.ritualServices.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findProductsByCountry(String country);
    List<Product> findProductsByMaterialLikeIgnoreCase(String material);
    List<Product> findProductsByNameLikeIgnoreCase(String name);
    List<Product> findProductsByWeight(int weight);
    List<Product> findProductsByType(TypeOfProduct typeOfProduct);
    List<Product> findProductsByWeightLessThan(int weight);
    Product findProductById(String id);

}
