package by.prostrmk.ritualServices.controller;

import by.prostrmk.ritualServices.model.entity.Product;
import by.prostrmk.ritualServices.model.entity.TypeOfProduct;
import by.prostrmk.ritualServices.model.entity.User;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminPaneController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/requests",method = RequestMethod.GET)
    public ModelAndView getAdminPane(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user==null){ return new ModelAndView("redirect:/auth"); }
        if (!user.getUsername().equals("admin") && !DigestUtils.md5Hex(user.getMessage()).equals("202cb962ac59075b964b07152d234b70")) {
            return new ModelAndView("redirect:/");
        }
        List<User> users = new ArrayList<>();
        for (User user1 : userRepository.findAll()) {
            users.add(user1);
        }
        return new ModelAndView("adminPane", "users", users);

    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ModelAndView getAuth(){
        return new ModelAndView("auth", "user", new User());
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String postAuth(HttpSession session, User user){
        if (user.getUsername().equals("admin") && DigestUtils.md5Hex(user.getMessage()).equals("202cb962ac59075b964b07152d234b70")){
            session.setAttribute("user", user);
            return "redirect:/requests";
        }else {
            return "redirect:/auth";
        }
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public ModelAndView getAddProductPage(){
        ModelAndView modelAndView = new ModelAndView("addProductPage", "product", new Product());
        modelAndView.addObject("typesList", TypeOfProduct.getAllTypes());
        return modelAndView;
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public ModelAndView postAddProductPage(@RequestParam("file")MultipartFile file, Product product){
        if (ProductUtil.validate(product)){
            String s = FileUtil.saveFile(file);
//            product.setType(TypeOfProduct.stringToEnum(product.getType().name()));
            product.setPathToPic(s);
            productRepository.save(product);
            return new ModelAndView("redirect:/");
        }
        System.out.println(product);
        return new ModelAndView("forward:https://www.google.by/search?q=%D0%BE%D1%88%D0%B8%D0%B1%D0%BA%D0%B0+%D0%B2%D0%B0%D0%BB%D0%B8%D0%B4%D0%B0%D1%86%D0%B8%D0%B8&oq=%D0%BE%D1%88%D0%B8%D0%B1%D0%BA%D0%B0+%D0%B2%D0%B0%D0%BB%D0%B8%D0%B4%D0%B0%D1%86%D0%B8%D0%B8&aqs=chrome..69i57j0l5.5589j0j7&sourceid=chrome&ie=UTF-8");
    }

    @RequestMapping(value = "/removeProduct", method = RequestMethod.GET)
    public ModelAndView getRemoveProductPage(HttpSession session){
        Object object = session.getAttribute("user");
        if (object!=null){
            List<Product> all = productRepository.findAll();
            return new ModelAndView("adminProducts", "products", all);
        }
        return new ModelAndView("redirect:/auth");
    }

    @RequestMapping(value = "/removeProduct/{id}", method = RequestMethod.POST)
    public ModelAndView postRemoveProductById(@PathVariable String id, HttpSession session){
        if (session.getAttribute("user")!=null){
            Product productsById = productRepository.findProductsById(id);
            if (productsById!=null){
                productRepository.delete(productsById);
            }
        }
        return new ModelAndView("redirect:/removeProduct");
    }



}
