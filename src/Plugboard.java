public class Plugboard {
    // Variables
    private String[] wiring;

    // Constructors
    public Plugboard() {
        this.wiring = null;
    }

    public Plugboard(String[] wiring) {
        this.wiring = wiring;
    }

    // Methods

    /**
     * encrypts a character as per the plugboard wiring
     * @param letter an un encrypted character
     * @return and encrypted character
     */
    public char encrypt(char letter) {
        // iterate through plugboard wiring (each string is a pair characters
        for (String s : wiring ) {
            // check if one of the characters is the input character
            if (s.contains(Character.toString(letter))) {
                // if the input character is present "swap" set to the other character in the pair
                if (letter == s.charAt(0)) {
                    return s.charAt((1));
                } else {
                    return s.charAt(0);
                }
            }
        }
        return letter;
    }




}
