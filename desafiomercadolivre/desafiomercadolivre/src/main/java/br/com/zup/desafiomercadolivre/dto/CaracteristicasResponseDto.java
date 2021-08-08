package br.com.zup.desafiomercadolivre.dto;

public class CaracteristicasResponseDto {

    public CaracteristicasResponseDto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    private String nome;
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
