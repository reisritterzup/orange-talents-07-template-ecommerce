package br.com.zup.desafiomercadolivre.repository;

import br.com.zup.desafiomercadolivre.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao,Long> {
}
