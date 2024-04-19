package com.preetish.BookStore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.preetish.BookStore.model.Book;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {

}
