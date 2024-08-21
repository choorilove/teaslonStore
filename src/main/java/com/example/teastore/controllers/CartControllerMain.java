package com.example.teastore.controllers;


import com.example.teastore.models.Item;
import com.example.teastore.repo.ItemRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CartControllerMain {


    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model){
        List<String> cart = (List<String>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        List<Item> items = new ArrayList<>();
        for (String id : cart) {
            Long itemId = Long.parseLong(id);
            Optional<Item> itemOptional = itemRepository.findById(itemId);
            itemOptional.ifPresent(items::add);
        }
        if (cart.isEmpty()) {
            items.clear();
        }
        session.setMaxInactiveInterval(1800);
        model.addAttribute("items", items);
        return "cart";
    }



}
