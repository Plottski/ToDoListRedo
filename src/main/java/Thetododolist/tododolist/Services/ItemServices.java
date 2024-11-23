package Thetododolist.tododolist.Services;

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
public class ItemServices {

    @Autowired
    ItemRepo items;

    @Autowired
    UserRepo users;

    @RequestMapping(path = "/add-item", method = RequestMethod.POST)
    public ResponseEntity<Item> addItemtoDB(HttpSession session, @RequestBody Item item) {
        Item newItem = new Item(item.getCreationDate(),item.getCreator(),item.getTitle(),item.getDescription(),
                item.getDueDate(),item.getClosedDate(),item.getCloser());
        addItemtoDB(session,item);

        items.save(newItem);


        //String uName = item.getCloser().getUserName();

        //return items.findByUserName(item.getCreator());
        return new ResponseEntity<Item>(newItem, HttpStatus.OK);
    }

   /* @RequestMapping(path = "/delete-item", method = RequestMethod.DELETE)
    public ResponseEntity<Item> deleteItemFromDB(HttpSession session, @RequestBody Item item) throws
            PasswordStorage.CannotPerformOperationException {
        String uName = (String) session.getAttribute("userName");
        if (User.isValidUser(users.findByUserName(uName)) != null) {
            if (items.findById(item.getId()).isPresent()) {
                items.delete(item);
                return new ResponseEntity<Item>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Item>(HttpStatus.FORBIDDEN);
    } */
}
