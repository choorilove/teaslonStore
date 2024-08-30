package com.example.teastore.controllers;

import com.example.teastore.models.Item;
import com.example.teastore.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<Item> searchResults = itemRepository.findByDescriptionContainingIgnoreCase(query);
        model.addAttribute("searchResults", searchResults);
        return "search-results";
    }
}
