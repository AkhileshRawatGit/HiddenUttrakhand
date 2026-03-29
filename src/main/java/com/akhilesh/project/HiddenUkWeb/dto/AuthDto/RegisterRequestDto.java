package com.akhilesh.project.HiddenUkWeb.dto.AuthDto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String name;
    private String email;
    private String password;
    private String phoneNo;
}
