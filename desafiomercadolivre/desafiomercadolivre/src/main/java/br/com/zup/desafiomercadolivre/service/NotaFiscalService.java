package br.com.zup.desafiomercadolivre.service;

import br.com.zup.desafiomercadolivre.util.ProcessaSucesso;
import org.springframework.stereotype.Service;

@Service
public class NotaFiscalService implements ProcessaSucesso {

    @Override
    public void processa(Long idPedido, Long idUsuario) {
        System.out.println("Id de pedido: "+idPedido.toString());
        System.out.println("Id de comprador: "+idUsuario.toString());
    }
}
