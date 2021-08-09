package br.com.zup.desafiomercadolivre.repository;

import br.com.zup.desafiomercadolivre.model.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntaRepository extends JpaRepository<Pergunta,Long> {
}
