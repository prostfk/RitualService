package by.prostrmk.ritualServices.controller;

import by.prostrmk.ritualServices.model.entity.Order;
import by.prostrmk.ritualServices.model.entity.Product;
import by.prostrmk.ritualServices.model.entity.User;
import by.prostrmk.ritualServices.model.repository.OrderRepository;
import by.prostrmk.ritualServices.model.repository.ProductRepository;
import by.prostrmk.ritualServices.model.repository.UserRepository;
import by.prostrmk.ritualServices.model.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {

    @Autowired ProductRepository productRepository;

    @Autowired UserRepository userRepository;

    @Autowired UserValidator userValidator;

    @Autowired OrderRepository orderRepository;

    @RequestMapping(value = "/orderProduct/{id}",method = RequestMethod.GET)
    public ModelAndView getOrderProductPage(@PathVariable String id){
        Product productById = productRepository.findProductById(id);
        if (productById!=null){
            productById.setPathToPic("../" + productById.getPathToPic());
            ModelAndView modelAndView = new ModelAndView("orderProduct");
            modelAndView.addObject("product",productById);
            modelAndView.addObject("user", new User());
            return modelAndView;
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/orderProduct/{id}",method = RequestMethod.POST)
    public String postOrder(@PathVariable String id, User user, BindingResult result, Model model){
        Product productById = productRepository.findProductById(id);
        userValidator.validate(user,result);
        if (productById!=null && !result.hasErrors()) {
            Order order = new Order(user,productById);
            orderRepository.save(order);
            System.out.println("SAVED");
            model.addAttribute("user", user);
            return "successRequest";
        }
        return "redirect:/error";


    }

}
