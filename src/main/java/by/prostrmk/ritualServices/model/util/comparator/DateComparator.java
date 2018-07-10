package by.prostrmk.ritualServices.model.util.comparator;

import by.prostrmk.ritualServices.model.entity.User;

import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<User> {


    @Override
    public int compare(User o1, User o2) {
        return (int) (o1.getDate().getTime() - o2.getDate().getTime());
    }
}
