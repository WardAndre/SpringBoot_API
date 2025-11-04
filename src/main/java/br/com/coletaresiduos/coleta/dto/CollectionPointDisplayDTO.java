package br.com.coletaresiduos.coleta.dto;

import br.com.coletaresiduos.coleta.model.CollectionPoint;

public record CollectionPointDisplayDTO(
        Long id,
        String address,
        String material
) {

    public CollectionPointDisplayDTO(CollectionPoint collectionPoint) {
        this(collectionPoint.getId(), collectionPoint.getAddress(), String.valueOf(collectionPoint.getMaterial()));
    }

}
