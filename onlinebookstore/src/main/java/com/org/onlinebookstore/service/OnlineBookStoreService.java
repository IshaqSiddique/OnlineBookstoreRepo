package com.org.onlinebookstore.service;

import com.org.onlinebookstore.entity.Books;

import java.util.List;
import java.util.Optional;

public interface OnlineBookStoreService {
    List<Books> getAllBooksDetails();
    Optional<Books> getBookDetail(Integer isbnNo);
    Books saveBookDetails(Books book);
    Books updateBookDetails(Books book, Integer isbnNo);
    void deleteBookDetails(Integer isbnNo);
    public double checkout(List<String> booksIsbn, String promotionCode);
}
