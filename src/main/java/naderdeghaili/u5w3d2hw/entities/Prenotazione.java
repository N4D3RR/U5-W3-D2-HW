package naderdeghaili.u5w3d2hw.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    @Column(nullable = false)
    private LocalDate dataCreazione;
    private String note;

    public Prenotazione() {
    }

    public Prenotazione(Viaggio viaggio, Dipendente dipendente, String note) {

        this.viaggio = viaggio;
        this.dipendente = dipendente;
        this.dataCreazione = LocalDate.now();
        this.note = note;
    }

    public UUID getId() {
        return id;
    }

    public Viaggio getViaggio() {
        return viaggio;
    }

    public void setViaggio(Viaggio viaggio) {
        this.viaggio = viaggio;
    }

    public Dipendente getDipendente() {
        return dipendente;
    }

    public void setDipendente(Dipendente dipendente) {
        this.dipendente = dipendente;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Prenotazione: " +
                "id: " + id +
                " | viaggio: " + viaggio.getDestinazione() + " " + viaggio.getDataViaggio() +
                " | dipendente: " + dipendente.getNome() + " " + dipendente.getCognome() +
                " | data Creazione: " + dataCreazione +
                " | note: " + note
                ;
    }
}
