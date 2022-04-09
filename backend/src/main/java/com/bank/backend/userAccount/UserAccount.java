package com.bank.backend.userAccount;

import com.bank.backend.bankaccount.BankAccount;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Model for user account
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
@Table
public class UserAccount implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "user_account_sequence",
            sequenceName = "user_account_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "user_account_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserAccountRole userRole;
    private LocalDate dateOfBirth;
    private Boolean locked = false;
    private Boolean enabled = false;
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="user_account",
            referencedColumnName = "id"
    )
    private List<BankAccount> list;

    public UserAccount(String firstName, String lastName, String email,
                       String username, String password, LocalDate dateOfBirth,
                       UserAccountRole userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.userRole = userRole;
    }

    //added this
    public UserAccount(String firstName, String lastName, String email,
                       String username, String password, LocalDate dateOfBirth,
                       UserAccountRole userRole, List<BankAccount> bankAccounts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.userRole = userRole;
        this.list = bankAccounts;
    }

    //added this
    public Long getId() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
