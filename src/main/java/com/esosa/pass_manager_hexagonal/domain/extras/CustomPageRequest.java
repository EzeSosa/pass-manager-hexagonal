package com.esosa.pass_manager_hexagonal.domain.extras;

public record CustomPageRequest(
        int pageNumber,
        int pageSize,
        String sortBy
) {}