package be.pxl.ja2.bezoekersapp.dao;

import be.pxl.ja2.bezoekersapp.model.Afdeling;
import be.pxl.ja2.bezoekersapp.model.Bezoeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BezoekerDao extends JpaRepository<Bezoeker, Long> {
    public Optional<Bezoeker> findBezoekerByPatient_Code(String patientCode);
    public List<Bezoeker> findBezoekersByTijdstipAndPatient_Afdeling(LocalTime tijdstip, Afdeling afdeling);
    public List<Bezoeker> findBezoekersByPatient_Afdeling_Code(String afdelingCode);
}
