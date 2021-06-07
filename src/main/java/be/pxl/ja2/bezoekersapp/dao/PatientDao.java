package be.pxl.ja2.bezoekersapp.dao;

import be.pxl.ja2.bezoekersapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao extends JpaRepository<Patient, String> {
    // JpaRepository wilt T (patient) en ID (type id = String)
    // FindById, FindAll, Delete, Save, ... zitten allemaal standaard in JpaRepository

}
