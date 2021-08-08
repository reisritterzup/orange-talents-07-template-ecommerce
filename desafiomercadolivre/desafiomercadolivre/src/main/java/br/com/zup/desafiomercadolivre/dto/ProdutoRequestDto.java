package br.com.zup.desafiomercadolivre.dto;

import br.com.zup.desafiomercadolivre.config.validacao.ExistsId;
import br.com.zup.desafiomercadolivre.model.Caracteristicas;
import br.com.zup.desafiomercadolivre.model.Categoria;
import br.com.zup.desafiomercadolivre.model.Produto;
import br.com.zup.desafiomercadolivre.model.Usuario;
import br.com.zup.desafiomercadolivre.repository.CategoriaRepository;
import br.com.zup.desafiomercadolivre.repository.UsuarioRepository;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProdutoRequestDto {

    public ProdutoRequestDto() {
    }

    public ProdutoRequestDto(String nome, BigDecimal valor, int quantidade, List<Caracteristicas> caracteristicasList, String descricao, Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicasList = caracteristicasList;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }

    public ProdutoRequestDto(String nome, BigDecimal valor, int quantidade, List<Caracteristicas> caracteristicasList, String descricao, Long idCategoria, Long idUsuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicasList = caracteristicasList;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.idUsuario = idUsuario;
    }

    @NotBlank
    @NotNull
    private String nome;

    @Min(0)
    @NotNull
    private BigDecimal valor;

    @Min(0)
    @NotNull
    private int quantidade;

    @Size(min = 3)
    private List<Caracteristicas> caracteristicasList;

    @NotBlank
    @Lob
    @Length(max=1000)
    @NotNull
    private String descricao;

    @NotNull
    @ExistsId(fieldName = "id",domainClass = Categoria.class)
    private Long idCategoria;

    @NotNull
    @ExistsId(fieldName = "id",domainClass = Usuario.class)
    private Long idUsuario;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public List<Caracteristicas> getCaracteristicasList() {
        return caracteristicasList;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public Produto toModel(CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(idCategoria);
        Categoria categoria = new Categoria();
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);
        Usuario usuario = new Usuario();
        if(categoriaOptional.isPresent())
            categoria = categoriaOptional.get();
        if(categoriaOptional.isPresent())
            usuario = optionalUsuario.get();
        return new Produto(nome,valor,quantidade,caracteristicasList,descricao,categoria,usuario);
    }
}
