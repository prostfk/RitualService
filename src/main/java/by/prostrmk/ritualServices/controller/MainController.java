package by.prostrmk.ritualServices.controller;

import by.prostrmk.ritualServices.model.entity.User;
import by.prostrmk.ritualServices.model.repository.UserRepository;
import by.prostrmk.ritualServices.model.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class MainController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserValidator validator;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex(){
        return new ModelAndView("index", "user", new User());
    }

    @RequestMapping(value = "/leaveMessage", method = RequestMethod.GET)
    public ModelAndView getLeaveMessage(){
        return new ModelAndView("LeaveRequest", "user", new User());
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
            repository.save(user);
            return new ModelAndView("successRequest","user", user);
        }


    }

}
