package by.prostrmk.ritualServices.controller;

import by.prostrmk.ritualServices.model.entity.User;
import by.prostrmk.ritualServices.model.repository.UserRepository;
import by.prostrmk.ritualServices.model.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    public String removeUser(@PathVariable String id, HttpSession session){
        User admin = (User) session.getAttribute("user");
        try{
            var s = admin.getUsername();
        }catch (Exception e){ return "redirect:/"; }
        User user = repository.findById(id).get();
        repository.delete(user);
        System.out.println("user = " + user + " was deleted");
        return "redirect:/requests";
    }

}
