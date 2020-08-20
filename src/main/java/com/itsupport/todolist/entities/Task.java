package com.itsupport.todolist.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "tasks")
    private Set<User> users = new HashSet<>();

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
