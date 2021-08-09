package br.com.zup.desafiomercadolivre.dto;

import br.com.zup.desafiomercadolivre.config.validacao.ExistsId;
import br.com.zup.desafiomercadolivre.model.Opniao;
import br.com.zup.desafiomercadolivre.model.Produto;
import br.com.zup.desafiomercadolivre.model.Usuario;
import br.com.zup.desafiomercadolivre.repository.ProdutoRepository;
import br.com.zup.desafiomercadolivre.repository.UsuarioRepository;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class OpniaoRequestDto {

    public OpniaoRequestDto() {
    }

    public OpniaoRequestDto(int nota, String titulo, String descricao, Long idProduto, Long idUsuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.idProduto = idProduto;
        this.idUsuario = idUsuario;
    }

    @Min(1)
    @Max(5)
    @NotNull
    private int nota;

    @NotNull
    @NotBlank
    private String titulo;

    @Lob
    @NotNull
    @NotBlank
    @Length(max = 500)
    private String descricao;

    @NotNull
    @ExistsId(fieldName = "id",domainClass = Produto.class)
    private Long idProduto;

    @NotNull
    @ExistsId(fieldName = "id",domainClass = Usuario.class)
    private Long idUsuario;

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Opniao toModel(UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository){
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);
        Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);
        return new Opniao(nota,titulo,descricao,optionalProduto.get(),optionalUsuario.get());
    }
}
