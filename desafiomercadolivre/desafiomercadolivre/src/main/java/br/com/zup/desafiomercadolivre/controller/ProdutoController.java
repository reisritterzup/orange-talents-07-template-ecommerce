package br.com.zup.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.dto.*;
import br.com.zup.desafiomercadolivre.model.*;
import br.com.zup.desafiomercadolivre.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private OpniaoRepository opniaoRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

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

    @PostMapping("/opniao")
    public ResponseEntity<?> cadastrarOpniao(@RequestBody @Valid OpniaoRequestDto requestDto){
        Opniao model = opniaoRepository.save(requestDto.toModel(usuarioRepository,produtoRepository));
        return ResponseEntity.ok(new OpniaoResponseDto(model));
    }

    @PostMapping("/pergunta")
    public ResponseEntity<?> cadastrarPergunta(@RequestBody @Valid PerguntaRequestDto requestDto){
        Pergunta model = perguntaRepository.save(requestDto.toModel(usuarioRepository,produtoRepository));
        return ResponseEntity.ok(new PerguntaResponseDto(model));
    }

    @GetMapping("/detalhe/{idProduto}")
    public ResponseEntity<?> buscarDetalheProduto(@PathVariable Long idProduto){
        Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);
        Produto produto = new Produto();
        if(optionalProduto.isPresent())
            produto = optionalProduto.get();
        return ResponseEntity.ok(new DetalheProdutoDto(produto,
                produtoRepository.buscarMediaNotasProduto(idProduto),
                produtoRepository.numeroTotalNotasProduto(idProduto)));
    }
}
