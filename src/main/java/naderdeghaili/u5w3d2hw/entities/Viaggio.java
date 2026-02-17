package naderdeghaili.u5w3d2hw.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "viaggi")
public class Viaggio {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String destinazione;
    @Column(nullable = false)
    private LocalDate dataViaggio;
    @Column(nullable = false)
    private String statoViaggio; //false prenotato, true completato

    public Viaggio() {
    }

    public Viaggio(String destinazione, LocalDate dataViaggio) {
        this.destinazione = destinazione;
        this.dataViaggio = dataViaggio;
        this.statoViaggio = "PRENOTATO";
    }


    public UUID getId() {
        return id;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public LocalDate getDataViaggio() {
        return dataViaggio;
    }

    public void setDataViaggio(LocalDate dataViaggio) {
        this.dataViaggio = dataViaggio;
    }

    public String getStatoViaggio() {
        return statoViaggio;
    }

    public void setStatoViaggio(String statoViaggio) {
        this.statoViaggio = statoViaggio;
    }

    @Override
    public String toString() {
        return "Viaggio: " +
                "id: " + id +
                " | destinazione: " + destinazione +
                " | data Viaggio: " + dataViaggio +
                " | stato Viaggio: " + statoViaggio
                ;
    }
}
