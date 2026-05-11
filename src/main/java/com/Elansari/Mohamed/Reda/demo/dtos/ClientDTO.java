package com.Elansari.Mohamed.Reda.demo.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class ClientDTO {
    private Long id;
    @NotBlank
    private String nom;
    @Email @NotBlank
    private String email;
}