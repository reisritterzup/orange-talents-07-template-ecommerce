package br.com.zup.desafiomercadolivre.model;

import br.com.zup.desafiomercadolivre.dto.CaracteristicasResponseDto;

import javax.persistence.*;

@Entity
public class Caracteristicas {

    public Caracteristicas() {
    }

    public Caracteristicas(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Caracteristicas(String nome, String descricao, Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    @ManyToOne
    private Produto produto;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public CaracteristicasResponseDto toDto(){
        return new CaracteristicasResponseDto(nome,descricao);
    }
}
