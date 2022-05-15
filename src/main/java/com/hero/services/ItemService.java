package com.hero.services;

import com.hero.models.Item;
import com.hero.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item getById(long id) {
        return getEntity(id);
    }

    public Item create(Item model) {
        return itemRepository.save(model);
    }

    public Item update(Item model, long id) {
        getEntity(id);

        model.setId(id);
        return itemRepository.save(model);
    }

    public void delete(long id) {
        itemRepository.deleteById(id);
    }

    private Item getEntity(long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(itemOptional.isPresent()) {
            return itemOptional.get();
        }

        throw new RuntimeException("Item with id:" + id + " does not exist!");
    }

}
