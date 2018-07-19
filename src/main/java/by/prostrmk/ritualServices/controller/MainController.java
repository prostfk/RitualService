package by.prostrmk.ritualServices.controller;

import by.prostrmk.ritualServices.model.entity.Product;
import by.prostrmk.ritualServices.model.entity.User;
import by.prostrmk.ritualServices.model.repository.ProductRepository;
import by.prostrmk.ritualServices.model.repository.UserRepository;
import by.prostrmk.ritualServices.model.validator.UserValidator;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserValidator validator;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex(){
        ModelAndView modelAndView = new ModelAndView("index", "user", new User());
        List<Product> all = productRepository.findAll();
        modelAndView.addObject("products", all);
        return modelAndView;
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ModelAndView getLeaveMessage(){
        return new ModelAndView("contacts", "user", new User());
    }

    @RequestMapping(value = "/leaveMessage", method = RequestMethod.POST)
    public ModelAndView postLeaveMessage(@ModelAttribute("user") User user, BindingResult result, SessionStatus status){

        validator.validate(user,result);
        System.out.println(user);
        if (result.hasErrors()){
            return new ModelAndView("LeaveRequest", "user", user);
        }else{
            user.setDate(new Date());
            status.setComplete();
            userRepository.save(user);
            return new ModelAndView("successRequest","user", user);
        }
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView getSearchByProducts(@RequestParam("searchValue") String searchValue){
        List<Product> productsByNameLikeIgnoreCase = productRepository.findProductsByNameLikeIgnoreCase(searchValue);
        return new ModelAndView("index", "products", productsByNameLikeIgnoreCase);
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ModelAndView getAuth(){
        return new ModelAndView("auth", "user", new User());
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String postAuth(HttpSession session, User user){
        if (user.getUsername().equals("admin") && DigestUtils.md5Hex(user.getMessage()).equals("202cb962ac59075b964b07152d234b70")){
            session.setAttribute("user", user);
            return "redirect:/admin/requests";
        }else {
            return "redirect:/auth";
        }
    }



}
