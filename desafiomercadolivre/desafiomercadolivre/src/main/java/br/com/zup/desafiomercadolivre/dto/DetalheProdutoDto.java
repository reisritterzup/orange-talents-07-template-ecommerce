package br.com.zup.desafiomercadolivre.dto;

import br.com.zup.desafiomercadolivre.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DetalheProdutoDto {

    public DetalheProdutoDto() {
    }

    public DetalheProdutoDto(Produto produto,double media,int total) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getValor();
        this.mediaNotas = media;
        this.totalNotas = total;
        List<ImagemResponseDto> imagemResponseDto = new ArrayList<>();
        for(Imagem imagem: produto.getImagemList())
            imagemResponseDto.add(imagem.toDto());
        this.imagemList = imagemResponseDto;
        List<OpniaoResponseDto> opniaoResponseDto = new ArrayList<>();
        for(Opniao opniao: produto.getOpniaoList())
            opniaoResponseDto.add(opniao.toDto());
        this.opniaoResponseDtoList = opniaoResponseDto;
        List<CaracteristicasResponseDto> caracDto = new ArrayList<>();
        for(Caracteristicas caracteristicas: produto.getCaracteristicasList())
            caracDto.add(caracteristicas.toDto());
        this.caracteristicasList = caracDto;
        List<PerguntaResponseDto> perguntaDto = new ArrayList<>();
        for(Pergunta pergunta: produto.getPerguntaList())
            perguntaDto.add(pergunta.toDto());
        this.perguntaResponseDtos = perguntaDto;

    }

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private List<CaracteristicasResponseDto> caracteristicasList;
    private List<ImagemResponseDto> imagemList;
    private double mediaNotas;
    private int totalNotas;
    private List<OpniaoResponseDto> opniaoResponseDtoList;
    private List<PerguntaResponseDto> perguntaResponseDtos;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public List<CaracteristicasResponseDto> getCaracteristicasList() {
        return caracteristicasList;
    }

    public List<ImagemResponseDto> getImagemList() {
        return imagemList;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public int getTotalNotas() {
        return totalNotas;
    }

    public List<OpniaoResponseDto> getOpniaoResponseDtoList() {
        return opniaoResponseDtoList;
    }

    public List<PerguntaResponseDto> getPerguntaResponseDtos() {
        return perguntaResponseDtos;
    }
}
