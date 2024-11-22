package Thetododolist.tododolist.Repos;

import Thetododolist.tododolist.Entities.User;
import Thetododolist.tododolist.Utilities.PasswordStorage;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserRepo extends CrudRepository<User, Integer> {
    ArrayList<User> findAll();
    User findByUserName(String userName);

}
