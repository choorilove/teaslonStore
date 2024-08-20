package com.example.teastore.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class CartControllerMain {


    @GetMapping("/cart")
    public String cart(HttpSession session){
        List<String> cart = (List<String>) session.getAttribute("cart");
        return "cart";
    }

}
