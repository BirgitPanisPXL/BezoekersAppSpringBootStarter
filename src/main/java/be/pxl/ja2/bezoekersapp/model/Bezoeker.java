package be.pxl.ja2.bezoekersapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Bezoeker {
	@Id
	@GeneratedValue
	// ID wordt automatisch gegenereerd > Staat standaard op "auto"
	private Long id;
	private String naam;
	private String voornaam;
	private String telefoonnummer;
	private LocalTime tijdstip;
	@OneToOne
	// 1 patiënt krijgt 1 bezoeker
	private Patient patient;
	private LocalDateTime aanmelding;

	public Bezoeker() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalTime getTijdstip() {
		return tijdstip;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getTelefoonnummer() {
		return telefoonnummer;
	}

	public void setTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}

	public void setTijdstip(LocalTime tijdstip) {
		this.tijdstip = tijdstip;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getInfo() {
		return naam + " " + voornaam + " (tel: " + telefoonnummer + ")";
	}

	public LocalDateTime getAanmelding() {
		return aanmelding;
	}

	public void setAanmelding(LocalDateTime aanmelding) {
		this.aanmelding = aanmelding;
	}

	public boolean isReedsAangemeld(LocalDate datum) {
		return aanmelding != null && aanmelding.toLocalDate().equals(datum);
	}
}
