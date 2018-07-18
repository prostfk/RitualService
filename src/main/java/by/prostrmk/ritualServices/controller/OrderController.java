package by.prostrmk.ritualServices.controller;

import by.prostrmk.ritualServices.model.entity.Product;
import by.prostrmk.ritualServices.model.entity.User;
import by.prostrmk.ritualServices.model.repository.ProductRepository;
import by.prostrmk.ritualServices.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/orderProduct/{id}",method = RequestMethod.GET)
    public ModelAndView getOrderProductPage(@PathVariable String id){
        Product productById = productRepository.findProductById(id);
        if (productById!=null){
            ModelAndView modelAndView = new ModelAndView("orderProduct");
            modelAndView.addObject("product",productById);
            modelAndView.addObject("user", new User());
            return modelAndView;
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/orderProduct/{id}",method = RequestMethod.POST)
    public ModelAndView postOrder(){
        return new ModelAndView();
    }

}
