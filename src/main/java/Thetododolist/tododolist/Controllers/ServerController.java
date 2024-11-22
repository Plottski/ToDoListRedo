package Thetododolist.tododolist.Controllers;

import Thetododolist.tododolist.Entities.Item;
import Thetododolist.tododolist.Entities.User;
import Thetododolist.tododolist.Repos.ItemRepo;
import Thetododolist.tododolist.Repos.UserRepo;
import Thetododolist.tododolist.Utilities.PasswordStorage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    @Autowired
    UserRepo users;

    @Autowired
    ItemRepo items;




}




