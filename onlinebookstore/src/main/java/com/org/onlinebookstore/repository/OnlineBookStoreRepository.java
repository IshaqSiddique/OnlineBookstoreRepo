package com.org.onlinebookstore.repository;

import com.org.onlinebookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnlineBookStoreRepository extends CrudRepository<Book,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM book WHERE book_isbn IN (1,2,3)")
    List<Book> getBooksDetailsByISBNNo(List<String> isbnNo);
}
