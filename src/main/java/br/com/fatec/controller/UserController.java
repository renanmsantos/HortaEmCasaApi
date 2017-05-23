package br.com.fatec.controller;

import br.com.fatec.domain.User;
import br.com.fatec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method= RequestMethod.GET, value="/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(method=RequestMethod.GET, value="/{userId}")
    public User getUserById( @PathVariable Long userId ){
        User user = userService.getUserById(userId);
        return user;
    }

    @RequestMapping(method = RequestMethod.POST)
    public User newUser(@RequestBody @Valid User user){
        userService.newUser(user);
        return user;
    }

    @RequestMapping(method=RequestMethod.PUT, value="/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody @Valid User user ){
        user.setUserId(userId);
        userService.updateUser(user);
        return user;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/{userId}")
    public void deleleUserById( @PathVariable Long userId ){
        userService.deleteUserById(userId);
    }

}
