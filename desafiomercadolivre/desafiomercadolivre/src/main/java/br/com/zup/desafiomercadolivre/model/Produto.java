package br.com.zup.desafiomercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Produto {

    public Produto() {
    }

    public Produto(Long id, String nome, BigDecimal valor, int quantidade, List<Caracteristicas> caracteristicasList, String descricao, Categoria categoria, LocalDateTime cadastro) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicasList = caracteristicasList;
        this.descricao = descricao;
        this.categoria = categoria;
        this.cadastro = cadastro;
    }

    public Produto(String nome, BigDecimal valor, int quantidade, List<Caracteristicas> caracteristicasList, String descricao, Categoria categoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicasList = caracteristicasList;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public Produto(String nome, BigDecimal valor, int quantidade, List<Caracteristicas> caracteristicasList, String descricao, Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicasList = caracteristicasList;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Min(0)
    @Column(nullable = false)
    private BigDecimal valor;

    @Min(0)
    @Column(nullable = false)
    private int quantidade;

    @Size(min = 3)
    @OneToMany(mappedBy = "produto",cascade = CascadeType.ALL)
    private List<Caracteristicas> caracteristicasList;

    @NotBlank
    @Lob
    @Column(nullable = false,length = 1000)
    private String descricao;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;



    private LocalDateTime cadastro = LocalDateTime.now();


    public Usuario getUsuario() {
        return usuario;
    }

    public Long getId() {
        return id;
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

    public void setCaracteristicasList(List<Caracteristicas> caracteristicasList) {
        this.caracteristicasList = caracteristicasList;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDateTime getCadastro() {
        return cadastro;
    }
}
