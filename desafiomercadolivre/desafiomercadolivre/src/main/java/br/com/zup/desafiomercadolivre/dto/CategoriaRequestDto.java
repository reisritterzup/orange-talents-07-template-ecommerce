package br.com.zup.desafiomercadolivre.dto;

import br.com.zup.desafiomercadolivre.config.validacao.UniqueValue;
import br.com.zup.desafiomercadolivre.model.Categoria;
import br.com.zup.desafiomercadolivre.repository.CategoriaRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CategoriaRequestDto {

    public CategoriaRequestDto() {
    }

    public CategoriaRequestDto(String nome, Long idCategoria) {
        this.nome = nome;
        this.idCategoria = idCategoria;
    }

    @NotBlank
    @NotNull
    @UniqueValue(domainClass = Categoria.class,fieldName = "nome")
    private String nome;

    private Long idCategoria;

    public Categoria toModel(CategoriaRepository categoriaRepository){

        Categoria categoria = null;
        if (idCategoria != null)
            categoria = categoriaRepository.getById(idCategoria);
        return new Categoria(nome, categoria);
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }
}
