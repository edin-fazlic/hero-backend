package com.hero.data;

import com.hero.models.Item;
import com.hero.models.ItemType;
import com.hero.models.Rarity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemTest {

    public static Item item() {
        Item item = new Item();
        item.setId(1L);
        item.setName("King's boots");
        item.setItemType(ItemType.feet);
        item.setRarity(Rarity.epic);
        item.setAttack(true);
        return item;
    }
}
