package com.akhilesh.project.HiddenUkWeb.service.place;

import com.akhilesh.project.HiddenUkWeb.dto.placeDto.CreatePlaceRequestDTO;
import com.akhilesh.project.HiddenUkWeb.dto.placeDto.PlaceResponseDTO;
import com.akhilesh.project.HiddenUkWeb.dto.placeDto.UpdatePlaceRequestDTO;
import com.akhilesh.project.HiddenUkWeb.entity.Place;
import com.akhilesh.project.HiddenUkWeb.exception.ResourceNotFoundException;
import com.akhilesh.project.HiddenUkWeb.repository.placeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class placeServiceImpl implements placeService {

    private final placeRepo repo;
    private final ModelMapper modelMapper;

    @Override
    public PlaceResponseDTO createPlace(CreatePlaceRequestDTO placeDto) {
        log.info("Creating a new place with name: {}",placeDto.getName());
        Place places=modelMapper.map(placeDto,Place.class);
        places=repo.save(places);
        return modelMapper.map(places,PlaceResponseDTO.class);

    }


    @Override
    public PlaceResponseDTO getPlacesById(Long id) {
        log.info("gettting place by id");
        Place places=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Place not found"+id));
        PlaceResponseDTO dto=modelMapper.map(places, PlaceResponseDTO.class);
        return dto;
    }

    @Override
    public List<PlaceResponseDTO> getAllPlaces() {
        List<Place>places=repo.findAll();

        return places.stream().map(a->modelMapper.map(a,PlaceResponseDTO.class)).toList();
    }

    @Override
    public PlaceResponseDTO updatePlace(Long id, UpdatePlaceRequestDTO dto) {
        Place place=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("place is not found"));
        modelMapper.map(dto,place);
        place.setId(id);
        Place updated=repo.save(place);
        return modelMapper.map(updated,PlaceResponseDTO.class);
    }

    @Override
    public void deletePlace(Long id) {
        Place place=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("place not found"));
        repo.delete(place);
    }

}
