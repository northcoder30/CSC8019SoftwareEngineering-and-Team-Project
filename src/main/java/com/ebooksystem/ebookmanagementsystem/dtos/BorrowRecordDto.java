package com.ebooksystem.ebookmanagementsystem.dtos;

import com.ebooksystem.ebookmanagementsystem.entities.BorrowStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Strucutre of the BorrowRecordDTO
 */
@Data
public class BorrowRecordDto {
    private Long id;
    private UserDto user;
    private EbookDto ebook;
    private BigDecimal price;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private BorrowStatus status;
}
