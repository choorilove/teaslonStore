package com.example.teastore.controllers;


import com.example.teastore.models.CartItem;
import com.example.teastore.models.Item;
import com.example.teastore.repo.ItemRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartControllerMain {


    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model){
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }
        float totalAmount = 0.0F;
                List<Item> items = new ArrayList<>();
        if (cart != null) {
            for (CartItem item : cart) {
                totalAmount+= item.getPrice();
            }
        }

        session.setMaxInactiveInterval(1800);
        model.addAttribute("cart", cart);
        model.addAttribute("totalAmount", totalAmount);
        return "cart";
    }




}
