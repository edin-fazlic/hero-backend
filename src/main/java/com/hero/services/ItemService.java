package com.hero.services;

import com.hero.models.Item;
import com.hero.models.ItemType;
import com.hero.models.Rarity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final List<Item> resultList;

    public ItemService() {
        resultList = new ArrayList<>();
        resultList.add(generateKingsHelmet());
        resultList.add(generatePrincesGloves());

    }

    public List<Item> getItems() {
        return resultList;
    }

    public Item getById(long id) {
        for (Item item : resultList) {
            if(item.getId() == id) {
                return item;
            }
        }
        throw new RuntimeException("Value not found provided id:" + id);
    }

    public Item create(Item model) {
        long id = resultList.size() + 1;
        model.setId(id);
        resultList.add(model);
        return model;
    }

    public Item update(Item model, long id) {
        return null;
    }

    public void delete(long id) {

    }

    private Item generateKingsHelmet() {
        Item item = new Item();
        item.setId(1);
        item.setName("King's helmet");
        item.setAttack(true);
        item.setRarity(Rarity.legendary);
        item.setItemType(ItemType.head);
        return item;
    }

    private Item generatePrincesGloves() {
        Item item = new Item();
        item.setId(2);
        item.setName("Prince Varyan leather gloves");
        item.setDefense(true);
        item.setRarity(Rarity.rare);
        item.setItemType(ItemType.hands);
        return item;
    }
}
