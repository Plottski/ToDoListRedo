package Thetododolist.tododolist.Controllers;

import Thetododolist.tododolist.Entities.User;
import Thetododolist.tododolist.Repos.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

public class ToDoListController {


    @RestController
    public class toDoListController {

        @Autowired
        UserRepo users;

        @RequestMapping(path = "/login", method = RequestMethod.POST)
        public User userLogin(HttpSession session, @RequestBody User user) {
            User userFromDB = users.findByUserName(user.getUserName());
            return userFromDB;
        }


    }
}
