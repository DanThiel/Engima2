/**
 * An instance of setting contains the current settings of the Enigma machine
 */

// Variables
public class Settings {
    private String[] rotorsSelected;
    private int[] rotorsSlipRing;
    private String[] plugboardWiring;
    private String rotorsStartPositions;
    private String reflector;

    // Constructors
    public Settings() {
        this.rotorsSelected = new String[]{"I", "II", "III",};
        this.rotorsSlipRing = new int[]{0, 0, 0,};
        this.plugboardWiring = null;
        this.rotorsStartPositions = "AAA";
        this.reflector = "UKW-B";
    }

    public Settings(String[] rotorsSelected,
                    int[] rotorsSlipRing,
                    String[] plugboardWiring,
                    String rotorsStartPositions,
                    String reflector) {
    }

    // Getters and Setters
    public String[] getRotorsSelected() {
        return rotorsSelected;
    }
    public void setRotorsSelected(String[] rotorsSelected) {
        this.rotorsSelected = rotorsSelected;
    }
    public int[] getRotorsSlipRing() {
        return rotorsSlipRing;
    }
    public void setRotorsSlipRing(int[] rotorsSlipRing) {
        this.rotorsSlipRing = rotorsSlipRing;
    }
    public String[] getPlugboardWiring() {
        return plugboardWiring;
    }
    public void setPlugboardWiring(String[] plugboardWiring) {
        this.plugboardWiring = plugboardWiring;
    }
    public String getRotorsStartPositions() {
        return rotorsStartPositions;
    }
    public void setRotorsStartPositions(String rotorsStartPositions) {
        this.rotorsStartPositions = rotorsStartPositions;
    }
    public String getReflector() {
        return reflector;
    }
    public void setReflector(String reflector) {
        this.reflector = reflector;
    }
}
