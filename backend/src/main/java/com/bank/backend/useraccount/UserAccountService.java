//package com.bank.backend.useraccount;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//@Service
//public class UserAccountService {
//    private final UserAccountRepository userRepository;
//
//    @Autowired
//    public UserAccountService(UserAccountRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public List<UserAccount> getUsers() {
//        return userRepository.findAll();
//
//    }
//
//    public void addNewUser(UserAccount user) {
//        Optional<UserAccount> userByEmail = userRepository
//                .findUserTByEmail(user.getEmail());
//        if(userByEmail.isPresent())
//            throw new IllegalStateException("Email Already in Use");
//        userRepository.save(user);
//    }
//
//    public void deleteUser(Long id) {
//        if(!userRepository.existsById(id))
//            throw new IllegalStateException("User with id: " + id + " does not exist");
//    }
//
//    @Transactional
//    public void updateUser(Long id, String username, String email) {
//        UserAccount user = userRepository.findById(id)
//                .orElseThrow(()->new IllegalStateException("User with id: " + id + " does not exist"));
//
//        if(username != null && username.length() > 0 && !Objects.equals(user.getUsername(), username))
//            user.setUsername(username);
//
//        if(email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
//            if(userRepository.findUserTByEmail(email).isPresent())
//                throw new IllegalStateException("Email Already in Use");
//            user.setEmail(username);
//        }
//
//    }
//}
