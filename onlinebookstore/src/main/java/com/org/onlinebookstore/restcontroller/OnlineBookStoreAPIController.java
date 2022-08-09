package com.org.onlinebookstore.restcontroller;

import com.org.onlinebookstore.entity.Book;
import com.org.onlinebookstore.service.OnlineBookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore")
public class OnlineBookStoreAPIController {

    @Autowired
    private OnlineBookStoreService onlineBookStoreService;

    @GetMapping(value="/books",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAllBooksDetails(){
        return onlineBookStoreService.getAllBooksDetails();
    }

    @GetMapping(value="/books/{bookIsbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBookDetailById(@PathVariable("bookIsbn") Integer bookIsbn){
        return onlineBookStoreService.getBookDetailById(bookIsbn);
    }

    @PostMapping(value="/books", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Book saveBookDetails(@RequestBody Book book){
        return onlineBookStoreService.saveBookDetails(book);
    }

    @PutMapping(value="/books/{bookIsbn}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Book updateBookDetails(@RequestBody Book book, @PathVariable("bookIsbn") Integer bookIsbn){
        return onlineBookStoreService.updateBookDetails(book, bookIsbn);
    }

    @DeleteMapping(value="/books/{bookIsbn}",produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteBookDetails(@PathVariable("bookIsbn") Integer bookIsbn){
        onlineBookStoreService.deleteBookDetails(bookIsbn);
        return "Deleted Successfully";
    }

    @GetMapping(value="/checkout",produces = MediaType.APPLICATION_JSON_VALUE)
    public double checkout(@RequestParam(value="booksIsbn") List<String> booksIsbn,
                           @RequestParam(value="promotionCode", defaultValue="NOCODE", required=false) String promotionCode){
        double totalBookPrice = onlineBookStoreService.checkout(booksIsbn, promotionCode);
        return totalBookPrice;
    }
}
