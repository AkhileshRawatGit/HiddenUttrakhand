package com.akhilesh.project.HiddenUkWeb.config;

import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.CreateHotelRequestDTO;
import com.akhilesh.project.HiddenUkWeb.dto.HotelDto.HotelResponseDTO;
import com.akhilesh.project.HiddenUkWeb.dto.RoomDto.CreateRoomRequestDto;
import com.akhilesh.project.HiddenUkWeb.dto.placeDto.PlaceResponseDTO;
import com.akhilesh.project.HiddenUkWeb.entity.Hotel;
import com.akhilesh.project.HiddenUkWeb.entity.Place;
import com.akhilesh.project.HiddenUkWeb.entity.Room;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(CreateHotelRequestDTO.class, Hotel.class)
                .addMappings(mapper -> {
                    mapper.skip(Hotel::setId);
                });
        modelMapper.typeMap(CreateRoomRequestDto.class, Room.class)
                .addMappings(mapper -> {
                    mapper.skip(Room::setId);
                });
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setAmbiguityIgnored(true);
        return modelMapper;
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
}
