import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ColoredPanels {

    // Attribute für die grafische Oberfläche (GUI)
    private static JFrame frame = new JFrame("Color Sorter");
    private static JPanel panel = new JPanel();
    private static JPanel colorPanel = new JPanel();
    private static String[] sortingMethods = { "Rot (Selection Sort)", "Euer Sortieren", "...", "..." };
    private static JComboBox method = new JComboBox<String>(sortingMethods);
    private static JButton button = new JButton("Farben sortieren");
    // Attribute für das Farbarray
    private int lenght;
    private JPanel[] colors;

    /**
     * Der Konstruktor erzeugt ein Farbarray in einer übergebenen Länge und zeigt
     * diese in einer grafischen Oberfläche an
     * 
     * @param l - Anzahl der Farben in dem Farbarray
     */
    public ColoredPanels(int l) {
        // Zunächst wird das Farbarray erzeugt
        lenght = l;
        colors = new JPanel[lenght];
        this.fill();
        // Danach wird die Funktionsweise des Buttons festgelegt
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch ((String) method.getSelectedItem()) {
                    case "Rot (Selection Sort)":
                        selectionSort_Red();
                        break;
                    case "Euer Sortieren":
                        euerSort();
                        break;
                }
            }
        });
        // Abschließend wird der Rest der GUI erzeugt und das Farbarray darin eingefügt
        // und angezeigt
        panel.add(method);
        panel.add(button);
        colorPanel.setLayout(new GridLayout(1, l));
        for (int i = 0; i < lenght; i++) {
            colorPanel.add(colors[i]);
        }
        frame.add(colorPanel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setSize(900, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Die Methode füllt das Farbarray mit zufölligen Farben auf
     */
    private void fill() {
        for (int i = 0; i < lenght; i++) {
            colors[i] = new JPanel();
            this.paint(i, random255(), random255(), random255());

        }
    }

    /**
     * Die Methode gibt eine zufällige Ganzzahl zwischen 0 und 255 zurück
     * 
     * @return int - zufällige Zahl zwischen 0 und 255
     */
    private int random255() {
        return (int) (Math.random() * 256);
    }

    /**
     * Methode gibt die Farbe als Datentyp Color beim Index i des Farbarrays zurück
     * 
     * @param i - Index im Farbarray
     * @return Color - Farbe im Farbarray beim Index i
     */
    public Color getColor(int i) {
        return colors[i].getBackground();
    }

    /**
     * Methode gibt den RGB-Wert für Rot (0 - 255) von der Farbe im Farbarray beim
     * Index i zurück
     * 
     * @param i - Index im Farbarray
     * @return int - RGB Wert für Rot im Farbarray
     */
    public int getRed(int i) {
        return colors[i].getBackground().getRed();
    }

    /**
     * Methode gibt den RGB-Wert für Grün (0 - 255) von der Farbe im Farbarray beim
     * Index i zurück
     * 
     * @param i - Index im Farbarray
     * @return int - RGB Wert für Grün im Farbarray
     */
    public int getGreen(int i) {
        return colors[i].getBackground().getGreen();
    }

    /**
     * Methode gibt den RGB-Wert für Blau (0 - 255) von der Farbe im Farbarray beim
     * Index i zurück
     * 
     * @param i - Index im Farbarray
     * @return int - RGB Wert für Blau im Farbarray
     */
    public int getBlue(int i) {
        return colors[i].getBackground().getBlue();
    }

    /**
     * Methode kann eine Farbe im Farbarray neu "einfärben"
     * 
     * @param i - Index im Farbarray wo die neue Farbe übermalt werden soll
     * @param r - RGB Wert der neuen Farbe für Rot
     * @param g - RGB Wert der neuen Farbe für Grün
     * @param b - RGB Wert der neuen Farbe für Blau
     */
    public void paint(int i, int r, int g, int b) {
        colors[i].setBackground(new Color(r, g, b));
    }

    /** 
     * Die Methode soll dafür sorgen, dass jedes Mal wenn sie aufgerufen wird,
     * das Programm die grafische Oberfläche auffrischt und kurz wartet,
     * so dass man die einzelenen Schritte der Sortierung nachvollziehen kann.
     * 
     * Bis jezt funktiniert, das Warten leider noch nicht für Einzelschritte :(
     * 
     * @param ms - Länge in Millisekunden, die bei jedem Update gewartet werden soll
     */
    private void update(int ms){
        try {
            Thread.sleep(ms);
            //System.out.println(".");
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        for (int i = 0; i < lenght; i++){
            colorPanel.add(colors[i]);
        }
        colorPanel.revalidate();
    }

    /**
     * Methode sortiert das Farbarray nach dem Rotanteil der RGB-Farbe und nutzt dazu die Methode Selection Sort.
     */
    public void selectionSort_Red() {
        for (int i = 0; i < lenght - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < lenght; j++) {
                if (this.getRed(j) < this.getRed(min_idx)) {
                    min_idx = j;
                }
            }
            JPanel temp = colors[min_idx];
            colors[min_idx] = colors[i];
            colors[i] = temp;
            this.update(0);
        }
    }
    
    /**
    * Methode die ihr implementieren sollt um ein beliebiges Sortierverfahren zu nutzen,
    * um die Farben nach einem Kriterium eurer Wahl zu sortieren.
    */
    private void euerSort(){

    }
}