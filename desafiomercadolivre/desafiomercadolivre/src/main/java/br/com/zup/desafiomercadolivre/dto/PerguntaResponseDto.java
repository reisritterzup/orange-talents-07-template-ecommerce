package br.com.zup.desafiomercadolivre.dto;

import br.com.zup.desafiomercadolivre.config.validacao.ExistsId;
import br.com.zup.desafiomercadolivre.model.Pergunta;
import br.com.zup.desafiomercadolivre.model.Produto;
import br.com.zup.desafiomercadolivre.model.Usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PerguntaResponseDto {
    public PerguntaResponseDto() {
    }

    public PerguntaResponseDto(Pergunta perguntaModel) {
        this.titulo = perguntaModel.getTitulo();
        this.pergunta = perguntaModel.getPergunta();
        this.produto = perguntaModel.getProduto().getNome();
        this.usuario = perguntaModel.getUsuario().getEmail();
    }

    public PerguntaResponseDto(String titulo, String pergunta, String produto, String usuario) {
        this.titulo = titulo;
        this.pergunta = pergunta;
        this.produto = produto;
        this.usuario = usuario;
    }

    private String titulo;
    private String pergunta;
    private String produto;
    private String usuario;

    public String getTitulo() {
        return titulo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public String getProduto() {
        return produto;
    }

    public String getUsuario() {
        return usuario;
    }
}
