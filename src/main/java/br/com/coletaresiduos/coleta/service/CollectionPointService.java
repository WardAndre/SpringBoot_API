package br.com.coletaresiduos.coleta.service;

import br.com.coletaresiduos.coleta.dto.CollectionPointDisplayDTO;
import br.com.coletaresiduos.coleta.dto.CollectionPointRegisterDTO;
import br.com.coletaresiduos.coleta.exception.CollectionPointNotFoundException;
import br.com.coletaresiduos.coleta.model.CollectionPoint;
import br.com.coletaresiduos.coleta.model.Material;
import br.com.coletaresiduos.coleta.repository.CollectionPointRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollectionPointService {

    @Autowired
    private CollectionPointRepository collectionPointRepository;

    public CollectionPointDisplayDTO save(CollectionPointRegisterDTO collectionPointRegisterDTO) {
        CollectionPoint collectionPoint = new CollectionPoint();
        BeanUtils.copyProperties(collectionPointRegisterDTO, collectionPoint);
        CollectionPoint saved = collectionPointRepository.save(collectionPoint);
        return new CollectionPointDisplayDTO(saved);
    }

    public CollectionPointDisplayDTO getById(Long id) {
        Optional<CollectionPoint> opt = collectionPointRepository.findById(id);
        if (opt.isPresent()) {
            return new CollectionPointDisplayDTO(opt.get());
        } else {
            throw new CollectionPointNotFoundException("Collection Point not found");
        }
    }

    public List<CollectionPointDisplayDTO> getAll() {
        return collectionPointRepository.findAll()
                .stream()
                .map(CollectionPointDisplayDTO::new)
                .toList();
    }

    public List<CollectionPointDisplayDTO> getByMaterial(Material material) {
        return collectionPointRepository.findByMaterial(material)
                .stream()
                .map(CollectionPointDisplayDTO::new)
                .toList();
    }

    public void delete(Long id) {
        Optional<CollectionPoint> opt = collectionPointRepository.findById(id);
        if (opt.isPresent()) {
            collectionPointRepository.delete(opt.get());
        } else {
            throw new CollectionPointNotFoundException("Collection Point not found");
        }
    }

    public CollectionPoint update(CollectionPoint collectionPoint){
        Optional<CollectionPoint> opt = collectionPointRepository.findById(collectionPoint.getId());

        if (opt.isPresent()){
            return collectionPointRepository.save(collectionPoint);
        } else {
            throw new CollectionPointNotFoundException("Collection Point not found");
        }
    }

}
