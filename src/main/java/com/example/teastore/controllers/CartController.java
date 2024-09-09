package com.example.teastore.controllers;

import com.example.teastore.models.CartItem;
import com.example.teastore.models.CustOrder;
import com.example.teastore.models.Item;
import com.example.teastore.repo.CustOrderRepository;
import com.example.teastore.repo.ItemRepository;
import com.example.teastore.services.CartService;
import com.example.teastore.services.TelegramNotificationService;
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
    TelegramNotificationService telegramNotificationService;

    @Autowired
    ItemRepository itemRepository;

    @PostMapping("/add")
    public String addToCart(@RequestParam("itemId") String itemId,@RequestParam("image") String image,
                            @RequestParam("title") String title,@RequestParam("sort") String sort,
                            @RequestParam int quantity,@RequestParam float basePrice, HttpSession session) {
        CartItem cartItem = new CartItem(itemId, quantity, basePrice,image,title,sort);
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        cart.add(cartItem);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @PostMapping("/delete")
    public String removeFromCart(@RequestParam("itemId") String itemId,HttpSession session){
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart != null) {
            cart.removeIf(item -> item.getItemId().equals(itemId));
            session.setAttribute("cart", cart);
        }
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
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            return "redirect:/cart";
        }else{
            float price = 0.0F;
            String id_items = " ";
            description="опис:" + description;
                for (CartItem itemc : cart) {
                    price += itemc.getPrice();
                    id_items = itemc.getItemId() + " , " + id_items;
                    description = itemc.getTitle() + "-"+ itemc.getQuantity() +" ; "+ description;
                }

            CustOrder ordr = new CustOrder(id_items,description,customer_number,customer_email,customer_name,customer_surname,city,post_dep,price);
            custOrderRepository.save(ordr);
            telegramNotificationService.sendOrderNotification("Нове замовлення: " + ordr.getId() +
                    " \nІм'я: " + ordr.getCustomer_name() + " " + ordr.getCustomer_surname() +
                    " \n Опис: " + ordr.getDescription() +
                    " \n Номер телефону:" + ordr.getCustomer_number()
                    +" \n Сума:" + ordr.getPrice());
            session.invalidate();
            return "redirect:/thankyou";
        }

    }
}
