import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Kurs {

    public int Kursnummer; // Attribute (Eigenschaften) der Klasse
    public String Kursname;

    public ArrayList<Mitglied> kursMitgliederListe;


    public Kurs(int Kursnummer, String Kursname) { // konstruktor der die objekte ausführt
        this.Kursnummer = Kursnummer; // trägt die objekte ein
        this.Kursname = Kursname;
        this.kursMitgliederListe = new ArrayList<Mitglied>(); // Liste wird initialisiert
    }

    public void mitgliedHinzufuegen(Mitglied mitglied) {
        kursMitgliederListe.add(mitglied);
    }

    public void mitgliedEntfernen(Mitglied mitglied) {
        kursMitgliederListe.remove(mitglied);
    }

    public String getKursname() { // ist das notwendig? - vielleicht
        return Kursname;
    }

    public void setKursname(String neuerKursname) { //set ist immer void
        this.Kursname = neuerKursname; //mit this greife ich auf das Attribut kursname zu was verändert werden soll
    }

    public ArrayList<Mitglied> getKursMitgliederListe() { //Liste soll zurückgegeben werden deswegen return, anstelle von Kursanzeigen weil kein Sinn so
        return kursMitgliederListe;
    }














    // code dient dazu das lesen und schreiben und aktualisieren von mitgliedern in CSV-Datei zu ermöglichen

import java.io.BufferedReader; // was ist das?? // io ist für lesen und schreiben //java.io steht für Input und Output für ein und Ausgabe von Datein
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList; // util ist für Listen und Arrays
import java.util.Arrays;


    public class csvReader {

        public static String testFileName = "testFile.csv"; //Test?
        private static String csvFitness = "mitglieder.csv"; // CSV Fitness ist unsere Hauptdatei
        private static String folderPrefix = "src/CSV-Records/"; //Pfad indem das gespeichert wird

        public static ArrayList<ArrayList<String>> testReadCSVFile() { // ??
            return readCSVFile(testFileName);
        } //

        public static ArrayList<ArrayList<String>> readCSVFile(String fileName) {
            ArrayList<ArrayList<String>> records = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(folderPrefix + fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    records.add(new ArrayList<>(Arrays.asList(values)));
                }
            } catch (FileNotFoundException exc) { // was ist catch?
                System.err.println("Datei konnte nicht in csvReader gefunden werden()");
            } catch (IOException exc) {
                System.err.println("fehler beim Lesen in der csvReader Datei()");
            }

            return records;
        }

        public static void writeCSVFile(String filename, ArrayList<ArrayList<String>> mitglieder) { // methode schreibt eine csv datei
            System.out.println(mitglieder);
            try (PrintWriter writer = new PrintWriter(folderPrefix + filename)) {

                StringBuilder sb = new StringBuilder();

                for (ArrayList<String> mitglied : mitglieder) {
                    for (String value : mitglied) {
                        sb.append(value);
                        sb.append(";");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append('\n');
                }
                System.out.println(sb.toString());
                writer.write(sb.toString());

                System.out.println("done!");

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void updateCsv(String filename, ArrayList<ArrayList<String>> mitgliedAsLst) { // hier wird datei aktualisiet
            writeCSVFile(filename, mitgliedAsLst);
        }

        public static boolean mitgliedIdVerfuegbar(int mitgliedID) { // überprüft ob mitglieder-ID in Datei vorhanden ist
            ArrayList<String> mitgliedIDs = getAllIds(csvFitness);
            var mitgliedIdAlsStr = Integer.toString(mitgliedID);
            if (mitgliedIDs.contains(mitgliedIDAlsStr)) { // was ist hier das problem
                return false;
            }
            return true;
        }

        private static ArrayList<String> getAllIds(String filename) {
            return getAlleVonEinemInfoTyp(filename, "Id");
        } //warum private?

        public static ArrayList<String> getColumn(String filename) {
            ArrayList<ArrayList<String>> mitglieder = readCSVFile(filename);
            ArrayList<String> mitgliedInfoNamen = mitglieder.remove(0);
            return mitgliedInfoNamen;
        }

        private static ArrayList<String> getAlleVonEinemInfoTyp(String filename, String infoTyp) {
            ArrayList<String> mitgliederIds = new ArrayList<>();
            ArrayList<ArrayList<String>> mitglieder = readCSVFile(filename);

            ArrayList<String> infoNamen = mitglieder.remove(0);
            int idPosition = getTypePosition(infoNamen, infoTyp);

            for (ArrayList<String> mitglied : mitglieder) {
                mitgliederIds.add(mitglied.get(idPosition));
            }
            return mitgliederIds;
        }

        private static int getTypePosition(ArrayList<String> infoNamen, String infoTyp) {
            int idPosition = 0;
            for (String temp : infoNamen) {
                if (temp.equals(infoTyp)) {
                    break;
                }
                idPosition++;
            }
            return idPosition;
        }

    }

}

