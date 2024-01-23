import java.util.ArrayList;

public class Mitglied {
    // Eigenschaften
    public int Mitgliednummer;
    
    public String Name;

    public String Geburtsdatum; // String oder int??

    public String Geschlecht;

    private ArrayList<Kurs> kurse;

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
    }
    // Methoden
    public void mitgliedAendern() {
    }// Was kommt in die Klammer ?

}
