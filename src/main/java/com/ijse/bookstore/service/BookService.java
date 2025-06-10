package com.ijse.bookstore.service;

import java.util.List;

import com.ijse.bookstore.dto.BookCreationDto;
import org.springframework.stereotype.Service;

import com.ijse.bookstore.entity.Book;

@Service
public interface BookService {
    List<Book> getAllBook();
    Book getBookById(Long id);
    List<Book> getBooksByCategoryID(Long id);
    List<Book> searchBooks(String query);
    Book patchBookQuantity(Long id, Book book);
    Book createBook (BookCreationDto bookCreationDto);
}
