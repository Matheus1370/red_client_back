package com.redclient.red_client_back.modules.client;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false)
    private String name;

    @Email(message = "O campo e-mail deve conter um e-mail válido")
    @Column(nullable = false, unique = true)
    private String email;

    @Size(min = 8, max = 100, message = "A senha deve ter entre 8 e 100 caracteres")
    @Column(nullable = false)
    private String password;
    
}
