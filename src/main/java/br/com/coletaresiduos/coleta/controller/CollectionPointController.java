package br.com.coletaresiduos.coleta.controller;

import br.com.coletaresiduos.coleta.dto.CollectionPointDisplayDTO;
import br.com.coletaresiduos.coleta.dto.CollectionPointRegisterDTO;
import br.com.coletaresiduos.coleta.model.CollectionPoint;
import br.com.coletaresiduos.coleta.model.Material;
import br.com.coletaresiduos.coleta.service.CollectionPointService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CollectionPointController {

    @Autowired
    private CollectionPointService collectionPointService;

    @PostMapping("/collection-point")
    @ResponseStatus(HttpStatus.CREATED)
    public CollectionPointDisplayDTO saveUser(@RequestBody @Valid CollectionPointRegisterDTO collectionPointRegisterDTO) {
        return collectionPointService.save(collectionPointRegisterDTO);
    }

    @GetMapping("/collection-point")
    @ResponseStatus(HttpStatus.OK)
    public List<CollectionPointDisplayDTO> getAll() {
        return collectionPointService.getAll();
    }

    @GetMapping("/collection-point/{id}")
    public ResponseEntity<CollectionPointDisplayDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(collectionPointService.getById(id));
    }

    @GetMapping("/collection-point/{material}")
    public List<CollectionPointDisplayDTO> getByMaterial(Material material) {
        return collectionPointService.getByMaterial(material);
    }

    @DeleteMapping("/collection-point/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        collectionPointService.delete(id);
    }

    @PutMapping("/collection-point")
    @ResponseStatus(HttpStatus.OK)
    public CollectionPoint update(@RequestBody CollectionPoint collectionPoint){
        return collectionPointService.update(collectionPoint);
    }

}
