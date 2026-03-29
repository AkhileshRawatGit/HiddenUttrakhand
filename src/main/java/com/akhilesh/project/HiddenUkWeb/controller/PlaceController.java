package com.akhilesh.project.HiddenUkWeb.controller;

import com.akhilesh.project.HiddenUkWeb.dto.placeDto.CreatePlaceRequestDTO;
import com.akhilesh.project.HiddenUkWeb.dto.placeDto.PlaceResponseDTO;
import com.akhilesh.project.HiddenUkWeb.dto.placeDto.UpdatePlaceRequestDTO;
import com.akhilesh.project.HiddenUkWeb.service.place.placeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/places")
@RequiredArgsConstructor
public class PlaceController {

    private final placeService service;

    //create places
    @PostMapping("/create")
    public ResponseEntity<?>createPlace(@RequestBody CreatePlaceRequestDTO dto){
        PlaceResponseDTO dto1=service.createPlace(dto);
        return ResponseEntity.ok(dto1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getPlacesById(id));
    }

    @GetMapping
    public ResponseEntity<?>getAllPlaces(){
        return ResponseEntity.ok(service.getAllPlaces());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateById(@PathVariable Long id, @RequestBody UpdatePlaceRequestDTO dto){
        return ResponseEntity.ok(service.updatePlace(id,dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlaceById(@PathVariable Long id){
        service.deletePlace(id);
        return ResponseEntity.noContent().build();
    }

}
