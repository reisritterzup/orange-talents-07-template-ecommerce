package br.com.zup.desafiomercadolivre.repository;

import br.com.zup.desafiomercadolivre.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {


}
