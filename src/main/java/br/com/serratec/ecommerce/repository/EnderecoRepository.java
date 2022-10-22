package br.com.serratec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serratec.ecommerce.model.Endereco;

@Repository
public interface EnderecoRepository  extends JpaRepository<Endereco, Long>{

}
