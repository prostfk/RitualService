package by.prostrmk.ritualServices.controller;

import by.prostrmk.ritualServices.model.entity.Order;
import by.prostrmk.ritualServices.model.entity.Product;
import by.prostrmk.ritualServices.model.entity.TypeOfProduct;
import by.prostrmk.ritualServices.model.entity.User;
import by.prostrmk.ritualServices.model.repository.OrderRepository;
import by.prostrmk.ritualServices.model.repository.ProductRepository;
import by.prostrmk.ritualServices.model.repository.UserRepository;
import by.prostrmk.ritualServices.model.util.FileUtil;
import by.prostrmk.ritualServices.model.util.ProductUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public ModelAndView getAdminPane(HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            return new ModelAndView("redirect:/auth");
//        }
//        if (!user.getUsername().equals("admin") && !DigestUtils.md5Hex(user.getMessage()).equals("202cb962ac59075b964b07152d234b70")) {
//            return new ModelAndView("redirect:/");
//        }
        List<User> users = new ArrayList<>();
        for (User user1 : userRepository.findAll()) {
            users.add(user1);
        }
        return new ModelAndView("adminPane", "users", users);

    }


    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public ModelAndView getAddProductPage() {
        ModelAndView modelAndView = new ModelAndView("addProductPage", "product", new Product());
        modelAndView.addObject("typesList", TypeOfProduct.getAllTypes());
        return modelAndView;
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public ModelAndView postAddProductPage(@RequestParam("file") MultipartFile file, Product product) {
        if (ProductUtil.validate(product)) {
            String s = FileUtil.saveFile(file);
            product.setPathToPic(s);
            productRepository.save(product);
            return new ModelAndView("redirect:/");
        }
        System.out.println(product);
        return new ModelAndView("forward:https://www.google.by/search?q=%D0%BE%D1%88%D0%B8%D0%B1%D0%BA%D0%B0+%D0%B2%D0%B0%D0%BB%D0%B8%D0%B4%D0%B0%D1%86%D0%B8%D0%B8&oq=%D0%BE%D1%88%D0%B8%D0%B1%D0%BA%D0%B0+%D0%B2%D0%B0%D0%BB%D0%B8%D0%B4%D0%B0%D1%86%D0%B8%D0%B8&aqs=chrome..69i57j0l5.5589j0j7&sourceid=chrome&ie=UTF-8");
    }

    @RequestMapping(value = "/removeProduct", method = RequestMethod.GET)
    public ModelAndView getRemoveProductPage() {
        List<Product> all = productRepository.findAll();
        return new ModelAndView("adminProducts", "products", all);
    }

    @RequestMapping(value = "/removeProduct/{id}", method = RequestMethod.POST)
    public ModelAndView postRemoveProductById(@PathVariable String id, HttpSession session) {
        Product productsById = productRepository.findProductById(id);
        if (productsById != null) {
            String pathToPic = productsById.getPathToPic();
            File file = new File("src/main/webapp/" + pathToPic);
            if (file.exists()) {
                file.delete();
            }
            productRepository.delete(productsById);
        }
        return new ModelAndView("redirect:/removeProduct");
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    public String removeUser(@PathVariable String id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        System.out.println("user = " + user + " was deleted");
        return "redirect:/admin/requests";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ModelAndView getIndexOfOrders(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("adminOrdersPage");
        List<Order> all = orderRepository.findAll();
        modelAndView.addObject("orders", all);
        return modelAndView;
    }


}
