package com.mariesto.userservice.service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mariesto.userservice.dto.CreateUserDTO;
import com.mariesto.userservice.dto.UserDTO;
import com.mariesto.userservice.exception.NotFoundException;
import com.mariesto.userservice.persistence.entity.User;
import com.mariesto.userservice.persistence.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO findUserById(final String userId){
        Optional<User> userOptional = userRepository.findUserByUserId(userId);
        if (userOptional.isEmpty()){
            throw new NotFoundException("user not found");
        }
        return modelMapper.map(userOptional.get(), UserDTO.class);
    }

    @Transactional
    public void createNewUser(CreateUserDTO dto){
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setUsername(dto.getUsername());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
    }
}
