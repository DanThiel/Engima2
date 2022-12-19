import java.util.Arrays;
public class Rotor {
    // Variables
    private String wiring;
    private char[] notches;
    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Constructors
    public Rotor(String wiring, char[] notches) {
        this.wiring = wiring;
        this.notches = notches;
    }

    // Getters and Setters
    public char[] getNotches() {
        return notches;
    }

    // Methods
    /**
     * encrypts a character as per the rotor settings instances wiring
     * @param letter    the input letter
     * @param position  the position of the rotor
     * @param direction direction of travel, true represents right to left.
     * @return
     */
    public char encrypt(char letter, char position, boolean direction) {
        // set adjustment value for rotor position
        int adjustment = Rotor.alphabet.indexOf(position);
        if (direction) {
            // adjust input letter for rotor position
            letter = alphabet.charAt((alphabet.indexOf(letter) + adjustment)%26);
            // encrypt letter as per rotor wiring
            letter = this.wiring.charAt(alphabet.indexOf(letter));
            // adjust output letter for rotor position
            letter = alphabet.charAt((alphabet.indexOf(letter) + 26 - adjustment)%26);

        } else {
            // adjust input letter for rotor position
            letter = alphabet.charAt((alphabet.indexOf(letter) + adjustment)%26);
            // encrypt letter as per rotor wiring
            letter = alphabet.charAt(this.wiring.indexOf(letter));
            // adjust output letter for rotor position
            letter = alphabet.charAt((alphabet.indexOf(letter) + 26 - adjustment)%26);
        }
        return letter;
    }

    @Override
    public String toString() {
        return "Rotor{" +
                "wiring='" + wiring + '\'' +
                ", notches=" + Arrays.toString(notches) +
                '}';
    }
}
