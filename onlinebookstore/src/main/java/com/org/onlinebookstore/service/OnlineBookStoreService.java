package com.org.onlinebookstore.service;

import com.org.onlinebookstore.entity.Book;

import java.util.List;
import java.util.Optional;

public interface OnlineBookStoreService {
    List<Book> getAllBooksDetails();
    Book getBookDetailById(Integer isbnNo);
    Book saveBookDetails(Book book);
    Book updateBookDetails(Book book, Integer isbnNo);
    void deleteBookDetails(Integer isbnNo);
    public double checkout(List<String> booksIsbn, String promotionCode);
}
