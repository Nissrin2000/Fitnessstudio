import java.util.ArrayList;

public class Mitglied {
    // Eigenschaften
    public int Mitgliednummer;
    public String Name;

    public String Geburtsdatum; // String oder int??

    public String Geschlecht;

    public ArrayList<Kurs> kurse;

    //Konstruktor
    public Mitglied(int Mitgliednummer, String Name, String Geburtsdatum, String Geschlecht) {
        this.Mitgliednummer = Mitgliednummer;
        this.Name = Name;
        this.Geburtsdatum = Geburtsdatum;
        this.Geschlecht = Geschlecht;
        kurse = new ArrayList<Kurs>();

    }
    public void anmeldenfuerKurs(Kurs kurs){
        kurse.add(kurs);
    }
    public void abmeldenfuerKurs(Kurs kurs){
        kurse.remove(kurs);
    }
    public ArrayList<Kurs> getKurs(){
        return kurse;
    } //ist das das selbe wie in mitgliedmanager liste
    // Methodenjh

}
