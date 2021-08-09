package br.com.zup.desafiomercadolivre.util;

import br.com.zup.desafiomercadolivre.model.Pedido;
import org.springframework.web.util.UriComponentsBuilder;

public enum Gateway {
    pagseguro {
        @Override
        public String criaUrlRetorno(Pedido pedido,
                                     UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPagseguro = uriComponentsBuilder
                    .path("/retorno-pagseguro/{id}")
                    .buildAndExpand(pedido.getId()).toString();

            return "pagseguro.com/" + pedido.getId() + "?redirectUrl="
                    + urlRetornoPagseguro;
        }
    },
    paypal {
        @Override
        public String criaUrlRetorno(Pedido pedido,
                                     UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPaypal = uriComponentsBuilder
                    .path("/retorno-paypal/{id}").buildAndExpand(pedido.getId())
                    .toString();

            return "paypal.com/" + pedido.getId() + "?redirectUrl=" + urlRetornoPaypal;
        }
    };

    public abstract String criaUrlRetorno(Pedido pedido,
                                   UriComponentsBuilder uriComponentsBuilder);
}
