package com.org.onlinebookstore.service;

import com.org.onlinebookstore.entity.Books;
import com.org.onlinebookstore.repository.OnlineBookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OnlineBookStoreServiceImpl implements OnlineBookStoreService {

    @Autowired
    private OnlineBookStoreRepository onlineBookStoreRepository;

    @Override
    public List<Books> getAllBooksDetails() {
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
    public double checkout(List<String> booksIsbn, String promotionCode) {
        List<Books> bookDetail = onlineBookStoreRepository.getBooksDetailsByISBNNo(booksIsbn);
        HashMap<String, Integer> promotionCodeDiscountMap = new HashMap<>();
        promotionCodeDiscountMap.put("FICTION10", 10);
        promotionCodeDiscountMap.put("DRAMA20", 20);
        double totalBooksCost = 0.0;
        if (promotionCode.equalsIgnoreCase("NOCODE")) {
            totalBooksCost = priceCalculator(promotionCode, promotionCodeDiscountMap, bookDetail);
        } else if (promotionCodeDiscountMap.containsKey(promotionCode)) {
            totalBooksCost = priceCalculator(promotionCode, promotionCodeDiscountMap, bookDetail);
        } else {
            System.out.println("Invalid Promotion Code. Please Enter a valid code");
            return 0;
        }

        return totalBooksCost;
    }

    public double priceCalculator(String promotionCode,
                                  Map<String, Integer> promotionCodeDiscountMap, List<Books> bookDetail) {
        double totalBooksCost = 0.0;
        if (promotionCode.equalsIgnoreCase("NOCODE")) {
            for (Books b : bookDetail) {
                totalBooksCost += b.getPrice();
            }
        } else if (promotionCodeDiscountMap.containsKey(promotionCode)) {
            String arr[] = promotionCode.split("((?<=[a-zA-Z])(?=[0-9]))");
            for (Books b : bookDetail) {
                if (arr[0].equalsIgnoreCase(b.getBookType())) {
                    totalBooksCost = ((totalBooksCost + b.getPrice()) - ((b.getPrice() * Integer.parseInt(arr[1])) / 100));
                } else {
                    totalBooksCost = totalBooksCost + b.getPrice();
                }
            }
        }
        return totalBooksCost;
    }
}
