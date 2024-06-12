package com.br.bird_service.mapper;

import com.br.bird_service.dto.UserCreateDTO;
import com.br.bird_service.dto.UserDTO;
import com.br.bird_service.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserDTO map(UserEntity source){
        return modelMapper.map(source, UserDTO.class);
    }

    public UserEntity map(UserCreateDTO source){
        return modelMapper.map(source, UserEntity.class);
    }
}
