package com.amh.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amh.demo.entities.Depense;

public interface DepenseRepository extends JpaRepository<Depense, Long>{
	
	@Query("SELECT d FROM Depense d where d.isdeleted = false")
	List<Depense> findAll();
	
	@Query(value = "SELECT sum(montant) FROM depense join caisse "
			+ "on depense.caisse_id_caisse=caisse.id_caisse "
			+ "WHERE depense.isdeleted = 0 AND caisse.libelle=:libCaisse GROUP BY caisse.libelle",nativeQuery = true)
	public int totalDepenseParCaisse(@Param("libCaisse") String libCaisse);
	
	
	 @Query ("SELECT d FROM Depense d WHERE d.caisse.libelle LIKE %?1%" 
			 + "OR d.personnel.nom LIKE %?1% OR d.gestionnaireCaisse.utilisateur LIKE %?1% OR d.dateDepense LIKE %?1%")
    public	List<Depense> findByMotifContains(String parameter);

}
