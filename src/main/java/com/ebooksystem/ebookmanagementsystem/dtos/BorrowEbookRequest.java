package com.ebooksystem.ebookmanagementsystem.dtos;

import lombok.Data;

/**
 * Strucutre of the request body for the borrowing of an ebook request
 */
@Data
public class BorrowEbookRequest {
    private Long userId;
    private Long ebookId;
}
