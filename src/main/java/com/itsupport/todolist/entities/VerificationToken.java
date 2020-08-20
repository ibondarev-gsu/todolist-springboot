package com.itsupport.todolist.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verification_token")
@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationToken {
    private static final int ONE_DAY = 1;

    public VerificationToken(final User user, final String token) {
        this.token = token;
        this.user = user;
        this.expiryDate = LocalDateTime.now().plusDays(1);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(mappedBy = "verificationToken")
    private User user;

    @Builder.Default
    private LocalDateTime expiryDate = LocalDateTime.now().plusDays(1);

    public boolean isExpired(){
        return expiryDate.compareTo(LocalDateTime.now()) < 0;
    }
}