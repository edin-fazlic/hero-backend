package com.hero.controllers;

import com.hero.models.Item;
import com.hero.models.ItemType;
import com.hero.models.Rarity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private List<Item> resultList;

    public ItemController() {
        resultList = new ArrayList<>();
        resultList.add(generateKingsHelmet());
        resultList.add(generatePrincesGloves());
    }

    @GetMapping
    public List<Item> getItems() {
        return resultList;
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable long id) {
        for (Item item : resultList) {
            if(item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    // CRUD
    // Create Read Update Delete
    // Post   Get  Put    Delete


    @PostMapping
    public Item createItem(@RequestBody Item item) {
        long id = resultList.size() + 1;
        item.setId(id);
        resultList.add(item);
        return item;
    }

    private Item generateKingsHelmet() {
        Item item = new Item();
        item.setId(1);
        item.setName("King's helmet");
        item.setAttack(true);
        item.setRarity(Rarity.legendary);
        item.setItemType(ItemType.helmet);
        return item;
    }

    private Item generatePrincesGloves() {
        Item item = new Item();
        item.setId(2);
        item.setName("Prince Varyan leather gloves");
        item.setDefense(true);
        item.setRarity(Rarity.rare);
        item.setItemType(ItemType.gloves);
        return item;
    }
}
