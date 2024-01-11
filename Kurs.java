import java.util.ArrayList;

public class Kurs {

    public int Kursnummer; // Eigenschaften der Klasse
    public String Kursname;

    public ArrayList<Mitglied> mitgliederListe;


    public Kurs (int Kursnummer, String Kursname){ // konstruktor der die objekte ausführt
        this.Kursnummer=Kursnummer; // trägt die objekte ein
        this.Kursname=Kursname;
        this.mitgliederListe=new ArrayList<Mitglied>(); // Liste wird initialisiert
    }

    public void mitgliedHinzufuegen (Mitglied mitglied){
        mitgliederListe.add(mitglied);

    }
}

