public class Reflector {
    // Variables
    private String wiring;
    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Constructors
    public Reflector(String wiring) {
        this.wiring = wiring;
    }

    // Methods
    /**
     * encrypts a character as per the reflector instances wiring
     * @param letter an un encrypted character
     * @return and encrypted character
     */
    public char encrypt(char letter) {
        // encrypt letter as per reflector wiring
        letter = this.wiring.charAt(alphabet.indexOf(letter));
        return letter;
    }

}
