package Thetododolist.tododolist.Services;

import Thetododolist.tododolist.Entities.User;
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

import java.util.ArrayList;

import static Thetododolist.tododolist.Entities.User.isValidUser;

@RestController
public class UserServices {

    @Autowired
    UserRepo users;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<User> userLogin(HttpSession session, @RequestBody User user) throws
            PasswordStorage.CannotPerformOperationException, PasswordStorage.InvalidHashException {
        User userFromDB = users.findByUserName(user.getUserName());
        if (User.userValidation(user,userFromDB,session)) {
            users.save(userFromDB);
            return new ResponseEntity<User>(userFromDB, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(path = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<User> userSignUp(HttpSession session, @RequestBody User user) throws
            PasswordStorage.CannotPerformOperationException {
        if (isValidUser(user) != null){
            users.save(user);
            session.setAttribute("userName", user.getUserName());
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        else return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(path = "/add-collaborator", method = RequestMethod.POST)
    public ResponseEntity<User> userAddCollaborator(HttpSession session, @RequestBody User user, String collaboratorName)
            throws PasswordStorage.CannotPerformOperationException {
        if (isValidUser(users.findByUserName(user.getUserName())) != null &&
                users.findByUserName(collaboratorName) != null) {
            User collaboratorToAdd = users.findByUserName(collaboratorName);
            user.getCollaborators().add(collaboratorToAdd);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(path = "/delete-collaborator", method = RequestMethod.DELETE)
    public ResponseEntity<User> userRemoveCollaborator(HttpSession session, @RequestBody User user, String collaboratorName)
            throws PasswordStorage.CannotPerformOperationException, PasswordStorage.InvalidHashException {
        if (User.userValidation(user, users.findByUserName(user.getUserName()), session)) {
            User updatedUser = deleteCollaborator(user, users.findByUserName(collaboratorName));
            return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
        }
        else return new ResponseEntity<User>(HttpStatus.FORBIDDEN);

        //if (deleteCollaborator(user, users.findByUserName(collaboratorName))) {
        //if (isValidUser(users.findByUserName(user.getUserName())) != null &&
        //        isValidUser(users.findByUserName(collaboratorName)) != null) {
        //    user.getCollaborators().remove(users.findByUserName(collaboratorName));


        }
    //    return new ResponseEntity<>(HttpStatus.FORBIDDEN);


    public User makeCollaborator(User user, User userCollaborator) throws PasswordStorage.CannotPerformOperationException {
        if (isValidUser(users.findByUserName(user.getUserName())) != null &&
                isValidUser(users.findByUserName(userCollaborator.getUserName())) != null) {

            user.getCollaborators().add(userCollaborator);
            return user;
        }
        return user;
    }

    public User deleteCollaborator(User user, User userCollaborator) throws PasswordStorage.CannotPerformOperationException {
        if (isValidUser(users.findByUserName(user.getUserName())) != null &&
                isValidUser(users.findByUserName(userCollaborator.getUserName())) != null) {

            ArrayList<User> collaborators = user.getCollaborators();
            for (int i = 0; i < collaborators.size(); i++) {
                if (collaborators.get(i) == userCollaborator) {
                    collaborators.remove(userCollaborator);
                    return user;
                }
            }
        }
        return null;
    }
}

