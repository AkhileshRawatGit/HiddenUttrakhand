package com.akhilesh.project.HiddenUkWeb.service.place;

import com.akhilesh.project.HiddenUkWeb.dto.placeDto.CreatePlaceRequestDTO;
import com.akhilesh.project.HiddenUkWeb.dto.placeDto.PlaceResponseDTO;
import com.akhilesh.project.HiddenUkWeb.dto.placeDto.UpdatePlaceRequestDTO;

import java.util.List;

public interface placeService {

    PlaceResponseDTO createPlace(CreatePlaceRequestDTO placeDto);
    PlaceResponseDTO getPlacesById(Long id);
    List<PlaceResponseDTO> getAllPlaces();
    PlaceResponseDTO updatePlace(Long id, UpdatePlaceRequestDTO dto);

    void deletePlace(Long id);


}
