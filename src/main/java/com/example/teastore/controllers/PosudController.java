package com.example.teastore.controllers;

import com.example.teastore.models.Item;
import com.example.teastore.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class PosudController {


    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/posud")
    public String posud(Model model){
        List<Item> items = itemRepository.findAllSorted();
        Map<String, List<Item>> itemsByCategory = items.stream()
                .collect(Collectors.groupingBy(Item::getSort));
        model.addAttribute("itemsByCategory", itemsByCategory);
        return "posud";
    }

}
