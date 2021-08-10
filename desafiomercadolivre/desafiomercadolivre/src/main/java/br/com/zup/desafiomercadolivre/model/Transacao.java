package br.com.zup.desafiomercadolivre.model;

import br.com.zup.desafiomercadolivre.util.Gateway;
import br.com.zup.desafiomercadolivre.util.StatusPedido;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Transacao {

    public Transacao() {
    }

    public Transacao(Long id, Pedido pedido, Gateway gateway, StatusPedido statusPedido) {
        this.id = id;
        this.pedido = pedido;
        this.gateway = gateway;
        this.statusPedido = statusPedido;
    }

    public Transacao(Pedido pedido, Gateway gateway, StatusPedido statusPedido) {
        this.pedido = pedido;
        this.gateway = gateway;
        this.statusPedido = statusPedido;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Pedido pedido;

    @NotNull
    private Gateway gateway;

    @NotNull
    private StatusPedido statusPedido;

    private LocalDateTime criacao = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }
}
