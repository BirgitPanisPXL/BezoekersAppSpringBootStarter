package be.pxl.ja2.bezoekersapp.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public final class BezoekerBuilder {
    private String naam;
    private String voornaam;
    private String telefoonnummer;
    private LocalTime tijdstip;
    private Patient patient;
    private LocalDateTime aanmelding;

    private BezoekerBuilder() {
    }

    public static BezoekerBuilder aBezoeker() {
        return new BezoekerBuilder();
    }

    public BezoekerBuilder withNaam(String naam) {
        this.naam = naam;
        return this;
    }

    public BezoekerBuilder withVoornaam(String voornaam) {
        this.voornaam = voornaam;
        return this;
    }

    public BezoekerBuilder withTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
        return this;
    }

    public BezoekerBuilder withTijdstip(LocalTime tijdstip) {
        this.tijdstip = tijdstip;
        return this;
    }

    public BezoekerBuilder withPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public BezoekerBuilder withAanmelding(LocalDateTime aanmelding) {
        this.aanmelding = aanmelding;
        return this;
    }

    public Bezoeker build() {
        Bezoeker bezoeker = new Bezoeker();
        bezoeker.setNaam(naam);
        bezoeker.setVoornaam(voornaam);
        bezoeker.setTelefoonnummer(telefoonnummer);
        bezoeker.setTijdstip(tijdstip);
        bezoeker.setPatient(patient);
        bezoeker.setAanmelding(aanmelding);
        return bezoeker;
    }
}
