package br.com.zup.desafiomercadolivre.repository;

import br.com.zup.desafiomercadolivre.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    @Query(nativeQuery = true,value =
            "select avg(nota) from opniao where produto_id = ?1 group by produto_id;")
    double buscarMediaNotasProduto(Long id);

    @Query(nativeQuery = true,value =
            "select count(nota) from opniao where produto_id = ?1 group by produto_id;")
    int numeroTotalNotasProduto(Long id);

}
