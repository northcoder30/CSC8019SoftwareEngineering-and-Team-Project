package com.ebooksystem.ebookmanagementsystem.mappers;

import com.ebooksystem.ebookmanagementsystem.dtos.BorrowRecordDto;
import com.ebooksystem.ebookmanagementsystem.entities.BorrowRecord;
import org.mapstruct.Mapper;

/**
 * Mapper for the BorrowRecord class. Translates a BorrowRecord into its DTO Object.
 */
@Mapper(componentModel = "spring")
public interface BorrowRecordMapper {
    public BorrowRecordDto toDto(BorrowRecord borrowRecord);

}
