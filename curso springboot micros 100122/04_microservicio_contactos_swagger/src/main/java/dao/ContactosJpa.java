package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import model.Contacto;
import model.INombreEmailCount;
import model.NombreEmailCount;

public interface ContactosJpa extends JpaRepository<Contacto, Integer>{
	Contacto findByEmail(String email); //segun convenio de nombres, metodo explicito porque atributo no es identity
	
	@Transactional
	@Modifying
	@Query("Delete from Contacto c Where c.email=?1")
	void eliminarPorEmail(String email);	

	@Query("SELECT nombre FROM Contacto c where c.edad<?1")
	List<String> findByEdad(int edad);
	
	//https://www.baeldung.com/jpa-queries-custom-result-with-aggregation-functions
	@Query("SELECT new model.NombreEmailCount(c.nombre,c.email,COUNT(c)) FROM Contacto AS c where c.edad<?1 GROUP BY c.nombre,c.email ORDER BY c.nombre DESC")
	List<NombreEmailCount> countByNombreAndEmaill(int edad);
	@Query("SELECT c.nombre as nombre, c.email as email, COUNT(c) as total "
			  + "FROM Contacto AS c where c.edad<?1 GROUP BY c.nombre,c.email ORDER BY c.nombre DESC")
	List<INombreEmailCount> countByNombreAndEmail(int edad);
}
