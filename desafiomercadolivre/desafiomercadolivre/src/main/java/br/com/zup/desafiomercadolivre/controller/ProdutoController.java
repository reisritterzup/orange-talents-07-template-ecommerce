package br.com.zup.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.dto.ProdutoRequestDto;
import br.com.zup.desafiomercadolivre.dto.ProdutoResponseDto;
import br.com.zup.desafiomercadolivre.model.Caracteristicas;
import br.com.zup.desafiomercadolivre.model.Produto;
import br.com.zup.desafiomercadolivre.repository.CategoriaRepository;
import br.com.zup.desafiomercadolivre.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    /*@PostMapping
    public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid Produto requestDto){
        Produto produto = produtoRepository.save(requestDto);
        return ResponseEntity.ok(produto);
    }*/

    @PostMapping
    public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid ProdutoRequestDto requestDto){
        Produto produto = produtoRepository.save(requestDto.toModel(categoriaRepository));
        List<Caracteristicas> caracteristicas = produto.getCaracteristicasList();
        caracteristicas.forEach(caracteristica -> caracteristica.setProduto(produto));
        produto.setCaracteristicasList(caracteristicas);
        produtoRepository.save(produto);
        return ResponseEntity.ok(new ProdutoResponseDto(produto));
    }
}
