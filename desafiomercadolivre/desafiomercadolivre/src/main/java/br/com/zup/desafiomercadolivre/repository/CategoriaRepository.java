package br.com.zup.desafiomercadolivre.repository;

import br.com.zup.desafiomercadolivre.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
