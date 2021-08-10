package br.com.zup.desafiomercadolivre.util;

import br.com.zup.desafiomercadolivre.model.Pedido;
import br.com.zup.desafiomercadolivre.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class EnvioEmailService {

    public void enviarEmailSucesso(Usuario usuario, Pedido pedido){
        System.out.println("Mandar email para : "+usuario.getEmail() );
        System.out.println("Pedido :"+ pedido.getId());
    }

    public void enviarEmailFalha(Usuario usuario, Pedido pedido){
        System.out.println("Mandar email para : "+usuario.getEmail() );
        System.out.println("Pedido :"+ pedido.getId());
        System.out.println("Link para compra!");
    }
}
