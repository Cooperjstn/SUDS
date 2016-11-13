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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
        else {
            defaultUser = users.findFirstByName(defaultUser.name);
        }
//        add second default user:  Admin2       password:  hunter2
        User defaultUser2 = new User("Admin2", PasswordStorage.createHash("hunter2"));
        if (users.findFirstByName(defaultUser2.name) == null) {
            users.save(defaultUser2);
        }
        else {
            defaultUser2 = users.findFirstByName(defaultUser2.name);
        }
//        add third default user:  Admin3       password:  hunter2
        User defaultUser3 = new User("Admin3", PasswordStorage.createHash("hunter2"));
        if (users.findFirstByName(defaultUser3.name) == null) {
            users.save(defaultUser3);
        }
        else {
            defaultUser3 = users.findFirstByName(defaultUser3.name);
        }

//        adding seed data for beer list;

        if (beers.count() == 0) {
            Beer beer = new Beer("Coors", "images/Coors.png", "Coors Brewing Company", "The Original Banquet Beer", 4,
                    Beer.Category.LAGER, defaultUser);
            Beer beer1 = new Beer("Fat Tire", "images/Fat-Tire.jpg", "New Belgium Brewing", "Delicious & Nutritious", 5,
                    Beer.Category.BROWN_ALE, defaultUser2);
            Beer beer2 = new Beer("Julius", "images/Julius.jpg", "Tree House Brewing Company", "Not for me, but others seem to like it.",
                    3, Beer.Category.INDIAN_PALE_ALE, defaultUser3);
            beers.save(beer);
            beers.save(beer1);
            beers.save(beer2);
        }
    }

    @PreDestroy
    public void destroy() {
        h2.stop();
    }


//    Login route;
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(HttpSession session,
                                      @RequestBody User user) throws PasswordStorage.InvalidHashException,
            PasswordStorage.CannotPerformOperationException {

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

//    This route is to add a beer
    @RequestMapping(path = "/input", method = RequestMethod.POST)
    public void addBeer(HttpServletResponse response, HttpSession session, String name, String brewery, String description,
                        Integer rating, Beer.Category category, MultipartFile image) throws Exception {
        String username = (String) session.getAttribute("name");
        if (username == null) {
            throw new Exception("Nope.");
        }

        createBeer(name, brewery, description, rating, category, image, username);

        response.sendRedirect("/");
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Beer> deleteBeer(HttpSession session, @RequestBody Beer beer) {
        String name = (String) session.getAttribute("name");
        if (name == null) {
            return new ResponseEntity<Beer>(HttpStatus.I_AM_A_TEAPOT);
        }

        int id = beer.getId();
        beers.delete(id);
        return new ResponseEntity<Beer>(HttpStatus.OK);
    }

//    Route to return a single beer
//    @RequestMapping(path = "/singleview", method = RequestMethod.GET)
//    public ResponseEntity<Beer> getABeer(@RequestBody Beer beer) {
//        int id = beer.getId();
//        Beer editBeer = beers.findOne(id);
//
//        return new ResponseEntity<Beer>(beer, HttpStatus.OK);
//    }

//    refactored method to create beer;

    public void createBeer(String name, String brewery, String description,
                           Integer rating, Beer.Category category, MultipartFile image, String username) throws IOException {
        File dir = new File("public/images");

        File photoFile = File.createTempFile("image", image.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(photoFile);
        fos.write(image.getBytes());

        Beer beer = new Beer(name, photoFile.getName(), brewery, description, rating, category, users.findFirstByName(username));
        beers.save(beer);
    }


}
