package by.prostrmk.ritualServices.controller;

import by.prostrmk.ritualServices.model.entity.User;
import by.prostrmk.ritualServices.model.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminPaneController {

    @Autowired
    UserRepository repository;

    @RequestMapping(value = "/requests",method = RequestMethod.GET)
    public ModelAndView getAdminPane(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user==null){ return new ModelAndView("redirect:/auth"); }
        if (!user.getUsername().equals("admin") && !DigestUtils.md5Hex(user.getMessage()).equals("202cb962ac59075b964b07152d234b70")) {
            return new ModelAndView("redirect:/");
        }
        List<User> users = new ArrayList<>();
        for (User user1 : repository.findAll()) {
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



}
