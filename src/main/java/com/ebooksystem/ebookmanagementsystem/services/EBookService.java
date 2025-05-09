package com.ebooksystem.ebookmanagementsystem.services;

import com.ebooksystem.ebookmanagementsystem.dtos.EbookDto;
import com.ebooksystem.ebookmanagementsystem.exceptions.CategoryNotFoundException;
import com.ebooksystem.ebookmanagementsystem.exceptions.EbookNotFoundException;
import com.ebooksystem.ebookmanagementsystem.exceptions.EbookUnavailableException;
import com.ebooksystem.ebookmanagementsystem.mappers.EbookMapper;
import com.ebooksystem.ebookmanagementsystem.repositories.CategoryRepository;
import com.ebooksystem.ebookmanagementsystem.repositories.EbookRepository;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
/**
 * EBookService class implements the logic required to process the frontend requests received by the controller
 */
@AllArgsConstructor
@Service
public class EBookService {
    private final EbookRepository ebookRepository;
    private final CategoryRepository categoryRepository;
    private final EbookMapper ebookMapper;

    /**
     * Updates the eBook object based on the request and updates the data in the database
     * @param id
     * @param ebookDto
     * @return
     */
    public EbookDto updateEbook(Long id, EbookDto ebookDto) {
        var category = categoryRepository.findById(ebookDto.getCategoryId()).orElse(null);
        if (category == null) {
            throw new CategoryNotFoundException("Category not found");
        }

        var ebook = ebookRepository.findById(id).orElse(null);
        if (ebook == null) {
            throw new EbookNotFoundException("eBook not found");
        }

        ebookMapper.update(ebookDto, ebook);
        ebook.setCategory(category);
        ebookRepository.save(ebook);
        ebookDto.setId(ebook.getId());

        return ebookDto;
    }

    /**
     * Handles changing the value of the books of this kind available to borrow when one is borrowed
     * If there is none, throws an error. If there are enough books in stock, updates the value.
     * @param id
     *
     */
    public void borrowBook(Long id) {
        var ebook = ebookRepository.findById(id).orElse(null);
        if (ebook == null) {
            throw new EbookNotFoundException("eBook not found");
        }
        int currentQuantity = ebook.getAvailableQuantity();
        if(currentQuantity < 1) {
            throw new EbookUnavailableException("This eBook is currently unavailable to borrow, check back later");
        }
        int newQuantity = currentQuantity - 1;
        ebook.setAvailableQuantity(newQuantity);
        ebookRepository.save(ebook);
    }

    /**
     * Handles changing the value of the books of this kind available to borrow when an eBook has been returned.
     * Increases the available value by 1
     * @param id
     */
    public void returnBook(Long id) {
        var ebook = ebookRepository.findById(id).orElse(null);
        if (ebook == null) {
            throw new EbookNotFoundException("eBook not found");
        }
        int currentQuantity = ebook.getAvailableQuantity();
        int newQuantity = currentQuantity + 1;
        ebook.setAvailableQuantity(newQuantity);
        ebookRepository.save(ebook);
    }
}

