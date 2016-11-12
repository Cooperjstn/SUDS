package com.theironyard.controllers;

import com.theironyard.entities.Beer;
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
import java.util.ArrayList;

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

//        add a default user: Admin     password: hunter2
        User defaultUser = new User("Admin", PasswordStorage.createHash("hunter2"));
        if (users.findFirstByName(defaultUser.name) == null) {
            users.save(defaultUser);
        }
//        add second default user:  Admin2       password:  hunter2
        User defaultUser2 = new User("Admin2", PasswordStorage.createHash("hunter2"));
        if (users.findFirstByName(defaultUser2.name) == null) {
            users.save(defaultUser2);
        }
//        add third default user:  Admin3       password:  hunter2
        User defaultUser3 = new User("Admin3", PasswordStorage.createHash("hunter2"));
        if (users.findFirstByName(defaultUser3.name) == null) {
            users.save(defaultUser3);
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

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "You're logged out";
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

//    Route to return all of the beers
    @RequestMapping(path = "/suds", method = RequestMethod.GET)
    public ArrayList<Beer> getBeers() {
        ArrayList<Beer> theBeers = (ArrayList<Beer>) beers.findAll();
        return theBeers;
    }

//    Route to add a new beer
    @RequestMapping(path = "/input", method = RequestMethod.POST)
    public ResponseEntity<Beer> addBeer(HttpSession session, @RequestBody Beer beer) {
        String name = (String) session.getAttribute("name");
        if (name == null) {
            return new ResponseEntity<Beer>(HttpStatus.I_AM_A_TEAPOT);
        }

        beer.setUser(users.findFirstByName(name));
        return new ResponseEntity<Beer>(beers.save(beer), HttpStatus.OK);
    }

//    Route to return a single beer
//    @RequestMapping(path = "/singleview", method = RequestMethod.GET)
//    public ResponseEntity<Beer> getABeer(@RequestBody Beer beer) {
//        int id = beer.getId();
//        Beer editBeer = beers.findOne(id);
//
//        return new ResponseEntity<Beer>(beer, HttpStatus.OK);
//    }


}
