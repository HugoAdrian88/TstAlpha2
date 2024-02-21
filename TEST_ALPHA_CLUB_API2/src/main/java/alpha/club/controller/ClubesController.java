package alpha.club.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import alpha.club.model.Clubes;
import alpha.club.repository.ClubesRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ClubesController {

	@Autowired
	ClubesRepository clubesRepository;

	@GetMapping("/clubes")
	public ResponseEntity<List<Clubes>> getAllClientes(@RequestParam(required = false) String nombre_club) {
		try {
			List<Clubes> clubes = new ArrayList<Clubes>();

			if (nombre_club == null)
				clubesRepository.findAll().forEach(clubes::add);
	

			if (clubes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(clubes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/clubes/{id}")
	public ResponseEntity<Clubes> getClubesById(@PathVariable("id") long id) {
		Optional<Clubes> clubesData = clubesRepository.findById(id);

		if (clubesData.isPresent()) {
			return new ResponseEntity<>(clubesData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/clubes")
	public ResponseEntity<Clubes> createClientes(@RequestBody Clubes clubes) {
		try {
			Clubes _clubes= clubesRepository
					.save(new Clubes( clubes.getClub() , clubes.getNombre_club() ,  clubes.getDireccion() ));
			return new ResponseEntity<>(_clubes, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PutMapping("/clubes/{id}")
	public ResponseEntity<Clubes> updateClubes(@PathVariable("id") long id, @RequestBody Clubes clubes) {
		Optional<Clubes> clubesData = clubesRepository.findById(id);

		if (clubesData.isPresent()) {
			Clubes _clubes = clubesData.get();
			_clubes.setClub(clubes.getClub());
			_clubes.setNombre_club(clubes.getNombre_club());
			_clubes.setDireccion(clubes.getDireccion());

			return new ResponseEntity<>(clubesRepository.save(_clubes), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/clubes/{id}")
	public ResponseEntity<HttpStatus> deleteClubes(@PathVariable("id") long id) {
		try {
			clubesRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/clubes")
	public ResponseEntity<HttpStatus> deleteAllClientes() {
		try {
			clubesRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	
}