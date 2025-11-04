package br.com.coletaresiduos.coleta.repository;

import br.com.coletaresiduos.coleta.model.CollectionPoint;
import br.com.coletaresiduos.coleta.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionPointRepository extends JpaRepository<CollectionPoint, Long> {

    List<CollectionPoint> findByMaterial(Material material);

}
