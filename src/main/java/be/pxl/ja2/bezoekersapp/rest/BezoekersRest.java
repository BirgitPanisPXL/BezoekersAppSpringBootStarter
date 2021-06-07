package be.pxl.ja2.bezoekersapp.rest;

import be.pxl.ja2.bezoekersapp.rest.resources.BezoekResource;
import be.pxl.ja2.bezoekersapp.rest.resources.RegistreerBezoekerResource;
import be.pxl.ja2.bezoekersapp.service.BezoekersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(path = "bezoekers")
public class BezoekersRest {
	// POST met body die controles gaat uitvoeren en indien juist: statuscode & ID teruggeven.

	private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHH:mm");
	private static final Logger LOGGER = LogManager.getLogger(BezoekersRest.class);

	@Autowired
	private BezoekersService bezoekersService;
	// TODO Add Rest endpoints
	// Hint: gebruik ResponseEntity<Long> als return-type voor het rest endpoint om een bezoeker te registreren
	@PostMapping
	public ResponseEntity<Long> registreerBezoekerVoorPatient(@RequestBody @Valid RegistreerBezoekerResource registreerBezoekerResource) {
		LOGGER.info("Registreer bezoeker");
		Long result = bezoekersService.registreerBezoeker(registreerBezoekerResource);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping
	@RequestMapping("{bezoekersId}/{entranceTimestamp}")
	public ResponseEntity<Void> controleerToegangVoorBezoeker(@PathVariable("bezoekersId") Long bezoekersId, @PathVariable("entranceTimestamp") String entranceTimeStamp) {
		bezoekersService.controleerBezoek(bezoekersId, LocalDateTime.parse(entranceTimeStamp, TIMESTAMP_FORMAT));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}



}
