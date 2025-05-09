package com.ebooksystem.ebookmanagementsystem.controllers;

import com.ebooksystem.ebookmanagementsystem.dtos.BorrowEbookRequest;
import com.ebooksystem.ebookmanagementsystem.dtos.BorrowRecordDto;
import com.ebooksystem.ebookmanagementsystem.entities.BorrowRecord;
import com.ebooksystem.ebookmanagementsystem.exceptions.*;
import com.ebooksystem.ebookmanagementsystem.mappers.BorrowRecordMapper;
import com.ebooksystem.ebookmanagementsystem.repositories.BorrowRecordRepository;
import com.ebooksystem.ebookmanagementsystem.services.BorrowRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller for borrow records. Allows the frontend to send requests regarding borrow records by exposing the nodes
 * and performs operations in the backend and database layers based on those requests. Sends appropriate responses.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/borrow-records")
public class BorrowRecordController {

    private final BorrowRecordService borrowRecordService;
    private final BorrowRecordRepository borrowRecordRepository;
    private final BorrowRecordMapper borrowRecordMapper;

    /**
     * Creates a Borrow Request, assigns the borrowed eBook to the correct user that is borrowing it
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<BorrowRecordDto> borrowEBook(@RequestBody BorrowEbookRequest request) {
        var borrowRequestDto = borrowRecordService.createRecord(request.getUserId(), request.getEbookId());

        return ResponseEntity.ok().body(borrowRequestDto);
    }

    /**
     * Returns a book by getting its id from the frontend, updates the database
     * @param id
     * @return
     */
    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowRecordDto> returnEBook(@PathVariable Long id) {
        var returnRequestDto = borrowRecordService.returnEbook(id);

        return ResponseEntity.ok().body(returnRequestDto);
    }

    /**
     * Gets all BorrowRecords, has optional request body for UserId, if it is passed, gets all records of specific user,
     * if not, gets all existing records
     * @param userId
     * @return
     */
    @GetMapping("/get-all")
    public List<BorrowRecordDto> getAllRecords(
            @RequestParam(required = false) Long userId) {
        List<BorrowRecord> records;
        if (userId != null) {
            records = borrowRecordRepository.findAllByUserId(userId);
        }
        else{
            records = borrowRecordRepository.findAll();
        }
        return records.stream().map(borrowRecordMapper::toDto).toList();
    }

    @ExceptionHandler(BorrowRecordExistsException.class)
    public ResponseEntity<Map<String, String>> handleBorrowRecordExistsException(BorrowRecordExistsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(BorrowRecordNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBorrowRecordNotFoundException(BorrowRecordExistsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(EbookAlreadyBorrowedException.class)
    public ResponseEntity<Map<String, String>> handleEbookAlreadyBorrowedException(EbookAlreadyBorrowedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(EBookAlreadyReturnedException.class)
    public ResponseEntity<Map<String, String>> handleEbookAlreadyReturnedException(EBookAlreadyReturnedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
    }


    @ExceptionHandler(EbookNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEbookNotFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "EBook not found."));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found."));
    }
}
