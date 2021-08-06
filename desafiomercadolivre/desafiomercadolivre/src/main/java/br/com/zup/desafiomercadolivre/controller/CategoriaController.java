package br.com.zup.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.dto.CategoriaRequestDto;
import br.com.zup.desafiomercadolivre.dto.CategoriaResponseDto;
import br.com.zup.desafiomercadolivre.model.Categoria;
import br.com.zup.desafiomercadolivre.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<?> cadastrarCategoria(@RequestBody @Valid CategoriaRequestDto requestDto) {
        Categoria model = categoriaRepository.save(requestDto.toModel(categoriaRepository));
        return ResponseEntity.ok(new CategoriaResponseDto(model));
    }
}
