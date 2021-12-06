package com.isimtl.waitingline.mapper;


import com.isimtl.waitingline.dto.StoreDTO;
import com.isimtl.waitingline.entity.Store;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface StoreMapper {

    StoreDTO toDto(Store entity);

    List<StoreDTO> toDto(List<Store> products);

    Store toEntity(StoreDTO dto);
}
