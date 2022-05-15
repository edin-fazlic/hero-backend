package com.hero.controllers;

import com.hero.models.Item;
import com.hero.services.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable long id) {
        return itemService.getById(id);
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.create(item);
    }

    @PutMapping("/{id}")
    public Item editItem(@PathVariable long id, @RequestBody Item item) {
        return itemService.update(item, id);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable long id) {
        itemService.delete(id);
    }

}
