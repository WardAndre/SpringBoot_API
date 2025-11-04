package br.com.coletaresiduos.coleta.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_collection_points")
@Getter
@Setter
public class CollectionPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @Enumerated(EnumType.STRING)
    private Material material;

}
