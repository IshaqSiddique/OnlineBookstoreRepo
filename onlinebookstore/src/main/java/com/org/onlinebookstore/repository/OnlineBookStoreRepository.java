package com.org.onlinebookstore.repository;

import com.org.onlinebookstore.entity.Books;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnlineBookStoreRepository extends CrudRepository<Books,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM books WHERE book_isbn IN (?1)")
    List<Books> getBooksDetailsByISBNNo(List<String> isbnNo);
}