package br.com.zup.desafiomercadolivre.dto;

import br.com.zup.desafiomercadolivre.config.validacao.ExistsId;
import br.com.zup.desafiomercadolivre.model.Pedido;
import br.com.zup.desafiomercadolivre.model.Transacao;
import br.com.zup.desafiomercadolivre.repository.PedidoRepository;
import br.com.zup.desafiomercadolivre.util.Gateway;
import br.com.zup.desafiomercadolivre.util.StatusPedido;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class PagSeguroRequestDto {

    @NotNull
    @ExistsId(domainClass = Pedido.class,fieldName = "id")
    private Long idPedido;

    @NotNull
    private int statusPedido;

    public Long getIdPedido() {
        return idPedido;
    }

    public int getStatusPedido() {
        return statusPedido;
    }

    public Transacao toModel(PedidoRepository pedidoRepository){
        Optional<Pedido> optionalPedido = pedidoRepository.findById(idPedido);
        return new Transacao(optionalPedido.get(), Gateway.pagseguro,statusPedido==1?StatusPedido.SUCESSO:StatusPedido.FALHA);
    }
}
