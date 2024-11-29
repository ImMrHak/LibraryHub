package com.libraryhub.msbooks.domain.theme.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.libraryhub.msbooks.domain.book.model.Book;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTheme;
    private String name;
    private Boolean isDeleted;

    @ManyToMany(mappedBy = "themes") @JsonBackReference
    private Set<Book> books;
}
