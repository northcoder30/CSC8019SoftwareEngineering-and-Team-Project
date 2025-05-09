package com.ebooksystem.ebookmanagementsystem.controllers;

import com.ebooksystem.ebookmanagementsystem.dtos.EbookDto;
import com.ebooksystem.ebookmanagementsystem.entities.Ebook;
import com.ebooksystem.ebookmanagementsystem.exceptions.EbookNotFoundException;
import com.ebooksystem.ebookmanagementsystem.mappers.EbookMapper;
import com.ebooksystem.ebookmanagementsystem.repositories.CategoryRepository;
import com.ebooksystem.ebookmanagementsystem.repositories.EbookRepository;
import com.ebooksystem.ebookmanagementsystem.services.EBookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
/**
 * Controller for ebooks. Allows the frontend to send requests regarding ebooks by exposing the nodes
 * and performs operations in the backend and database layers based on those requests. Sends appropriate responses.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/ebooks")
public class EbookController {

    private final EbookRepository ebookRepository;
    private final EbookMapper ebookMapper;
    private final CategoryRepository categoryRepository;
    private final EBookService ebookService;

    /**
     * Gets all ebooks of specific categoryId if provided, if not provided gets all of the ebooks in the database
     * @param categoryId
     * @return
     */
    @GetMapping("/get-all")
    public List<EbookDto> getAllProducts(
            @RequestParam(name = "categoryId", required = false) Byte categoryId
    ) {
        List<Ebook> products;
        if (categoryId != null) {
            products = ebookRepository.findByCategoryId(categoryId);
        }
        else {
            products = ebookRepository.findAllWithCategory();
        }
        return products.stream().map(ebookMapper::toDto).toList();
    }

    /**
     * Gets a specific ebook by its id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<EbookDto> getProduct(@PathVariable Long id) {
        var product = ebookRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ebookMapper.toDto(product));
    }

    /**
     * Creates a new ebook from the request body
     * @param ebookDto
     * @param uriBuilder
     * @return
     */
    @PostMapping
    public ResponseEntity<EbookDto> createEbook(
            @RequestBody EbookDto ebookDto,
            UriComponentsBuilder uriBuilder) {
        var category = categoryRepository.findById(ebookDto.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        var ebook = ebookMapper.toEntity(ebookDto);
        ebook.setCategory(category);
        ebookRepository.save(ebook);
        ebookDto.setId(ebook.getId());

        var uri = uriBuilder.path("/products/{id}").buildAndExpand(ebook.getId()).toUri();
        return ResponseEntity.created(uri).body(ebookDto);
    }

    /**
     * Updates the ebook based on the request body
     * @param id
     * @param ebookDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<EbookDto> updateEbook(
            @PathVariable Long id,
            @RequestBody EbookDto ebookDto) {
        var ebook = ebookService.updateEbook(id, ebookDto);

        return ResponseEntity.ok(ebook);
    }

    /**
     * Deletes ebook from the database
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEbook(@PathVariable Long id) {
        var ebook = ebookRepository.findById(id).orElse(null);
        if (ebook == null) {
            return ResponseEntity.notFound().build();
        }

        ebookRepository.delete(ebook);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EbookNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEbookNotFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "EBook not found."));
    }
}
