package br.com.zup.desafiomercadolivre.dto;

import br.com.zup.desafiomercadolivre.config.validacao.ExistsId;
import br.com.zup.desafiomercadolivre.model.Caracteristicas;
import br.com.zup.desafiomercadolivre.model.Categoria;
import br.com.zup.desafiomercadolivre.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoResponseDto {

    public ProdutoResponseDto() {
    }

    public ProdutoResponseDto(Produto produto) {
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        this.descricao = produto.getDescricao();
        this.nomeCategoria = produto.getCategoria().getNome();

        List<CaracteristicasResponseDto> responseDto = new ArrayList<>();
        for(Caracteristicas caracteristicas: produto.getCaracteristicasList()){
            responseDto.add(caracteristicas.toDto());
        }

        this.caracteristicasList = responseDto;
        this.nomeUsuario = produto.getUsuario().getEmail();
    }

    private String nome;
    private BigDecimal valor;
    private int quantidade;
    private List<CaracteristicasResponseDto> caracteristicasList;
    private String descricao;
    private String nomeCategoria;
    private String nomeUsuario;

    public String getNomeUsuario() {
        return nomeUsuario;
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

    public List<CaracteristicasResponseDto> getCaracteristicasList() {
        return caracteristicasList;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }
}
