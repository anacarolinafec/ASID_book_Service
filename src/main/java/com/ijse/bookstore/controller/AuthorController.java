package com.ijse.bookstore.controller;

import com.ijse.bookstore.dto.AuthorCreationDTO;
import com.ijse.bookstore.entity.Author;
import com.ijse.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping("/author/register")
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorCreationDTO dto) {
        Author author = new Author();
        author.setAuthorName(dto.getName());

        Author saved = authorRepository.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

}
