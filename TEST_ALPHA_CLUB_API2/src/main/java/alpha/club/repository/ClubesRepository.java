package alpha.club.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import alpha.club.model.Clubes;

public interface ClubesRepository extends JpaRepository<Clubes, Long> {
  List<Clubes> findById(Integer id);

}