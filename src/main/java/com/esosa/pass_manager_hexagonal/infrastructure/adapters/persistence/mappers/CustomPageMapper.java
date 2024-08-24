package com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.mappers;

import com.esosa.pass_manager_hexagonal.domain.extras.CustomPage;
import org.springframework.data.domain.Page;

public class CustomPageMapper {

    public static <T> CustomPage buildCustomPage(Page<T> springPage) {
        return CustomPage.builder()
                .content(springPage.getContent())
                .totalElements(springPage.getTotalElements())
                .totalPages(springPage.getTotalPages())
                .pageNumber(springPage.getNumber())
                .build();
    }

}