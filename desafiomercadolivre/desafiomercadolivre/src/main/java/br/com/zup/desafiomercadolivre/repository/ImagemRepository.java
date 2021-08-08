package br.com.zup.desafiomercadolivre.repository;

import br.com.zup.desafiomercadolivre.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<Imagem,Long> {
}
