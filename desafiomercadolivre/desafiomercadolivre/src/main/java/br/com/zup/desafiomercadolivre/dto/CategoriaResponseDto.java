package br.com.zup.desafiomercadolivre.dto;

import br.com.zup.desafiomercadolivre.model.Categoria;

public class CategoriaResponseDto {

    public CategoriaResponseDto(Categoria model) {
        this.nome = model.getNome();
    }

    private String nome;

    public String getNome() {
        return nome;
    }
}
