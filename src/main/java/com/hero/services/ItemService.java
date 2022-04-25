package com.hero.services;

import com.hero.models.Item;
import com.hero.models.ItemType;
import com.hero.models.Rarity;
import com.hero.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final List<Item> resultList;

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        resultList = new ArrayList<>();
        resultList.add(generateKingsHelmet());
        resultList.add(generatePrincesGloves());

    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item getById(long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(itemOptional.isPresent()) {
            return itemOptional.get();
        }

        throw new RuntimeException("Item with id:" + id + " does not exist!");
    }

    public Item create(Item model) {
        return itemRepository.save(model);
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
