package leader.ctrl;

import leader.service.UserService;
import leader.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by leader on 17/9/3.
 */
@RestController
public class MyCtrl {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public String getUser(@PathVariable long userId){
        User user = userService.getUser(userId);
        return user.toString();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String createUser(@RequestBody String userName, @RequestBody String pwd, @RequestBody boolean enabled, @RequestBody Date regDate) {
        boolean successful = userService.createUser(userName,pwd,enabled,regDate);
        return "{responseCode:'200',responseMsg:'"+(successful?"success":"fail")+"'}";
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable long userId){
        boolean successful = userService.deleteUser(userId);
        return "{responseCode:'200',responseMsg:'"+(successful?"success":"fail")+"'}";
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
    public String disableUser(@PathVariable long userId, @RequestBody String userName, @RequestBody boolean enabled, @RequestBody String pwd)
    {
        boolean successful = userService.disableUser(userId,userName,enabled,pwd);
        return "{responseCode:'200',responseMsg:'"+(successful?"success":"fail")+"'}";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String queryUsers(@RequestParam String userNamePrex, @RequestParam boolean onlyValidUser)
    {
        List<User> users = userService.queryUsers(userNamePrex,onlyValidUser);
        return users.toString();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody String userName, @RequestBody String md5EncodedPassword){
        UserSession userSession = userService.login(userName,md5EncodedPassword);
        return userSession.toString();
    }
}
