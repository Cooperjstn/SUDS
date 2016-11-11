package com.theironyard.controllers;

import com.theironyard.entities.User;
import com.theironyard.services.BeerRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utilities.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Created by stevenburris on 11/10/16.
 */
@RestController
public class SudsRestController {
    @Autowired
    BeerRepository beers;

    @Autowired
    UserRepository users;

    Server h2;

    @PostConstruct
    public void init() throws PasswordStorage.CannotPerformOperationException, SQLException {
//        start web server
        h2 = Server.createWebServer().start();

//        add a default user
        User defaultUser = new User("Admin", PasswordStorage.createHash("hunter2"));
        if (users.findFirstByName(defaultUser.name) == null) {
            users.save(defaultUser);
        }
    }

    @PreDestroy
    public void destroy() {
        h2.stop();
    }


//    Login route;
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(HttpSession session, @RequestBody User user) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {
        User userFromH2 = users.findFirstByName(user.getName());
        if (userFromH2 == null) {
            return new ResponseEntity<User>(HttpStatus.I_AM_A_TEAPOT);
        }
        else if(!PasswordStorage.verifyPassword(user.getPassword(), userFromH2.getPassword())){
            return new ResponseEntity<User>(HttpStatus.I_AM_A_TEAPOT);
        }

        session.setAttribute("name", user.getName());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

//    Route to create a new user
    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(HttpSession session, @RequestBody User user) throws PasswordStorage.CannotPerformOperationException, PasswordStorage.InvalidHashException {
        User userFromH2 = users.findFirstByName(user.getName());
        if (userFromH2 == null) {
            user.setPassword(PasswordStorage.createHash(user.getPassword()));
            user.setName(user.getName());
            users.save(user);
        }
        else {
            return new ResponseEntity<User>(HttpStatus.I_AM_A_TEAPOT);
        }

        session.setAttribute("name", user.getName());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
//      Route to return current user object
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public User getUser(HttpSession session) {
        String name = (String) session.getAttribute("name");
        return users.findFirstByName(name);
    }


}
