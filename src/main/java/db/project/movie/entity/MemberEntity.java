package db.project.movie.entity;

import db.project.movie.constant.Role;
import db.project.movie.dto.MemberDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public MemberEntity(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
}