package by.prostrmk.ritualServices.controller;

import by.prostrmk.ritualServices.model.entity.User;
import by.prostrmk.ritualServices.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class InfoController {

    @Autowired
    UserRepository repository;

    @RequestMapping(value = "/getInfo/{username}", method = RequestMethod.GET)
    public void downloadInfo(@PathVariable String username, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        var user = (User)session.getAttribute("user");
        User userByUsername = repository.findUserByUsername(username);
        if (user!=null){
            String filepath = "/logging/users/" + userByUsername.getUsername() + " - " + user.getDate().toString();
            String dataDirectory = request.getServletContext().getRealPath(filepath);
            Path file = Paths.get(dataDirectory, username);
            if (Files.exists(file)) {
                response.setContentType(URLConnection.guessContentTypeFromName(username));
                response.addHeader("Content-Disposition", "attachment; filename=" + username);
                try {
                    Files.copy(file, response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
