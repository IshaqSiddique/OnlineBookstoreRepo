package com.org.onlinebookstore.restcontroller;

import com.org.onlinebookstore.entity.Books;
import com.org.onlinebookstore.service.OnlineBookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookstore")
public class OnlineBookStoreAPIController {

    @Autowired
    private OnlineBookStoreService onlineBookStoreService;

    @GetMapping("/books")
    public List<Books> getAllBooksDetails(){
        return onlineBookStoreService.getAllBooksDetails();
    }

    @GetMapping("/books/{bookIsbn}")
    public Optional<Books> getBookDetail(@PathVariable("bookIsbn") Integer bookIsbn){
        return onlineBookStoreService.getBookDetail(bookIsbn);
    }

    @PostMapping("/books")
    public Books saveBookDetails(@RequestBody Books book){
        return onlineBookStoreService.saveBookDetails(book);
    }

    @PutMapping("/books/{bookIsbn}")
    public Books updateBookDetails(@RequestBody Books book, @PathVariable("bookIsbn") Integer bookIsbn){
        return onlineBookStoreService.updateBookDetails(book, bookIsbn);
    }

    @DeleteMapping("/books/{bookIsbn}")
    public String deleteBookDetails(@PathVariable("bookIsbn") Integer bookIsbn){
        onlineBookStoreService.deleteBookDetails(bookIsbn);
        return "Deleted Successfully";
    }

    @GetMapping("/checkout")
    public double checkout(@RequestParam(value="booksIsbn") List<String> booksIsbn,
                           @RequestParam(value="promotionCode", defaultValue="NOCODE", required=false) String promotionCode){
        double totalBookPrice = onlineBookStoreService.checkout(booksIsbn, promotionCode);
        return totalBookPrice;
    }
}
