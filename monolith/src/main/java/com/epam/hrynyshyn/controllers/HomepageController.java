package com.epam.hrynyshyn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author by Roman Hrynyshyn
 * Created on 2019-04-16.
 */
@Controller
public class HomepageController {
    @GetMapping("home")
    String getIndexPage() {
        return "home";
    }
}
