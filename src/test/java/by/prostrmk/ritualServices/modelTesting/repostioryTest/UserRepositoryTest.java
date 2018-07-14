package by.prostrmk.ritualServices.modelTesting.repostioryTest;

import by.prostrmk.ritualServices.RitualServicesApplication;
import by.prostrmk.ritualServices.model.entity.User;
import by.prostrmk.ritualServices.model.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RitualServicesApplication.class)
@Transactional
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

//    @Before
//    public void test(){
//        User user = new User("TestRoman", "+testphone", "test@mail.com", "Hey, i am test", new Date());
//        User user1 = new User("Vova", "+22346435436", "gagkiller@gmail.com", "pro spring dev", new Date());
//        User user2 = new User("Vitaliy", "+28678545436", "vitvit@gmail.com", "dotnetdev", new Date());
//        User user3 = new User("Anya", "+375444346536", "anyaDeveloper@gmail.com", "Spring data expert", new Date());
//    }

    @Test
    public void findAllTest(){
        List<User> all = repository.findAll();
        all.forEach(System.out::println);
        assertEquals(4, all.size());
    }

    @Test
    public void findUserByUsernameTest() {
        User testRoman = repository.findUserByUsername("TestRoman");
        assertEquals( "test@mail.com", testRoman.getMail());
    }

    @Test
    public void findUserByMailTest() {
        User userByMail = repository.findUserByMail("vitvit@gmail.com");
        assertEquals("Vitaliy", userByMail.getUsername());
    }

    @Test
    public void findUserByPhoneTest() {
        User userByMobilePhone = repository.findUserByMobilePhone("+375444346536");
        assertEquals("Anya", userByMobilePhone.getUsername());
    }

    @Test
    public void findUserByUsernameLikeIgnoreCaseTest() {
        User ny = repository.findUserByUsernameLikeIgnoreCase("NY");
        System.out.println("ny = " + ny);
        assertNotNull(ny);
    }

    @Test
    public void findUsersByMessageTest() {
        List<User> deV = repository.findUsersByMessageLikeIgnoreCase("DeV");
        assertEquals(2,deV.size());
        deV.forEach(user -> {
            assertNotNull(user);
            System.out.println("user = " + user);
        });
    }
}
