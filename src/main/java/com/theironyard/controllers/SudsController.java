package com.theironyard.controllers;

import com.theironyard.services.BeerRepository;
import com.theironyard.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by stevenburris on 11/10/16.
 */
@Controller
public class SudsController {

    @Autowired
    UserRepository users;

    @Autowired
    BeerRepository beers;

}
