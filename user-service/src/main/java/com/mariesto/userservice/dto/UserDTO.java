package com.mariesto.userservice.dto;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String userId;

    private String username;

    private String phoneNumber;

    private Timestamp createdAt;

    private Timestamp updatedAt;

}
