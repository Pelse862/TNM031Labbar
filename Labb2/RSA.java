import java.math.*;
import java.util.*;

public class RSA
{

    public static void main(String[] args) {

        BigInteger p, q, n, d = new BigInteger("0");
        BigInteger c, e, m = new BigInteger("0");



        String message = "";
        String encryptKey = "";

        p = generatePrime(128);
        q = generatePrime(128);
        e = generatePrime(123);
        n = p.multiply(q);
        BigInteger one = new BigInteger("1");
        n = p.multiply(q);
        d = (p.subtract(one).multiply(q.subtract(one)));
       // z = BigInteger.valueOf(d);




        while(e.gcd(d).intValue() > 1){
            e = generatePrime(123);
        }

        BigInteger d2 = e.modInverse(d);

        if((d2.intValue() > d.intValue())){
            System.out.println("s");
        }


        //Create Message
        message = readMessage();
        int i = 0;

        while(i < message.length()){
            char res = message.charAt(i);
            encryptKey += indexOfLetter(Character.toString(res));
            i++;
        }

        BigInteger P  =  new BigInteger(String.valueOf(encryptKey));
        System.out.println(P);

        //since Public keys are known (e,n)
        // e = P^e
        c = P.modPow(e,n);
       // c = P.pow(e.intValue());
       // c = c.mod(n);
        System.out.println("c_1 = " + c);

        //c is sent to sender
        //private keys are known (d,n)
        // m = c^j%
        m = c.modPow(d2,n);
        System.out.println(m);
        //fungerar när resten fungerar ordentligt
        decrypt(m);

    }

    public static void decrypt(BigInteger s){

        String lengthString = ""+s;

        int length = lengthString.length();

        System.out.println("length of message =" +length);

        int encrypter = s.intValue();

        String alph = "abcdefghijlkmnopqrstuvwxyz123";

        String de = "";
        int i = 0;
        if(length %2 == 0)
        {
            String string = ""+encrypter;
            while(i < length)
            {
               // System.out.println(i + " < " + length);
                String sub_s = string.substring(i, i+2);
                de += Character.toString(alph.charAt(Integer.valueOf(sub_s)-1));
                i = i+2;
            }
        }

        else
        {
            String string = "0"+encrypter;
            length = length+1;
            while(i < length)
            {
              //  System.out.println(i + " < " + length);
                String sub_s = string.substring(i, i+2);
                de += Character.toString(alph.charAt(Integer.valueOf(sub_s)-1));
                i = i+2;
            }
        }



        System.out.println(de);
    }

    public static String indexOfLetter(String s)
    {

        String alph = "abcdefghijlkmnopqrstuvwxyz123";
        //return 0+index or just index
        if(alph.indexOf(s) < 10){
            return "0"+Integer.toString(alph.indexOf(s)+1);
        }
        return Integer.toString(alph.indexOf(s)+1);

    }


    public static String readMessage()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your Message: ");
        String string = scanner.next();
        return string;
    }

    public static BigInteger readInput(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        String p_string = scanner.next();

        return (new BigInteger(p_string));
    }

    public static BigInteger generatePrime(int bitLength)
    {
        // create and assign value to bitLength

        // create a random object
        Random rnd = new Random();
        // assign probablePrime result to bi using bitLength and rnd
        // static method is called using class name
        BigInteger bi = BigInteger.probablePrime(bitLength, rnd);
        return bi;

    }
}


/*

/ create a scanner so we can read the command-line input
    Scanner scanner = new Scanner(System.in);

    //  prompt for the user's name
    System.out.print("Enter your name: ");

    // get their input as a String
    String username = scanner.next();

    // prompt for their 1age
    System.out.print("Enter your age: ");

    // get the age as an int
    int age = scanner.nextInt();

 */