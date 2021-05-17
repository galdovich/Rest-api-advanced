package com.galdovich.esm.dto;

import com.galdovich.esm.exception.MessageKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO {

    @Min(value = 1, message = MessageKey.INCORRECT_PAGE_NUMBER)
    private int page;
    @Min(value = 1, message = MessageKey.INCORRECT_PAGE_SIZE)
    @Max(value = 50, message = MessageKey.INCORRECT_PAGE_SIZE)
    private int size;
}
