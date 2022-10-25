package br.com.serratec.ecommerce.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.serratec.ecommerce.model.Selecao;

@Repository
public interface SelecaoRepository extends JpaRepository<Selecao, Long>{
	List<Selecao> findByDescricao(String descricao);
}

