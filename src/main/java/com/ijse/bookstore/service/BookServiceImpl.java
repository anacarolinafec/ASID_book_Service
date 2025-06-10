package com.ijse.bookstore.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ijse.bookstore.dto.BookCreationDto;
import com.ijse.bookstore.entity.Author;
import com.ijse.bookstore.entity.Category;
import com.ijse.bookstore.repository.AuthorRepository;
import com.ijse.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ijse.bookstore.entity.Book;
import com.ijse.bookstore.repository.BookRepository;


@Service
public class BookServiceImpl implements BookService {
    
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Book createBook (BookCreationDto bookCreationDto) {

        Book newbook = new Book();
        newbook.setTitle(bookCreationDto.getTitle());
        newbook.setPrice(bookCreationDto.getPrice());
        newbook.setQuantity(bookCreationDto.getQuantity());
        newbook.setIsbnNumber(bookCreationDto.getIsbnNumber());
        newbook.setDescription(bookCreationDto.getDescription());

        Author author = authorRepository.findById(bookCreationDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        newbook.setAuthor(author);

        Category category = categoryRepository.findById(bookCreationDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        newbook.setCategory(category);

        return bookRepository.save(newbook);
    }

    @Override
    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }


    @Override
    public Book getBookById(Long id){
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> getBooksByCategoryID(Long id){
        return bookRepository.findByCategoryId(id);
    }

    @Override
    public List<Book> searchBooks(String query) {
        return bookRepository.searchBooks(query);
    }
       
    @Override
    public Book patchBookQuantity(Long id, Book book){
        Book existBook = bookRepository.findById(id).orElse(null);

        if (existBook != null) {
            existBook.setQuantity(book.getQuantity());
            bookRepository.save(existBook);

            return existBook;

        } else {

            return null;
        }
    }
}
