package com.kunyk.cinemaauthserver.model;

import com.kunyk.cinemaauthserver.dto.UserDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "User")
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Column(name = "age", nullable = false)
    private Integer age;

    public UserDto toDto() {
        return UserDto.builder()
                .name(name)
                .email(email)
                .build();
    }

    public org.springframework.security.core.userdetails.User toSpringUser() {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_" + role);

        return new org.springframework.security.core.userdetails.User(email, password, grantedAuthorities);
    }
}