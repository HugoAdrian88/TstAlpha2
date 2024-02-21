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

import alpha.club.model.Autos;
import alpha.club.model.AutosActivos;
import alpha.club.model.Clientes;
import alpha.club.repository.AutosRepository;
import alpha.club.repository.ClientesRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ClientesController {

	@Autowired
	ClientesRepository clientesRepository;
	@Autowired
	AutosRepository autosRepository;
	
/////////lista de autos activos por club y cliente acivo
	@GetMapping("/clientesbyclub/{club}/autoactivo")
	 public ResponseEntity<List<Autos>> findClientesByActivo(@PathVariable("club") Integer club) {
			try {
				List<Clientes> clientes = new ArrayList<Clientes>();
				List<Clientes> clientesfinal = new ArrayList<Clientes>();
				List<Autos> autos  = new ArrayList<Autos>();
				List<Autos> autosfinal  = new ArrayList<Autos>();

				if (club == null)
					clientesRepository.findAll().forEach(clientes::add);
				else {
					clientesRepository.findByClub(club).forEach(clientes::add);		
					  for(Clientes name: clientes) {
						  if(name.isActivo() == true) {
							  clientesfinal.add(name);
							  autosRepository.findByCliente(name.getId_cliente()).forEach(autos::add);
							 
						  }
					  }
				}
				for(Autos names: autos) {
					 if(names.isActivo() == true) {
						 autosfinal.add(names);
			
				}
				}

				if (autosfinal.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
				}

				return new ResponseEntity<>(autos, HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

	@GetMapping("/clientes")
	public ResponseEntity<List<Clientes>> getAllClientes(@RequestParam(required = false) String nombre) {
		try {
			List<Clientes> clientes = new ArrayList<Clientes>();

			if (nombre == null)
				clientesRepository.findAll().forEach(clientes::add);
			else
				clientesRepository.findByNombreContaining(nombre).forEach(clientes::add);

			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/clientes/{id_cliente}")
	public ResponseEntity<Clientes> getClientesById(@PathVariable("id_cliente") long id_cliente) {
		Optional<Clientes> clientesData = clientesRepository.findById(id_cliente);

		if (clientesData.isPresent()) {
			return new ResponseEntity<>(clientesData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/clientes")
	public ResponseEntity<Clientes> createClientes(@RequestBody Clientes clientes) {
		try {
			Clientes _clientes = clientesRepository
					.save(new Clientes(clientes.getId_cliente() , clientes.getClub() ,  clientes.getNombre() , clientes.getFecha_alta(), clientes.getFecha_actualizacion(),  true, true));
			return new ResponseEntity<>(_clientes, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PutMapping("/clientes/{id_cliente}")
	public ResponseEntity<Clientes> updateClientes(@PathVariable("id_cliente") long id_cliente, @RequestBody Clientes clientes) {
		Optional<Clientes> clientesData = clientesRepository.findById(id_cliente);

		if (clientesData.isPresent()) {
			Clientes _clientes = clientesData.get();
			_clientes.setId_cliente(clientes.getId_cliente());
			_clientes.setClub(clientes.getClub());
			_clientes.setNombre(clientes.getNombre());
			_clientes.setFecha_alta(clientes.getFecha_alta());
			_clientes.setFecha_actualizacion(clientes.getFecha_actualizacion());
			_clientes.setAcceso_permitido(clientes.isAcceso_permitido());
			_clientes.setActivo(clientes.isActivo());
			return new ResponseEntity<>(clientesRepository.save(_clientes), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/clientes/{id_cliente}")
	public ResponseEntity<HttpStatus> deleteClientes(@PathVariable("id_cliente") long id_cliente) {
		try {
			clientesRepository.deleteById(id_cliente);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/clientes")
	public ResponseEntity<HttpStatus> deleteAllClientes() {
		try {
			clientesRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/clientes/activo")
	public ResponseEntity<List<Clientes>> findByActivo() {
		try {
			List<Clientes> clientes = clientesRepository.findByActivo(true);

			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/clientesbyclub/{club}")
	public ResponseEntity<List<Clientes>> findByClub(@PathVariable("club") Integer club) {
		try {
		List<Clientes> clientes = clientesRepository.findByClub(club);

		if (clientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
}