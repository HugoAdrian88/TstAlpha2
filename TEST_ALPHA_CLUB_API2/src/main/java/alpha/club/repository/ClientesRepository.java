package alpha.club.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import alpha.club.model.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {
  List<Clientes> findByActivo(boolean activo);
  List<Clientes> findByNombreContaining(String nombre);
  
  List<Clientes> findByClub(Integer club);
  

  //List<Clientes> findClientesByActivo(Integer club);
  
}