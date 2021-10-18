package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "shoe")
public class ShoeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private BigInteger size;

    @Column
    private String color;

    @Column
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name="id_stock", referencedColumnName = "id")
    private StockEntity stock;

    @Override
    public String toString() {
        return "Shoe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", color=" + color +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoeEntity that = (ShoeEntity) o;
        return name.equals(that.name) && size.equals(that.size) && color.equals(that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, color);
    }
}
