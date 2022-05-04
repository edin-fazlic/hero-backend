package com.hero.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "hero", referencedColumnName = "id")
    private Hero hero;

    @Column(name = "name")
    private String name;

    @Column(name = "rarity")
    @Enumerated(value = EnumType.STRING)
    private Rarity rarity;

    @Column(name = "defense", nullable = false)
    private boolean defense;

    @Column(name = "attack", nullable = false)
    private boolean attack;

    @Column(name = "item_type")
    @Enumerated(value = EnumType.STRING)
    private ItemType itemType;
}
