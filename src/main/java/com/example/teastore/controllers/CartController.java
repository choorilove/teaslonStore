package com.example.teastore.controllers;

import com.example.teastore.models.CustOrder;
import com.example.teastore.models.Item;
import com.example.teastore.repo.CustOrderRepository;
import com.example.teastore.repo.ItemRepository;
import com.example.teastore.services.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    CustOrderRepository custOrderRepository;

    @Autowired
    ItemRepository itemRepository;

    @PostMapping("/add")
    public String addToCart(@RequestParam("itemId") String itemId, HttpSession session) {
        cartService.addItemToCart(itemId, session);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String confOrder(@RequestParam("customer_name") String customer_name,
                            @RequestParam("customer_surname") String customer_surname,
                            @RequestParam("customer_number") String customer_number,
                            @RequestParam("customer_email") String customer_email,
                            @RequestParam("city") String city,
                            @RequestParam("post_dep") String post_dep,
                            @RequestParam(value = "description", defaultValue = "нема опису") String description,
                            HttpSession session){
        List<String> cart = (List<String>) session.getAttribute("cart");
        if (cart == null) {
            return "redirect:/cart";
        }else{
            float price = 0;
            String id_items = " ";
            for (String id : cart) {
                Item item = itemRepository.findById(Long.parseLong(id));
                price+= item.getPrice();
                id_items = id + " , " + id_items;
            }
            CustOrder ordr = new CustOrder(id_items,description,customer_number,customer_email,customer_name,customer_surname,city,post_dep,price);
            custOrderRepository.save(ordr);
            return "redirect:/";
        }

    }
}
