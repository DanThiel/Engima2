
public class Main {
    public static void main(String[] args) {
        // https://www.cryptomuseum.com/crypto/enigma/wiring.htm
        // https://www.101computing.net/enigma-machine-emulator/

        // initialise Enigma settings
        Settings settings = new Settings();
        settings.setPlugboardWiring(new String[]{"AB","CD","EF"});

        // initialise encryption engine
        EncryptionEngine encrypter = new EncryptionEngine(settings);

        // message to encode
        String message = "hello world";

        // print encoded message
        System.out.println(encrypter.encrypt(message));

        // todo
        /**
         * protect against none A-Z character inputs
         * implement ring settings
         * upload to git
         * create an interface
         */






    }
}