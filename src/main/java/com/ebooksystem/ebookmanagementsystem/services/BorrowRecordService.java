package com.ebooksystem.ebookmanagementsystem.services;

import com.ebooksystem.ebookmanagementsystem.dtos.BorrowRecordDto;
import com.ebooksystem.ebookmanagementsystem.entities.BorrowRecord;
import com.ebooksystem.ebookmanagementsystem.entities.BorrowStatus;
import com.ebooksystem.ebookmanagementsystem.exceptions.*;
import com.ebooksystem.ebookmanagementsystem.mappers.BorrowRecordMapper;
import com.ebooksystem.ebookmanagementsystem.repositories.BorrowRecordRepository;
import com.ebooksystem.ebookmanagementsystem.repositories.EbookRepository;
import com.ebooksystem.ebookmanagementsystem.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * BorrowRecordService class implements the logic required to process the frontend requests received by the controller
 */
@Service
@AllArgsConstructor
public class BorrowRecordService {

    public BorrowRecordRepository borrowRecordRepository;
    public UserRepository userRepository;
    public EbookRepository ebookRepository;
    public BorrowRecordMapper borrowRecordMapper;
    public EBookService eBookService;

    /**
     * Creates a new BorrowRecord. Receives a userId and eBookId, checks if they exist, throws an appropriate error
     * if the don't. Checks the user already borrowed this eBook and hasn't returned it yet, doesn't allow the user
     * to borrow the same eBook again and throws an appropriate error. If all off the checks are passed, allows the user
     * to borrow this book, assigns all off the fields to the new BorrowRecord object, sets the due date to be 7 days
     * from the borrow data, assigns a BORROWED status to the request and saves it in the database. Decreases the
     * amount of this eBook available for borrowing by one
     * @param userId
     * @param ebookId
     * @return
     */
    public BorrowRecordDto createRecord(Long userId, Long ebookId){
        var user = userRepository.findById(userId).orElse(null);
        if(user == null){
            throw new UserNotFoundException("User not found");
        }
        var ebook = ebookRepository.findById(ebookId).orElse(null);
        if(ebook == null){
            throw new EbookNotFoundException("Ebook not found");
        }
        var borrowRecord = borrowRecordRepository.findByUserId(userId).orElse(null);

        if(borrowRecord != null && borrowRecord.getEbook() == ebook &&
                borrowRecord.getStatus().equals(BorrowStatus.BORROWED)){
            throw new BorrowRecordExistsException("Borrow record for this eBook by this user already exists");
        }
        borrowRecord = new BorrowRecord();
        borrowRecord.setUser(user);
        borrowRecord.setEbook(ebook);
        borrowRecord.setBorrowDate(LocalDate.now());
        borrowRecord.setDueDate(LocalDate.now().plusDays(7));
        borrowRecord.setPrice(ebook.getPrice());
        borrowRecord.setStatus(BorrowStatus.BORROWED);
        borrowRecordRepository.save(borrowRecord);
        eBookService.borrowBook(ebookId);

        return borrowRecordMapper.toDto(borrowRecord);
    }

    /**
     * Marks a borrow record as returned. Checks if this record exists by ID, if not, throw an appropriate error.
     * Checks if this eBook has already been returned, if so, throws an appropriate error.
     * If all the checks pass, assign the record a status of returned, save the date of the return date, and increases
     * the amount of this eBook available to borrow by one
     * @param recordId
     * @return
     */
    public BorrowRecordDto returnEbook(Long recordId){
        var record = borrowRecordRepository.findById(recordId).orElse(null);
        if(record == null){
            throw new BorrowRecordNotFoundException("Borrow record not found");
        }
        if (record.getStatus().equals(BorrowStatus.RETURNED)){
            throw new EBookAlreadyReturnedException("Ebook is already returned");
        }
        var ebookId = record.getEbook().getId();
        record.setStatus(BorrowStatus.RETURNED);
        record.setReturnDate(LocalDate.now());
        borrowRecordRepository.save(record);
        eBookService.returnBook(ebookId);
        return borrowRecordMapper.toDto(record);

    }

}
