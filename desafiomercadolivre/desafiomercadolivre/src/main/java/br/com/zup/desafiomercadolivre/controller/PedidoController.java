package br.com.zup.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.dto.PedidoRequestDto;
import br.com.zup.desafiomercadolivre.model.Pedido;
import br.com.zup.desafiomercadolivre.repository.PedidoRepository;
import br.com.zup.desafiomercadolivre.repository.ProdutoRepository;
import br.com.zup.desafiomercadolivre.repository.UsuarioRepository;
import br.com.zup.desafiomercadolivre.util.Gateway;
import br.com.zup.desafiomercadolivre.util.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @ResponseStatus(HttpStatus.FOUND)
    @PostMapping("{idUsuario}")
    public String realizarPedido(@RequestBody @Valid PedidoRequestDto pedidoRequestDto,
                                            @PathVariable Long idUsuario,
                                            UriComponentsBuilder uriComponentsBuilder) throws BindException {
        Pedido model = pedidoRequestDto.toModel(usuarioRepository,produtoRepository,idUsuario);
        Gateway gateway = model.getGateway();
        if(model.getProduto().getQuantidade() < model.getQuantidade()){
            BindException problemaComEstoque = new BindException(pedidoRequestDto,"novaCompraRequest");
            problemaComEstoque.reject(null,"Não foi possível realizar a compra por conta do estoque");
            throw problemaComEstoque;
        }
        model.getProduto().abatarEstoque(model.getQuantidade());
        model.setStatus(StatusPedido.INICIADA);
        pedidoRepository.save(model);
        return gateway.criaUrlRetorno(model,uriComponentsBuilder);

    }
}
