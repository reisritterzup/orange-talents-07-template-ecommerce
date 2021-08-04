package br.com.zup.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.dto.UsuarioRequestDto;
import br.com.zup.desafiomercadolivre.model.Usuario;
import br.com.zup.desafiomercadolivre.repositoy.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioRequestDto usuarioDto){
        Usuario usuario = usuarioRepository.save(usuarioDto.toModel());
        return ResponseEntity.ok().build();
    }
}
