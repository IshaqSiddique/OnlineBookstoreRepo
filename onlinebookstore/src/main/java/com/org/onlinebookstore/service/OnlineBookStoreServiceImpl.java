package com.org.onlinebookstore.service;

import com.org.onlinebookstore.entity.Books;
import com.org.onlinebookstore.repository.OnlineBookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OnlineBookStoreServiceImpl implements OnlineBookStoreService {

    @Autowired
    private OnlineBookStoreRepository onlineBookStoreRepository;

    @Override
    public List<Books> getAllBooksDetails(){
         List<Books> booksDetails = (List<Books>) onlineBookStoreRepository.findAll();
         return booksDetails;
    }

    @Override
    public Optional<Books> getBookDetail(Integer isbnNo) {
        return onlineBookStoreRepository.findById(isbnNo);
    }

    @Override
    public Books saveBookDetails(Books book) {
        return onlineBookStoreRepository.save(book);
    }

    @Override
    public Books updateBookDetails(Books book, Integer isbnNo) {
        Books bookDetail = onlineBookStoreRepository.findById(isbnNo).get();
        if (Objects.nonNull(book.getBookType())
                && !"".equalsIgnoreCase(
                book.getBookType())) {
            bookDetail.setBookType(
                    book.getBookType());
        }

        if (Objects.nonNull(
                book.getAuthor())
                && !"".equalsIgnoreCase(
                book.getAuthor())) {
            bookDetail.setAuthor(
                    book.getAuthor());
        }

        if (Objects.nonNull(book.getDescription())
                && !"".equalsIgnoreCase(
                book.getDescription())) {
            bookDetail.setDescription(
                    book.getDescription());
        }

        if (Objects.nonNull(book.getName())
                && !"".equalsIgnoreCase(
                book.getName())) {
            bookDetail.setName(
                    book.getName());
        }
            bookDetail.setPrice(
                    book.getPrice());

        return onlineBookStoreRepository.save(bookDetail);
    }

    @Override
    public void deleteBookDetails(Integer isbnNo) {
        onlineBookStoreRepository.deleteById(isbnNo);
    }

    @Override
    public double checkout(List<String> booksIsbn, String promotionCode){
        System.out.println(booksIsbn);
        List<Books> bookDetail = onlineBookStoreRepository.getBooksDetailsByISBNNo(booksIsbn);
        for(Books b: bookDetail) {
            System.out.println(b.getAuthor());
        }
        return 0;
    }
}
