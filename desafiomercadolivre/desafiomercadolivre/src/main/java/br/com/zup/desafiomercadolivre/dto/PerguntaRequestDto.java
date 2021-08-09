package br.com.zup.desafiomercadolivre.dto;

import br.com.zup.desafiomercadolivre.config.validacao.ExistsId;
import br.com.zup.desafiomercadolivre.model.Pergunta;
import br.com.zup.desafiomercadolivre.model.Produto;
import br.com.zup.desafiomercadolivre.model.Usuario;
import br.com.zup.desafiomercadolivre.repository.ProdutoRepository;
import br.com.zup.desafiomercadolivre.repository.UsuarioRepository;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class PerguntaRequestDto {

    public PerguntaRequestDto() {
    }

    public PerguntaRequestDto(String titulo, String pergunta, Long idProduto, Long idUsuario) {
        this.titulo = titulo;
        this.pergunta = pergunta;
        this.idProduto = idProduto;
        this.idUsuario = idUsuario;
    }

    @NotNull
    @NotBlank
    private String titulo;

    @NotNull
    @NotBlank
    private String pergunta;

    @NotNull
    @ExistsId(fieldName = "id",domainClass = Produto.class)
    private Long idProduto;

    @NotNull
    @ExistsId(fieldName = "id",domainClass = Usuario.class)
    private Long idUsuario;

    public String getTitulo() {
        return titulo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Pergunta toModel(UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository){
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);
        Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);
        return new Pergunta(titulo,pergunta,optionalProduto.get(),optionalUsuario.get());
    }
}
