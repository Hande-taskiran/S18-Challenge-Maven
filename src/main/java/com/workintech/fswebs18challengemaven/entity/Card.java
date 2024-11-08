package com.workintech.fswebs18challengemaven.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "card", schema="public")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer value;
    @Enumerated(EnumType.STRING)
    private Color color;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Card(Integer value, Type type, Color color) {
        if ((value != null && type != null) || (value == null && type == null)) {
            throw new IllegalArgumentException("Either value or type must be provided, not both.");
        }
        if (type == Type.JOKER) {
            if (value != null || color != null) {
                throw new IllegalArgumentException("JOKER type must have null value and color.");
            }
        }
        this.value = value;
        this.type = type;
        this.color = color;
    }

}
