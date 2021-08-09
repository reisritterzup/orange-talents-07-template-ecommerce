package br.com.zup.desafiomercadolivre.dto;

import br.com.zup.desafiomercadolivre.config.validacao.ExistsId;
import br.com.zup.desafiomercadolivre.model.Pedido;
import br.com.zup.desafiomercadolivre.model.Produto;
import br.com.zup.desafiomercadolivre.model.Usuario;
import br.com.zup.desafiomercadolivre.repository.ProdutoRepository;
import br.com.zup.desafiomercadolivre.repository.UsuarioRepository;
import br.com.zup.desafiomercadolivre.util.Gateway;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Optional;

public class PedidoRequestDto {

    public PedidoRequestDto() {
    }

    public PedidoRequestDto(Long id, Gateway gateway, BigDecimal valor, int quantidade, Long idProduto) {
        this.id = id;
        this.gateway = gateway;
        this.valor = valor;
        this.quantidade = quantidade;
        this.idProduto = idProduto;
    }

    public PedidoRequestDto(Gateway gateway, BigDecimal valor, int quantidade, Long idProduto) {
        this.gateway = gateway;
        this.valor = valor;
        this.quantidade = quantidade;
        this.idProduto = idProduto;
    }

    private Long id;

    @NotNull
    private Gateway gateway;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Positive
    @Min(1)
    private int quantidade;

    @NotNull
    @ExistsId(fieldName = "id",domainClass = Produto.class)
    private Long idProduto;

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

    public Long getIdProduto() {
        return idProduto;
    }

    public Pedido toModel(UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository, Long idUsuario){
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);
        Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);
        return new Pedido(id,gateway,valor,quantidade,optionalProduto.get(),optionalUsuario.get());
    }
}
