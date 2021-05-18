package com.kodilla.library2.book.domain;

import com.kodilla.library2.borrow.domain.Borrow;
import com.kodilla.library2.title.domain.Title;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Entity
@Table(name = "BOOKS")
public final class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private Title title;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "BOOK_STATUS")
    private BookStatus bookStatus;

    @OneToMany(
            targetEntity = Borrow.class,
            mappedBy = "book",
            fetch = FetchType.LAZY
    )
    private List<Borrow> borrows;
}
