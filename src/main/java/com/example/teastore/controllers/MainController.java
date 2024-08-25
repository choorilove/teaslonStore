package com.example.teastore.controllers;

import com.example.teastore.models.Item;
import com.example.teastore.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Item> items = itemRepository.findAllSorted();
        Map<String, List<Item>> itemsByCategory = items.stream()
                .collect(Collectors.groupingBy(Item::getSort));

        model.addAttribute("itemsByCategory", itemsByCategory);
        return "index";
    }

    @GetMapping("/about-us")
    public String about() {
        return "about-us";
    }

    @GetMapping("/thankyou")
    public String thankyou(Model model) {
        List<Item> items = itemRepository.findAllSorted();
        Map<String, List<Item>> itemsByCategory = items.stream()
                .collect(Collectors.groupingBy(Item::getSort));

        model.addAttribute("itemsByCategory", itemsByCategory);

        return "thankyou";
    }

    @GetMapping("/item/{id}")
    public String teaKind(@PathVariable("id") String id, Model model) {
        List<Item> items = itemRepository.findAllSorted();
        Map<String, List<Item>> itemsByCategory = items.stream()
                .collect(Collectors.groupingBy(Item::getSort));

        model.addAttribute("itemsByCategory", itemsByCategory);
        Item item = itemRepository.findById(Long.parseLong(id));
        model.addAttribute("current_item",item);
        model.addAttribute("itemId", id);
        return "item-view";
    }

}
