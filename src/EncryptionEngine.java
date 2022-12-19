import java.util.HashMap;
import java.util.Map;
import static java.lang.Character.toUpperCase;

/**
 * The encryption engine object represents the physical encryption components of the machine
 * including rotors, reflector and plugboard. A settings object is passed to the encryption engine upon
 * construction.
 */


public class EncryptionEngine {
    // Static Variables
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Map<String, String[]> stock_rotors = new HashMap<>();
    private static Map<String, String> stock_reflectors = new HashMap<>();
    static {
        String[] I = {"EKMFLGDQVZNTOWYHXUSPAIBRCJ","Q"};
        String[] II = {"AJDKSIRUXBLHWTMCQGZNPYFVOE","E"};
        String[] III = {"BDFHJLCPRTXVZNYEIWGAKMUSQO","V"};
        String[] IV = {"ESOVPZJAYQUIRHXLNFTGKDCMWB","J"};
        String[] V = {"VZBRGITYUPSDNHLXAWMJQOFECK","Z"};
        String[] VI = {"JPGVOUMFYQBENHZRDKASXLICTW","ZM"};
        String[] VII = {"NZJHGRCXMYSWBOUFAIVLPEKQDT","ZM"};
        String[] VIII = {"FKQHTLXOCBJSPDZRAMEWNIUYGV","ZM"};

        stock_rotors.put("I", I);
        stock_rotors.put("II", II);
        stock_rotors.put("III", III);
        stock_rotors.put("IV", IV);
        stock_rotors.put("V", V);
        stock_rotors.put("VI", VI);
        stock_rotors.put("VII", VII);
        stock_rotors.put("VIII", VIII);
        stock_reflectors.put("UKW-B", "YRUHQSLDPXNGOKMIEBFZCWVJAT");
        stock_reflectors.put("UKW-C", "FVPJIAOYEDRZXWGCTKUQSBNMHL");
    }

    // Instance Variables
    private Rotor[] rotors = new Rotor[3];
    private Reflector reflector;
    private Plugboard plugboard = null;
    private char[] rotorPositions;



    /**
     * Creates a new instance of the enigma encryption engine.
     * The Encryption engine consists of the 3 rotors and one reflector
     * @param settings a settings object, sets the initial state of enigma
     */
    public EncryptionEngine(Settings settings) {
        // Set the names of the reflector and rotors from settings
        String reflectorName = settings.getReflector();
        String[] rotorNames = settings.getRotorsSelected();

        // initialise reflector and rotor instances
        reflector = new Reflector(stock_reflectors.get(reflectorName));
        rotors[0] = new Rotor(stock_rotors.get(rotorNames[0])[0],
                stock_rotors.get(rotorNames[0])[1].toCharArray());
        rotors[1] = new Rotor(stock_rotors.get(rotorNames[1])[0],
                stock_rotors.get(rotorNames[1])[1].toCharArray());
        rotors[2] = new Rotor(stock_rotors.get(rotorNames[2])[0],
                stock_rotors.get(rotorNames[2])[1].toCharArray());
        rotorPositions = settings.getRotorsStartPositions().toCharArray();

        // initialise new instance of plugboard if plugboard settings are not null
        if (settings.getPlugboardWiring() != null) {
            plugboard = new Plugboard(settings.getPlugboardWiring());
        }
    }

    /**
     * moves the rotors forward one increment. To be executed before each letter is encrypted
     */
    private void incrementRotorPositions() {
        // rotor at position 0 only increments if notch on rotor 1 is in position
        for (char notch : rotors[1].getNotches()) {
            if (notch == this.rotorPositions[1]) {
                this.rotorPositions[0] = LETTERS.charAt((LETTERS.indexOf(this.rotorPositions[0]) + 1) % 26);
            }
        }
        // rotor at position 1 only increments if notch on rotor 2 is in position
        for (char notch : rotors[2].getNotches()) {
            if (notch == this.rotorPositions[2]) {
                this.rotorPositions[1] = LETTERS.charAt((LETTERS.indexOf(this.rotorPositions[1])+1)%26);
            }
        }
        // Rotor 2 always increments prior to encryption of each character
        this.rotorPositions[2] = LETTERS.charAt((LETTERS.indexOf(this.rotorPositions[2])+1)%26);
    }

    /**
     * Completes the encryption process for a single character give the
     * current state of the encryption engine.
     * @param input_char a character the requires encryption
     * @return the encrypted character
     */
    private char encryptLetter(char input_char) {
        // Enigma is not case sensitive, parse all characters to upper case
        char letter = toUpperCase(input_char);
        // increment the rotors prior to the encryption of each character
        this.incrementRotorPositions();
        // plugboard encryption only required if plugboard settings are not null
        if (plugboard != null) {
            letter = plugboard.encrypt(letter);
        }
        // character encrypted right to left through rotors 2,1,0
        for (int i=2; i>=0; i--) {
            letter = rotors[i].encrypt(letter,this.rotorPositions[i],true);
        }
        // preform reflector encryption
        letter = reflector.encrypt(letter);
        // character encrypted left to right through rotors 0,1,2
        for (int i=0; i<=2; i++) {
            letter = rotors[i].encrypt(letter,this.rotorPositions[i],false);
        }
        // plugboard encryption only required if plugboard settings are not null
        if (plugboard != null) {
            letter = plugboard.encrypt(letter);
        }
        return letter;
    }

    /**
     * Encrypts an entire string by progressing the rotor positions incrementally while
     * passing characters to encryptLetter
     * @param input_string a string of letter to be encrypted
     * @return an encrypted string
     */
    public String encrypt(String input_string) {
        int i = 0;
        // process string as char array
        char[] inputChars = input_string.toCharArray();
        StringBuilder outputString = new StringBuilder();
        // encrypt each character in turn
        for (char letter : inputChars) {
            i++;
            // insert a space after every 5 characters
            if (i%6==0) {
                outputString.append(' ');
            }
            // skip spaces, enigma only accepts chars A-Z
            if (letter != ' ') {
                outputString.append(this.encryptLetter(letter));
            }
        }
        return outputString.toString();
    }
}
