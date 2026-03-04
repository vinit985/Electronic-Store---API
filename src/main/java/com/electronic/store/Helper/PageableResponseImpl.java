package com.electronic.store.Helper;

import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.dtos.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PageableResponseImpl {


    public <U, V> PageableResponse<V> getPageableResponse(Page<U> listOfUserPageable, Class<V> type) {

        ModelMapper modelMapper = new ModelMapper();

        PageableResponse<V> listOfPageResponse =
                PageableResponse.<V>builder()
                        .content(
                                listOfUserPageable.getContent()
                                        .stream()
                                        .map(user -> modelMapper.map(user, type))
                                        .collect(Collectors.toList())
                        )
                        .pageNo(listOfUserPageable.getNumber())
                        .pageSize(listOfUserPageable.getSize())
                        .totalPages(listOfUserPageable.getTotalPages())
                        .totalElements((int)listOfUserPageable.getTotalElements())
                        .lastPage(listOfUserPageable.isLast())
                        .build();

        return listOfPageResponse;
    }




}
