import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //csv, Gui, Mitgliedmanager kommt rein

     //   package GUI von TicketManager

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ticketManager.Manager;

        public class CreateTicketWindow {
            JFrame frame;

            String selectedBox = "offen";
            String zeitTxt = null;
            String beschreibung = null;
            String kategorie = null;
            String ticketId;
            JTextField tfName;
            JTextField tfBeschreib;
            JTextField tfZeit;
            JTextField tfKategorie;
            Manager manager;

            public CreateTicketWindow() {
                ticketId = getNeueTicketId();
                manager = new Manager();
                setLayout();
                setButtons();
                addTextFields(ticketId);
                addComboBox();
                frame.pack();
                frame.setVisible(true);
            }

            private String getNeueTicketId() {
                int newId = 0;
                Manager manager = new Manager();
                String[] ticketIds = manager.getTicketName();

                for (String ticketId : ticketIds) {
                    String[] ticketIdArr = ticketId.split(" ");
                    int currId = Integer.parseInt(ticketIdArr[0]);
                    if (currId > newId) {
                        newId = currId;
                    }
                }
                return Integer.toString(newId + 1);
            }

            private void setLayout() {
                frame = new JFrame("New Ticket");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setPreferredSize(new Dimension(800, 600));
                frame.setLayout(null);
            }

            private void setButtons() {
                int x = 620;
                int width = 150;
                int height = 30;
                int y = 50;

                JButton close = new JButton("Close");
                close.addActionListener(e -> System.exit(0));
                close.setBounds(x, 500, width, height);

                setAddTicketButton(x, width, height, y);

                JButton back = new JButton("Back to Ticket List");
                back.addActionListener(e -> {
                    frame.setVisible(false);
                    new TicketList(null);
                });
                back.setBounds(x, y + 50, width, height);

                frame.add(close);

                frame.add(back);
            }

            private void setAddTicketButton(int x, int width, int height, int y) {

                JButton addTicket = new JButton("Add this Ticket");
                addTicket.addActionListener(e -> {
                    readFieldsOut();
                    if (alleTextFieldsSindGefuellt()) {
                        manager.addTicketAndSaveInCsv(ticketId, zeitTxt, beschreibung, kategorie);
                        frame.setVisible(false);
                        new TicketList(null);
                    }

                });

                addTicket.setBounds(x, y, width, height);
                frame.add(addTicket);
            }

            private void readFieldsOut() {
                zeitTxt = tfZeit.getText();
                kategorie = tfKategorie.getText();
                beschreibung = tfBeschreib.getText();
            }

            private boolean alleTextFieldsSindGefuellt() {
                if (selectedBox == null | zeitTxt == null | beschreibung == null | kategorie == null) {
                    return false;
                }
                return true;
            }

            private void addTextFields(String ticketId) {
                int x = 20;
                int y = 50;
                int width = 200;
                int height = 50;

                JPanel idPanel = new JPanel();
                idPanel.setBounds(x, y, width, height);
                JLabel idLabel = new JLabel("Ticket Id");
                tfName = new JTextField(ticketId);
                tfName.setEditable(false);
                idPanel.add(idLabel);
                idPanel.add(tfName);

                // Bearbeitungszeit
                JPanel zeitPanel = new JPanel();
                zeitPanel.setBounds(x, y + 50, width, height);
                JLabel zeitLabel = new JLabel("Bearbeitungszeit");
                tfZeit = new JTextField(4);
                zeitPanel.add(zeitLabel);
                zeitPanel.add(tfZeit);

                // beschreibung
                JPanel beschreibPanel = new JPanel();
                beschreibPanel.setBounds(x, y + 150, width + 250, height);
                JLabel beschreibLabel = new JLabel("Beschreibung");
                tfBeschreib = new JTextField(30);
                beschreibPanel.add(beschreibLabel);
                beschreibPanel.add(tfBeschreib);

                // kategorie
                JPanel kategoriePanel = new JPanel();
                kategoriePanel.setBounds(x, y + 100, width + 160, height);
                JLabel kategorieLabel = new JLabel("Kategorie");
                tfKategorie = new JTextField(20);
                kategoriePanel.add(kategorieLabel);
                kategoriePanel.add(tfKategorie);

                frame.add(idPanel);
                frame.add(zeitPanel);
                frame.add(beschreibPanel);
                frame.add(kategoriePanel);
            }

            private void addComboBox() {
                JPanel boxPanel = new JPanel();
                JLabel statusLabel = new JLabel("Ticket Status");
                String[] status = { "offen", "geschlossen" };
                JComboBox<String> statusBox = new JComboBox<String>(status);
                statusBox.setSelectedIndex(0);

                statusBox.addActionListener(e -> {
                    selectedBox = (String) ((JComboBox<String>) e.getSource()).getSelectedItem();

                });

                boxPanel.setBounds(0, 250, 350, 50);
                boxPanel.add(statusLabel);
                boxPanel.add(statusBox);
                frame.add(boxPanel);
            }
        }

        package ticketManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The CSV Dateien sind immer der Speicher f�r alle Tickets und wenn hinzugef�gt
 * wird oder entfernt wird dann auch dort
 * CSV Reader von TicketManager
 *
 */
        public class CsvReader {

            public static String testFileName = "testFile.csv";
            private static String csvTicketFile = "tickets.csv";
            private static String folderPrefix = "src/CSV-Records/";

            public static ArrayList<ArrayList<String>> test_readCSVFile() {
                return readCSVFile(testFileName);
            }

            public static ArrayList<ArrayList<String>> readCSVFile(String fileName) {
                ArrayList<ArrayList<String>> records = new ArrayList<>();
                try (BufferedReader br = new BufferedReader(new FileReader(folderPrefix + fileName))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] values = line.split(";");
                        records.add(new ArrayList<String>(Arrays.asList(values)));
                    }
                } catch (FileNotFoundException exc) {
                    System.err.println("File not Fonud in csvReader()");
                } catch (IOException exc) {
                    System.err.println("Error occurs during reading out the File in csvReader()");
                }

                return records;
            }

            public static void writeCSVFile(String filename, ArrayList<ArrayList<String>> tickets) {
                System.out.println(tickets);
                try (PrintWriter writer = new PrintWriter(folderPrefix + filename)) {

                    StringBuilder sb = new StringBuilder();

                    for (ArrayList<String> ticket : tickets) {
                        for (String value : ticket) {
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

            public static void updateCsv(String filename, ArrayList<ArrayList<String>> ticketAsLst) {
                writeCSVFile(filename, ticketAsLst);
            }

            public static boolean ticketIdVerfuegbar(int ticketId) {
                ArrayList<String> ticketIds = getAllIds(csvTicketFile);
                var ticketIdAlsStr = Integer.toString(ticketId);
                if (ticketIds.contains(ticketIdAlsStr)) {
                    return false;
                }
                return true;
            }

            private static ArrayList<String> getAllIds(String filename) {
                return getAlleVonEinemInfoTyp(filename, "Id");
            }

            public static ArrayList<String> getColumn(String filename) {
                ArrayList<ArrayList<String>> tickets = readCSVFile(filename);
                ArrayList<String> ticketInfoNamen = tickets.remove(0);
                return ticketInfoNamen;
            }

            private static ArrayList<String> getAlleVonEinemInfoTyp(String filename, String infoTyp) {
                ArrayList<String> ticketIds = new ArrayList<>();
                ArrayList<ArrayList<String>> tickets = readCSVFile(filename);

                ArrayList<String> infoNamen = tickets.remove(0);
                int idPosition = getTypePosition(infoNamen, infoTyp);

                for (ArrayList<String> ticket : tickets) {
                    ticketIds.add(ticket.get(idPosition));
                }
                return ticketIds;
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
}
