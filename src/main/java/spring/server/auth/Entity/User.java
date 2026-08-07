package spring.server.auth.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    // @Id marks this field as the primary key of the entity
    // @GeneratedValue indicates that the primary key value will be generated automatically
    // IDENTITY means postgreSql's AUTO_INCREMENT handles it for us
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false, unique = true)
    private String email;

    // 
    @Column(nullable = false, length= 60)
    private String password;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // runs automatically before this entity is inserted into the database
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // runs automatically before this entity is updated in the database
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


}
