import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    String enhancedCipher(String input, int key){
       String output = "";
        for(int i = 0; i < input.length(); i++){
          char encrypted = input.charAt(i);
            if(Character.isLowerCase(encrypted)){
              //System.out.println("(1)Source letter: " + encrypted);
              output += (char)(((encrypted - 'a') + key + 26) % 26 + 'a');
            }else if(Character.isUpperCase(encrypted)){
              //System.out.println("(2)Source letter: " + encrypted);
              output += (char)(((encrypted - 'A') + key +26) % 26 + 'A');
            }else{
              output += (encrypted);
            }
        }
       return output;
    }

    String SampleDecrypt(String input){
      String output = "";
      String[] inputMod = input.split(" ");
      String comparator = inputMod[0];
      try {
          for(int i = 0; i < 26; i++){
            Scanner fileIn = new Scanner(new File("sample.txt")).useDelimiter("\\s+|\\n");
            String current = enhancedCipher(comparator, -i);
            System.out.println("Testing Shift " + i + " = " + current);
              while(fileIn.hasNext()){
                String read = fileIn.next();
                //System.out.println("Comparing " + current + " to " + read);
                if(current.equals(read)){
                  output = enhancedCipher(input, -i) + " (Key = " + i + ")";
                  break;
                } 
             }
          }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
  
      return output;
    }
  
    public static void main(String[] args) {
        Main c = new Main();
        System.out.println("Welcome to Amadeo Costaldi's CSCE 4433 Enhanced Caesar Cipher Program\n");
        System.out.println("Would you like to: ");
        System.out.println("a) Encrypt a Message");
        System.out.println("b) Decrypt a Message");
        System.out.println("c) Decrypt Ciphertext based on Bruteforce Attack and return key");
        System.out.println("d) Quit");
        Scanner scan1 = new Scanner(System.in);
        String choice = scan1.nextLine();
        if(choice.equals("a")){
            System.out.println("Enter the message to encrypt:");
            String message = scan1.nextLine();
            System.out.println("Enter the encryption key (an integer):");
            int key = scan1.nextInt();
            scan1.nextLine(); 

            // Call the Encrypt function
            String encryptedMessage = c.enhancedCipher(message, key);
            System.out.println("Encrypted message: " + encryptedMessage);
        }else if(choice.equals("b")){
            System.out.println("Enter the message to decrypt:");
            String message = scan1.nextLine();
            System.out.println("Enter the decryption key (an integer):");
            int key = scan1.nextInt();
            scan1.nextLine(); // Consume the newline character

            // Call the Decrypt function
            String decryptedMessage = c.enhancedCipher(message, -key);
            System.out.println("Decrypted message: " + decryptedMessage);
        }else if(choice.equals("c")){
            System.out.println("Enter the message to decrypt:");
            String message = scan1.nextLine();
            System.out.println("Decrypted message: " + c.SampleDecrypt(message));
        }else{
            System.out.println("Please choose a listed option :(");
        }
        scan1.close();
    }
  }