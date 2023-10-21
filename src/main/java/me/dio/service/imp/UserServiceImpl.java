package me.dio.service.imp;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepositiry;
import me.dio.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepositiry userRepositiry;

    public UserServiceImpl(UserRepositiry userRepositiry) {
        this.userRepositiry =userRepositiry;
    }

    @Override
    public User findById(Long id) {
        return userRepositiry.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if (userRepositiry.existsByAccountNumber(userToCreate.getAccount().getNumber())){
            throw new IllegalArgumentException("This Account Number already exists.");
        }
        return userRepositiry.save(userToCreate);
    }
}
