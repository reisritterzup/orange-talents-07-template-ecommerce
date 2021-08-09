package br.com.zup.desafiomercadolivre.repository;

import br.com.zup.desafiomercadolivre.controller.PedidoController;
import br.com.zup.desafiomercadolivre.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
}
