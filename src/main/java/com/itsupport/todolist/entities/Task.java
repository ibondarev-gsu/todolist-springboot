package com.itsupport.todolist.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    private String description;

    @Builder.Default
    private LocalDateTime dateOfCreation = LocalDateTime.now();

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                '}';
    }
}
