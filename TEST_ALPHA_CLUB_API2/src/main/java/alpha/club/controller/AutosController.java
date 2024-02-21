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

import alpha.club.exception.ResourceNotFoundException;
import alpha.club.model.Autos;
import alpha.club.model.Clientes;
import alpha.club.repository.AutosRepository;
import alpha.club.repository.ClientesRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AutosController {

	@Autowired
	AutosRepository autosRepository;

	@Autowired
	ClientesRepository clientesRepository;
	
	@GetMapping("/clientes/{club}/autos")
	  public ResponseEntity<List<Autos>> getAllAutosByClienteId(@PathVariable(value = "club") Integer club) {
		List<Clientes> clientes = clientesRepository.findByClub(club);

		if (clientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		List<Autos> autos = autosRepository.findByActivo(true);
	    return new ResponseEntity<>(autos, HttpStatus.OK);
	  }
	/*
	 @GetMapping("/autos/{club}/clientes")
	  public ResponseEntity<List<Clientes>> getAllClientesByAutoActivo(@PathVariable(value = "club") Integer club) {
		 List<Autos> autos = autosRepository.findByActivo(true);
		 if (autos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

	    List<Clientes> clientes = clientesRepository.findClientesByActivo(club);
	    // List<Clientes> clientes = clientesRepository.findByActivo(true);
	    
	    return new ResponseEntity<>(clientes, HttpStatus.OK);
	  }
*/
	@GetMapping("/autos")
	public ResponseEntity<List<Autos>> getAllAutos(@RequestParam(required = false) String marca) {
		try {
			List<Autos> autos = new ArrayList<Autos>();

			if (marca == null)
				autosRepository.findAll().forEach(autos::add);
			else
				autosRepository.findByMarcaContaining(marca).forEach(autos::add);

			if (autos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(autos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/autos/{id_auto}")
	public ResponseEntity<Autos> getAutosById(@PathVariable("id_auto") long id_auto) {
		Optional<Autos> autosData = autosRepository.findById(id_auto);

		if (autosData.isPresent()) {
			return new ResponseEntity<>(autosData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/autosbycliente/{cliente}")
	public ResponseEntity<List<Autos>> getAutosBycliente(@PathVariable("cliente") long cliente) {
	
		try {
				List<Autos> autos = autosRepository.findByCliente(cliente);

				if (autos.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				
				return new ResponseEntity<>(autos, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		
	}


	@PostMapping("/autos")
	public ResponseEntity<Autos> createAutos(@RequestBody Autos autos) {
		try {
			Autos _autos = autosRepository
					//.save(new Autos(autos.getId_cliente(), autos.getId_chip(), autos.getMarca(), autos.getModelo(), autos.getPlacas() , autos.getColor(), autos.isActivo() ,false));
					.save(new Autos(autos.getId_auto() , autos.getCliente() , autos.getId_chip()  ,  autos.getMarca(), autos.getModelo(), autos.getPlacas() , autos.getColor() , autos.getFecha_alta() , autos.getFecha_actualizacion() ,true));
			return new ResponseEntity<>(_autos, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/autos/{id_auto}")
	public ResponseEntity<Autos> updateAutos(@PathVariable("id_auto") long id_auto, @RequestBody Autos autos) {
		Optional<Autos> autosData = autosRepository.findById(id_auto);

		if (autosData.isPresent()) {
			Autos _autos = autosData.get();
			_autos.setCliente(autos.getCliente());
			_autos.setId_chip(autos.getId_chip());
			_autos.setMarca(autos.getMarca());
			_autos.setModelo(autos.getModelo());
			_autos.setPlacas(autos.getPlacas());
			_autos.setColor(autos.getColor());
			_autos.setActivo(autos.isActivo());
			return new ResponseEntity<>(autosRepository.save(_autos), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/autos/{id_auto}")
	public ResponseEntity<HttpStatus> deleteAutos(@PathVariable("id_auto") long id_auto) {
		try {
			autosRepository.deleteById(id_auto);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/autos")
	public ResponseEntity<HttpStatus> deleteAllAutos() {
		try {
			autosRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/autos/activo")
	public ResponseEntity<List<Autos>> findByActivo() {
		try {
			List<Autos> autos = autosRepository.findByActivo(true);

			if (autos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(autos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}