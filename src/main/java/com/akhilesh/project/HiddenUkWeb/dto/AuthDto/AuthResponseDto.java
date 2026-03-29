package com.akhilesh.project.HiddenUkWeb.dto.AuthDto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;

    public AuthResponseDto(String token) {
        this.token = token;
    }
}
