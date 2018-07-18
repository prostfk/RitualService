package by.prostrmk.ritualServices.controller;

import by.prostrmk.ritualServices.model.entity.Product;
import by.prostrmk.ritualServices.model.entity.TypeOfProduct;
import by.prostrmk.ritualServices.model.entity.User;
import by.prostrmk.ritualServices.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SectionController {

    @Autowired
    ProductRepository repository;

    @RequestMapping(value = "/monuments", method = RequestMethod.GET)
    public ModelAndView getMonumentsPage(){
        return getSection(TypeOfProduct.Памятник);
    }

    @RequestMapping(value = "/fences", method = RequestMethod.GET)
    public ModelAndView getFencePage(){
        return getSection(TypeOfProduct.Ограда);
    }

    @RequestMapping(value = "/engravings", method = RequestMethod.GET)
    public ModelAndView getEngravingWorksPage(){
        return getSection(TypeOfProduct.ГраверныеРаботы);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ModelAndView getCurrentProduct(@PathVariable String id){
        Product productById = repository.findProductById(id);
        if (productById!=null){
            productById.setPathToPic("../" + productById.getPathToPic());
            return new ModelAndView("singleProduct", "product", productById);
        }
        return new ModelAndView("redirect:/");
    }


    private ModelAndView getSection(TypeOfProduct typeOfProduct){
        ModelAndView modelAndView = new ModelAndView("index", "user", new User());
        List<Product> productsByType = repository.findProductsByType(typeOfProduct);
        modelAndView.addObject("products", productsByType);
        return modelAndView;
    }


}
