package com.bank.backend.userAccount;

import com.bank.backend.registration.token.ConfirmationTokenService;
import com.bank.backend.registration.token.ConformationToken;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * DB CRUD operations for UserAccount objects
 */
@Service
@AllArgsConstructor
public class UserAccountService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "User <<%s>> does NOT Exist";
    private final static String USER_ID_NOT_FOUND = "User with ID: %d does NOT Exist";
    private final static String EMAIL_IN_USE = "The E-mail <<%s>> is already is Use";

    private final UserAccountRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public List<UserAccount> getUsers() {
        return userRepository.findAll();

    }

    public String signupNewUser(UserAccount user) {
        if (userRepository.findUserAccountByEmail(user.getEmail()).isPresent())
            throw new IllegalStateException(String.format(EMAIL_IN_USE, user.getEmail()));

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        confirmationTokenService.saveConfirmationToken(new ConformationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        ));

        return token;
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id))
            throw new IllegalStateException();
    }

    public boolean authenticateUser(String username, String password) {
        return false;
    }

    @Transactional
    public void updateUser(Long id, String username, String email) {
        UserAccount user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format(USER_ID_NOT_FOUND, id)));

        if (username != null && username.length() > 0 && !Objects.equals(user.getUsername(), username))
            user.setUsername(username);

        if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
            if (userRepository.findUserAccountByEmail(email).isPresent())
                throw new IllegalStateException(String.format(EMAIL_IN_USE, email));
            user.setEmail(username);
        }
    }

    public int enableUserAccount(String email) {
        return userRepository.enableUserAccount(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserAccountByEmail(username).orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND, username)));
    }
}