package br.com.zup.desafiomercadolivre.model;

import javax.persistence.*;

@Entity
public class Imagem {

    public Imagem() {
    }

    public Imagem(String caminhoImagem, Produto produto) {
        this.caminhoImagem = caminhoImagem;
        this.produto = produto;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String caminhoImagem;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public Produto getProduto() {
        return produto;
    }
}
