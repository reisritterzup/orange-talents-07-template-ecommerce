package br.com.zup.desafiomercadolivre.model;

import io.micrometer.core.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    public Pergunta() {
    }

    public Pergunta(String titulo, String pergunta, Produto produto, Usuario usuario) {
        this.titulo = titulo;
        this.pergunta = pergunta;
        this.produto = produto;
        this.usuario = usuario;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String titulo;

    @NotNull
    @NotBlank
    private String pergunta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    private LocalDateTime criacao = LocalDateTime.now();

    public String getTitulo() {
        return titulo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

}
