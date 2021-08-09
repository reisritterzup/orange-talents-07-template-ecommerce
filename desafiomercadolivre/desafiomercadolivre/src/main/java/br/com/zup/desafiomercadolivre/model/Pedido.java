package br.com.zup.desafiomercadolivre.model;

import br.com.zup.desafiomercadolivre.util.Gateway;
import br.com.zup.desafiomercadolivre.util.StatusPedido;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Pedido {

    public Pedido() {
    }

    public Pedido(Long id, Gateway gateway, BigDecimal valor, int quantidade, Produto produto, Usuario usuario) {
        this.id = id;
        this.gateway = gateway;
        this.valor = valor;
        this.quantidade = quantidade;
        this.produto = produto;
        this.usuario = usuario;
    }

    public Pedido(Gateway gateway, BigDecimal valor, int quantidade, Produto produto, Usuario usuario) {
        this.gateway = gateway;
        this.valor = valor;
        this.quantidade = quantidade;
        this.produto = produto;
        this.usuario = usuario;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Gateway gateway;

    @Positive
    private BigDecimal valor;

    @Positive
    @Min(1)
    private int quantidade;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario usuario;

    private StatusPedido status;

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
