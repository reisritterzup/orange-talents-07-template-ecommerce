package br.com.zup.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.dto.ImagemRequestDto;
import br.com.zup.desafiomercadolivre.dto.ProdutoRequestDto;
import br.com.zup.desafiomercadolivre.dto.ProdutoResponseDto;
import br.com.zup.desafiomercadolivre.model.Caracteristicas;
import br.com.zup.desafiomercadolivre.model.Imagem;
import br.com.zup.desafiomercadolivre.model.Produto;
import br.com.zup.desafiomercadolivre.repository.CategoriaRepository;
import br.com.zup.desafiomercadolivre.repository.ImagemRepository;
import br.com.zup.desafiomercadolivre.repository.ProdutoRepository;
import br.com.zup.desafiomercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ImagemRepository imagemRepository;

    @PostMapping
    public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid ProdutoRequestDto requestDto){
        Produto produto = produtoRepository.save(requestDto.toModel(categoriaRepository,usuarioRepository));
        List<Caracteristicas> caracteristicas = produto.getCaracteristicasList();
        caracteristicas.forEach(caracteristica -> caracteristica.setProduto(produto));
        produto.setCaracteristicasList(caracteristicas);
        produtoRepository.save(produto);
        return ResponseEntity.ok(new ProdutoResponseDto(produto));
    }

    @PostMapping("/imagem")
    public ResponseEntity<?> cadastrarImagem(@RequestBody @Valid List<ImagemRequestDto> requestDto){
        requestDto.forEach(imagem ->
                imagemRepository.save(imagem.toModel(produtoRepository)));
        return ResponseEntity.ok().build();
    }
}
