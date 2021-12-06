package com.isimtl.waitingline.mapper;

import com.isimtl.waitingline.dto.StoreDTO;
import com.isimtl.waitingline.dto.UserDTO;
import com.isimtl.waitingline.entity.Store;
import com.isimtl.waitingline.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User entity);

    List<UserDTO> toDto(List<User> products);

    Store toEntity(UserDTO dto);
}
