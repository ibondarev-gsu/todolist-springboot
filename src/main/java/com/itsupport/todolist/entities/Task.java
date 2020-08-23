package com.itsupport.todolist.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name = "tasks")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(of = {"id", "description"})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String description;

    @Builder.Default
    private LocalDateTime dateOfCreation = LocalDateTime.now();
}
