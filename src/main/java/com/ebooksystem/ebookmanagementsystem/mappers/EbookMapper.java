package com.ebooksystem.ebookmanagementsystem.mappers;

import com.ebooksystem.ebookmanagementsystem.dtos.EbookDto;
import com.ebooksystem.ebookmanagementsystem.entities.Ebook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper for the Ebook class.
 */
@Mapper(componentModel = "spring")
public interface EbookMapper {
    /**
     * Translates and eBook object into a DTO object.
     * @param ebook
     * @return
     */
    @Mapping(target = "categoryId", source = "category.id")
    EbookDto toDto(Ebook ebook);

    /**
     * Translates an EBookDTO into an Ebook object
     * @param ebookDto
     * @return
     */
    Ebook toEntity(EbookDto ebookDto);

    /**
     * Updates the ebook object and the ebookDTO
     * @param ebookDto
     * @param ebook
     */
    @Mapping(target = "id", ignore = true)
    void update(EbookDto ebookDto, @MappingTarget Ebook ebook);

}
