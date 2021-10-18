package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "stock")
public class StockEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER, mappedBy="stock")
    private Set<ShoeEntity> stockList;

    @Column
    private Integer stockTotal;

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", stockList=" + stockList +
                ", stockTotal=" + stockTotal +
                '}';
    }


}
