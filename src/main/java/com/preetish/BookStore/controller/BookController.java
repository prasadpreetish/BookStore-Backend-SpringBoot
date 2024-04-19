package com.preetish.BookStore.controller;

import com.preetish.BookStore.model.Book;
import com.preetish.BookStore.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepo bookRepo;


    @GetMapping("/allbooks")
    public ResponseEntity<List<Book>> getAllBooks(){
        try{
            List<Book> booksList = new ArrayList<>();
            bookRepo.findAll().forEach(booksList::add);

            if(booksList.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(booksList,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> bookData = bookRepo.findById(id);
        if(bookData.isPresent()){
            return new ResponseEntity<>(bookData.get(),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book bookObject = bookRepo.save(book);
        return new ResponseEntity<>(bookObject,HttpStatus.OK);
    }

    @PostMapping("/editBook/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book newBookData){
        Optional<Book> oldBookData = bookRepo.findById(id);
        if(oldBookData.isPresent()){
            Book updatedBook = oldBookData.get();
            updatedBook.setTitle(newBookData.getTitle());
            updatedBook.setAuthor(newBookData.getAuthor());

            Book bookObject = bookRepo.save(updatedBook);
            return new ResponseEntity<>(bookObject,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id){

        Optional<Book> ToDeleteBook = bookRepo.findById(id);
        if(ToDeleteBook.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            bookRepo.deleteById(id);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
