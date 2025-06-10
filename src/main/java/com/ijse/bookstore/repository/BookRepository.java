package com.ijse.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ijse.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("SELECT b FROM Book b " +
            "WHERE (:query IS NULL OR " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(b.author.authorName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(b.category.name) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<Book> searchBooks(@Param("query") String query);

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Book findByTitle(@Param("title") String title);

    List<Book> findByCategoryId(Long categoryId);

}
