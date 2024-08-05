package com.example.Master.Microservices.Dao;

import com.example.Master.Microservices.entity.Name;
import com.example.Master.Microservices.entity.User;
import com.example.Master.Microservices.entity.UserV2;
import com.example.Master.Microservices.exceptionHandling.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<User>();
    private static int userCounter=0;
    static {
        users.add(new User(++userCounter,"Hassan Kamel", LocalDate.of(1998,11,29)));
        users.add(new User(++userCounter,"Zayn Malek", LocalDate.now().minusYears(20)));
        users.add(new User(++userCounter,"Layla Ali", LocalDate.now().minusYears(10)));
    }
    public List<User> getUsers() {
        return users;
    }
    public List<UserV2> getUsersV2() {
       return users.stream().map(
                user -> {
                    Name name=new Name(user.getName().split(" ")[0],user.getName().split(" ")[1]);
                    return new UserV2(user.getId(),name,user.getBirthDate());
                }).collect(Collectors.toList());
    }
    public User saveUser(User user) {
        user.setId(++userCounter);
        users.add(user);
        return user;
    }
    public User getUser(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElseThrow(()->new UserNotFoundException("User is not found with id:"+id));
    }

    public void deleteUser(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
