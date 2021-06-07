package be.pxl.ja2.bezoekersapp.rest.resources;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class RegistreerBezoekerResource {
	@NotEmpty(message = "Patiëntcode moet ingevuld zijn")
	private String patientCode;
	@NotNull(message = "Tijdstip moet ingevuld zijn")
	private LocalTime tijdstip;
	@NotEmpty(message = "Naam mag niet leeg zijn")
	private String naam;
	@NotEmpty(message = "Voornaam mag niet leeg zijn")
	private String voornaam;
	@NotEmpty(message = "Telefoonnummer moet ingevuld zijn")
	private String telefoonnummer;

	public String getPatientCode() {
		return patientCode;
	}

	public void setPatientCode(String patientCode) {
		this.patientCode = patientCode;
	}

	public LocalTime getTijdstip() {
		return tijdstip;
	}

	public void setTijdstip(LocalTime tijdstip) {
		this.tijdstip = tijdstip;
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
}
