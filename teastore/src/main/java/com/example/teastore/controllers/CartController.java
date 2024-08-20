package com.example.teastore.controllers;

import com.example.teastore.models.Item;
import com.example.teastore.services.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/add")
    public String addToCart(@RequestParam("itemId") String itemId, HttpSession session) {
        cartService.addItemToCart(itemId, session);
        return "redirect:/cart";
    }
}
