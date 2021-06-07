package be.pxl.ja2.bezoekersapp.service;

import be.pxl.ja2.bezoekersapp.dao.BezoekerDao;
import be.pxl.ja2.bezoekersapp.dao.PatientDao;
import be.pxl.ja2.bezoekersapp.model.Bezoeker;
import be.pxl.ja2.bezoekersapp.model.BezoekerBuilder;
import be.pxl.ja2.bezoekersapp.model.Patient;
import be.pxl.ja2.bezoekersapp.rest.resources.BezoekResource;
import be.pxl.ja2.bezoekersapp.rest.resources.RegistreerBezoekerResource;
import be.pxl.ja2.bezoekersapp.util.BezoekerstijdstipUtil;
import be.pxl.ja2.bezoekersapp.util.exception.BezoekersAppException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static be.pxl.ja2.bezoekersapp.model.BezoekerBuilder.aBezoeker;

@Service
public class BezoekersService {
	private static final Logger LOGGER = LogManager.getLogger(BezoekersService.class);
	public static final int BEZOEKERS_PER_TIJDSTIP_PER_AFDELING = 2;

	@Autowired
	private PatientDao patientDao;
	@Autowired
	private BezoekerDao bezoekerDao;

	public Long registreerBezoeker(RegistreerBezoekerResource registreerBezoekerResource) {
		// TODO implement this method
		// Patient uit database halen
		if (!patientDao.existsById(registreerBezoekerResource.getPatientCode())) {
			throw new BezoekersAppException("Deze bezoeker bestaat niet");
			// Van deze exception hebben we een runtimeException gemaakt! Anders moet hij afgehandeld worden.
		}

		Patient patient = patientDao.findById(registreerBezoekerResource.getPatientCode()).get();
		/* FindByID geeft standaard een optional terug. Een optional zegt: het kan een patiënt zijn
			of het kan niks zijn (dit geeft geen null pointer exception).
			Optional kan zijn .isPresent (hij is er, je kan er iets mee doen) of .isEmpty.
			.get() gaat de isPresent of isEmpty niet doen, hij gaat hem gewoon teruggeven.*/

		Optional<Bezoeker> optionalBezoeker = bezoekerDao.findBezoekerByPatient_Code(patient.getCode());
		if (optionalBezoeker.isPresent()) {
			throw new BezoekersAppException("Er is reeds een bezoeker geregistreerd voor patiënt [" + patient.getCode() + "]");
		}

		BezoekerstijdstipUtil.controleerBezoekerstijdstip(registreerBezoekerResource.getTijdstip());
		List<Bezoeker> bezoekersByTijdstipAndPatient_afdeling = bezoekerDao.findBezoekersByTijdstipAndPatient_Afdeling(registreerBezoekerResource.getTijdstip(), patient.getAfdeling());
		if(bezoekersByTijdstipAndPatient_afdeling.size() >= BEZOEKERS_PER_TIJDSTIP_PER_AFDELING) {
			throw new BezoekersAppException("Kies een ander tijdstip.");
		}

		Bezoeker bezoeker = aBezoeker().withTijdstip(registreerBezoekerResource.getTijdstip())
				.withNaam(registreerBezoekerResource.getNaam())
				.withVoornaam(registreerBezoekerResource.getVoornaam())
				.withTelefoonnummer(registreerBezoekerResource.getTelefoonnummer())
				.withPatient(patient)
				.build();

		bezoekerDao.save(bezoeker);
		return bezoeker.getId();
	}

	public void controleerBezoek(Long bezoekerId, LocalDateTime aanmelding) {
		// TODO implement this method
		Bezoeker bezoeker = bezoekerDao.findById(bezoekerId).orElse(null);
		if (bezoeker == null) {
			throw new BezoekersAppException("Bezoeker met id [" + bezoekerId + "] is niet gekend.");
		}
		if(bezoeker.isReedsAangemeld(aanmelding.toLocalDate())) {
			throw new BezoekersAppException("Bezoeker is vandaag reeds aangemeld.");
		}
		// met controleerAanmeldingstijdstip
		BezoekerstijdstipUtil.controleerAanmeldingstijdstip(aanmelding, bezoeker.getTijdstip());

		bezoeker.setAanmelding(aanmelding);
		bezoekerDao.save(bezoeker);
		// SAVEN IS BELANGRIJK!!!
	}

	public List<Bezoeker> getBezoekersVoorAfdeling(String afdelingCode) {
		// TODO implement this method
		return bezoekerDao.findBezoekersByPatient_Afdeling_Code(afdelingCode);
	}
}
