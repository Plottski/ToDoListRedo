package Thetododolist.tododolist.Repos;

import Thetododolist.tododolist.Entities.Item;
import Thetododolist.tododolist.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ItemRepo extends CrudRepository<Item, Integer> {
    ArrayList<Item> findAll();
    Item findByCreator(User user);
    Item findById(int id);

}
