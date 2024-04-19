package com.preetish.BookStore.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
}
