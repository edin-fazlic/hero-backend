package com.hero.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private long id;
    private String name;
    private Rarity rarity;
    private boolean defense;
    private boolean attack;
    private ItemType itemType;
}
