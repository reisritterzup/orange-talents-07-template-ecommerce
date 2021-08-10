package br.com.zup.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.dto.PagSeguroRequestDto;
import br.com.zup.desafiomercadolivre.dto.PayPalRequestDto;
import br.com.zup.desafiomercadolivre.model.Transacao;
import br.com.zup.desafiomercadolivre.repository.PedidoRepository;
import br.com.zup.desafiomercadolivre.repository.TransacaoRepository;
import br.com.zup.desafiomercadolivre.service.NotaFiscalService;
import br.com.zup.desafiomercadolivre.service.RankingVendedoresService;
import br.com.zup.desafiomercadolivre.util.EnvioEmailService;
import br.com.zup.desafiomercadolivre.util.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private NotaFiscalService notaFiscalService;

    @Autowired
    private RankingVendedoresService rankingVendedoresService;

    @Autowired
    private EnvioEmailService envioEmailService;

    @PostMapping("/pagseguro")
    public ResponseEntity<StatusPedido> processaTransacaoPagSeguro(@RequestBody @Valid PagSeguroRequestDto pagSeguroRequestDto){
        Transacao model = transacaoRepository.save(pagSeguroRequestDto.toModel(pedidoRepository));
        processaCasoSucessoOuFalha(model);
        return ResponseEntity.ok(model.getStatusPedido());
    }

    @PostMapping("/paypal")
    public ResponseEntity<StatusPedido> processaTransacaoPayPal(@RequestBody @Valid PayPalRequestDto pagSeguroRequestDto){
        Transacao model = transacaoRepository.save(pagSeguroRequestDto.toModel(pedidoRepository));
        processaCasoSucessoOuFalha(model);
        return ResponseEntity.ok(model.getStatusPedido());
    }

    private void processaCasoSucessoOuFalha(Transacao transacao){
        if(transacao.getStatusPedido().equals(StatusPedido.SUCESSO)){
            notaFiscalService.processa(transacao.getPedido().getId(),transacao.getPedido().getUsuario().getId());
            rankingVendedoresService.processa(transacao.getPedido().getId(),transacao.getPedido().getProduto().getUsuario().getId());
            envioEmailService.enviarEmailSucesso(transacao.getPedido().getUsuario(),transacao.getPedido());
        }
        if(transacao.getStatusPedido().equals(StatusPedido.FALHA)){
            envioEmailService.enviarEmailFalha(transacao.getPedido().getUsuario(),transacao.getPedido());
        }
    }
}
