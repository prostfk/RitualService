package by.prostrmk.ritualServices.modelTesting.repostioryTest;

import by.prostrmk.ritualServices.RitualServicesApplication;
import by.prostrmk.ritualServices.model.entity.Product;
import by.prostrmk.ritualServices.model.entity.TypeOfProduct;
import by.prostrmk.ritualServices.model.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RitualServicesApplication.class)
@Transactional
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;


//    @Before
//    public void setUp() throws Exception {
//        Product product = new Product("Ограда какая-то", "","","","",220,TypeOfProduct.Ограда);
//        productRepository.save(product);
//    }

    @Test
    public void findProductByTypeTest() {
        List<Product> productsByType = productRepository.findProductsByType(TypeOfProduct.Памятник);
        productsByType.forEach(System.out::println);
    }
}
