import java.util.ArrayList;

public class MitgliedManager {
    //Attribute
    public ArrayList<Mitglied> mitgliedListe;
    public ArrayList<Kurs> kursListe;

    //Constructor
    public MitgliedManager() {
        mitgliedListe = new ArrayList<Mitglied>();
        kursListe = new ArrayList<Kurs>();
    }

    //Methoden
    public void neuesMitgliedAnlegen(int Mitgliednummer, String Name, String Geburtsdatum, String Geschlecht) {
        Mitglied tmpMitglied = new Mitglied(Mitgliednummer, Name, Geburtsdatum, Geschlecht); //tmp=temporär wird nur kurzzeitig in die Liste eingefügt und löscht sich danach von selbst, sobald es in der liste eingefügt wurde wird die kopie gelöscht
        mitgliedListe.add(tmpMitglied);
    }

    public void neuenKursAnlegen(int Kursnummer, String Kursname) {
        Kurs tmpKurs = new Kurs(Kursnummer, Kursname); //temp=temporär
        kursListe.add(tmpKurs);
    }

    public void mitgliedLoeschen(Mitglied mitglied) {
        for (Kurs kurs : kursListe) {
            kurs.mitgliedEntfernen(mitglied);
        }
        mitgliedListe.remove(mitglied);
    }

    public void kursloeschen(Kurs kurs) {
        for (Mitglied mitglied : mitgliedListe) {
            mitglied.abmeldenfuerKurs(kurs);
        }
        kursListe.remove(kurs);
    }

}
