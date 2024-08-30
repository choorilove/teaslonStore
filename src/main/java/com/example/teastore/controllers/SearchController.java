package com.example.teastore.controllers;

import com.example.teastore.models.Item;
import com.example.teastore.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private ItemRepository itemRepository;



    @GetMapping("/search")
    public String search(@RequestParam(value = "query", required = false) String query, Model model) {
        if (query == null || query.trim().isEmpty()) {
            model.addAttribute("searchResults", Collections.emptyList());
        } else {
            List<Item> searchResults = itemRepository.findByDescriptionContainingIgnoreCase(query.trim());
            model.addAttribute("searchResults", searchResults);
        }
        return "search-results";
    }

}
