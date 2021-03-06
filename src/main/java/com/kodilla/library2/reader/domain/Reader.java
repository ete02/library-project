package com.kodilla.library2.reader.domain;

import com.kodilla.library2.borrow.domain.Borrow;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Entity
@Table(name = "READERS")
public final class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastname;

    @NotNull
    @Column(name = "CREATE_ACCOUNT_DATE")
    private final Date createAccountDate = new Date();

    @OneToMany(
            targetEntity = Borrow.class,
            mappedBy = "reader",
            fetch = FetchType.LAZY
    )
    private List<Borrow> borrows;
}