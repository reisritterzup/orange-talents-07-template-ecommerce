package br.com.zup.desafiomercadolivre.dto;

import br.com.zup.desafiomercadolivre.config.validacao.ExistsId;
import br.com.zup.desafiomercadolivre.model.Imagem;
import br.com.zup.desafiomercadolivre.model.Produto;
import br.com.zup.desafiomercadolivre.repository.ProdutoRepository;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

public class ImagemRequestDto {

    public ImagemRequestDto(String caminhoImagem, Long idProduto) {
        this.caminhoImagem = caminhoImagem;
        this.idProduto = idProduto;
    }

    @Lob
    @NotNull
    private String caminhoImagem;

    @NotNull
    @ExistsId(domainClass = Produto.class,fieldName = "id")
    private Long idProduto;

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Imagem toModel(ProdutoRepository produtoRepository){
        Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);
        Produto produto = new Produto();
        if(produtoOptional.isPresent())
            produto = produtoOptional.get();
        return new Imagem(caminhoImagem,produto);
    }
}
