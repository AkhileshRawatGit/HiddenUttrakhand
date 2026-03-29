package com.akhilesh.project.HiddenUkWeb.service.Hotel;

import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.CreateHotelRequestDTO;
import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.HotelResponseDTO;
import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.UpdateHotelRequestDto;
import com.akhilesh.project.HiddenUkWeb.entity.Hotel;
import com.akhilesh.project.HiddenUkWeb.entity.Place;
import com.akhilesh.project.HiddenUkWeb.exception.ResourceNotFoundException;
import com.akhilesh.project.HiddenUkWeb.repository.HotelRepo;
import com.akhilesh.project.HiddenUkWeb.repository.placeRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {


    private final HotelRepo hotelRepo;
    private final placeRepo placeRepo;
    private final ModelMapper modelMapper;

    @Override
    public HotelResponseDTO createHotel(CreateHotelRequestDTO dto) {

        Place place = placeRepo.findById(dto.getPlaceId())
                .orElseThrow(() -> new ResourceNotFoundException("place not found"));
        Hotel hotel = modelMapper.map(dto, Hotel.class);
        hotel.setPlace(place);
        Hotel updated = hotelRepo.save(hotel);
        return modelMapper.map(updated, HotelResponseDTO.class);
    }

    @Override
    public HotelResponseDTO getHotelById(Long id) {
        Hotel hotel = hotelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel not found by this id"));
        return modelMapper.map(hotel, HotelResponseDTO.class);
    }

    @Override
    public List<HotelResponseDTO> getAllHotelByPlace(Long id) {
        boolean placeExists = placeRepo.existsById(id);
        if (!placeExists) {
            throw new RuntimeException("hotel not found with place id: " + id);
        }
        return hotelRepo.findByPlaceId(id).stream().map(h -> modelMapper.map(h, HotelResponseDTO.class)).toList();
    }

    @Override
    public HotelResponseDTO updateById(Long placeId, Long hotelId, UpdateHotelRequestDto dto) {
        Hotel hotel = hotelRepo.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + hotelId));

        Place place = placeRepo.findById(placeId)
                .orElseThrow(() -> new ResourceNotFoundException("Place not found"));

        modelMapper.map(dto, hotel);
        hotel.setPlace(place);
        hotelRepo.save(hotel);
        return modelMapper.map(hotel, HotelResponseDTO.class);
    }

    @Override
    public void deleteById(Long placeId,Long hotelId) {
        Hotel hotel=hotelRepo.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("hotel not found with id: "+hotelId));
        if (!hotel.getPlace().getId().equals(placeId)) {
            throw new RuntimeException("Hotel does not belong to this place!");
        }
        hotelRepo.deleteById(hotelId);
    }

}
