package Thetododolist.tododolist.Controllers;

import Thetododolist.tododolist.Entities.User;
import Thetododolist.tododolist.Repos.UserRepo;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

public class ToDoListController {


    @RestController
    public class toDoListController {

        @Autowired
        UserRepo users;

        public Server h2;

        @PostConstruct
        public void init() throws SQLException {
            //Server.createWebServer().start();
            h2.start();
            if (users.count() == 0) {
                User user = new User("plottski", "touchmyopness@gmail.com", "1234",
                        false,null);
                users.save(user);
            }
        }

        @RequestMapping(path = "/login", method = RequestMethod.POST)
        public User userLogin(HttpSession session, @RequestBody User user) {
            System.out.println(user);
            User userFromDB = users.findByUserName(user.getUserName());
            return userFromDB;
        }


    }
}
