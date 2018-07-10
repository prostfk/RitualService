package by.prostrmk.ritualServices.model.util;

import by.prostrmk.ritualServices.model.entity.User;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProposalUtil {

    private static final Logger LOGGER = Logger.getLogger(ProposalUtil.class);

    public static void saveInFile(@NotNull User user){

        String username = "logging/users/" + user.getUsername() + " - " + user.getDate().toString();
        var data = String.format("Дата: %s, Имя: %s, Телефон: %s, Mail: %s, Пожелание: %s", user.getDate().toString(), user.getUsername(), user.getMobilePhone(), user.getMail(), user.getMessage());
        try {
            FileUtils.write(new File(username), data,"UTF-8",true);
        } catch (IOException e) {
            LOGGER.error(username);
        }
    }

    public static void method(){

    }

}
