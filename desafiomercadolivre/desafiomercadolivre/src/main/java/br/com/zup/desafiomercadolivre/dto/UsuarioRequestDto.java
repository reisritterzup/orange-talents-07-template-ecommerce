package br.com.zup.desafiomercadolivre.dto;

import br.com.zup.desafiomercadolivre.model.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioRequestDto {

    public UsuarioRequestDto() {
    }

    public UsuarioRequestDto(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    @Email
    @NotBlank
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    @Length(min = 6)
    private String senha;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel(){
        return new Usuario(email,new BCryptPasswordEncoder().encode(senha));
    }
}
