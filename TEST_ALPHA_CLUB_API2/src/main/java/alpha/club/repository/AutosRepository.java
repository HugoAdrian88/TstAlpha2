package alpha.club.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import alpha.club.model.Autos;

public interface AutosRepository extends JpaRepository<Autos, Long> {
  List<Autos> findByActivo(boolean activo);
  List<Autos> findByMarcaContaining(String marca);
  List<Autos> findByCliente(long id_cliente);

  //List<Autos> findById_cliente(long id_cliente);

}