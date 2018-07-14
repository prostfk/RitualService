package by.prostrmk.ritualServices.modelTesting.repostioryTest;

import by.prostrmk.ritualServices.RitualServicesApplication;
import by.prostrmk.ritualServices.model.entity.Product;
import by.prostrmk.ritualServices.model.entity.TypeOfProduct;
import by.prostrmk.ritualServices.model.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RitualServicesApplication.class)
@Transactional
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;


//    @Before
//    public void setUp() throws Exception {
//        Product product = new Product("Плита оч классная", "Оч крутая плита","Мрамор вроде","Беларусь","путь",220,TypeOfProduct.Памятник);
//        Product product1 = new Product("Средняя плита", "Оч крутая плита","Мрамор вроде","Беларусь","путь",100,TypeOfProduct.Памятник);
//        Product product2 = new Product("Плита из рашки", "Оч крутая плита","Мрамор вроде","Россия","путь",150,TypeOfProduct.Памятник);
//        Product product3 = new Product("Румынская легкая", "Оч крутая плита","Мрамор вроде","Румыния","путь",70,TypeOfProduct.Памятник);
//        Product product4 = new Product("Шведская плиточка", "Оч крутая плита","Мрамор вроде","Швеция","путь",50,TypeOfProduct.Памятник);
//    }


    @Test
    public void findProductByTypeTest() {
        List<Product> productsByType = productRepository.findProductsByType(TypeOfProduct.Ограда);
        assertEquals(1,productsByType.size());
    }

    @Test
    public void findProductsByNameLikeIgnoreCase() {
        List<Product> byName = productRepository.findProductsByNameLikeIgnoreCase("пли");
        assertNotNull(byName);
        assertEquals(8, byName.size());
    }

    @Test
    public void findProductsByCountry() {
        List<Product> bel = productRepository.findProductsByCountry("Беларусь");
        assertEquals(4,bel.size());
        assertNotNull(bel.get(0));
        assertNotNull(bel.get(1));
        assertNotNull(bel.get(2));
        assertNotNull(bel.get(3));
        bel.forEach(System.out::println);
    }

    @Test
    public void findProductsByWeightLessThanTest() {
        List<Product> productsByWeightLessThan = productRepository.findProductsByWeightLessThan(101);
        productsByWeightLessThan.forEach(System.out::println);
        assertEquals(6,productsByWeightLessThan.size());
        for (Product product : productsByWeightLessThan) {
            if (product==null){
                fail();
            }
        }
    }

    @Test
    public void findProductByWeightFiveTeen() {
        List<Product> productsByWeight = productRepository.findProductsByWeight(50);
        assertEquals(2,productsByWeight.size());
        productsByWeight.forEach(product -> {
            if (product==null){
                fail();
            }
        });
    }


    @Test
    public void findProductsByMaterialTest() {
        List<Product> products = productRepository.findProductsByMaterialLikeIgnoreCase("мрамор");
        products.forEach(product -> {
            assertNotNull(product);
            System.out.println("product = " + product);
        });
    }
}
