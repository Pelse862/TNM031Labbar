import java.math.*;
import java.util.*;

public class RSA
{

    public static void main(String[] args) {

        BigInteger p, q, n, z = new BigInteger("0");
        BigInteger j, c, e, m = new BigInteger("0");
        BigInteger k = new BigInteger("2");
        BigInteger l = new BigInteger("2");
        int d = 0;
        String message = "";
        String encryptKey = "";

        p = generatePrime();
        q = generatePrime();
        n = p.multiply(q);
        System.out.println("(p = "+ p +",q = "+ q +")");
        d = (p.intValue()-1)*(q.intValue()-1);
        z = BigInteger.valueOf(d);

        System.out.println("d = " + d +'\n'+"z  = " + z);
        while(true) {
            e = k.nextProbablePrime();

            System.out.println( z.intValue() +" % "+ k.nextProbablePrime().intValue());

            if(z.intValue()%k.nextProbablePrime().intValue()  != 0)
            {
                break;
            }
            k = k.nextProbablePrime();
        }
        System.out.println("");
        System.out.println("Beräknar j");
        while(true) {
            j = l.nextProbablePrime();
            System.out.println((e.multiply(j)) + " % " + z + " = " + e.multiply(j).mod(z));

            if( (e.multiply(j)).mod(z).equals( new BigInteger("1"))){
                if(j.intValue() > z.intValue()){
                    j = new BigInteger("0");
                    e = k.nextProbablePrime();
                    k = k.nextProbablePrime();
                }
                else {
                    break;
                }
            }
            l = l.nextProbablePrime();
        }

        // Public keys (j,n)
        //Private keys (e,n)
        System.out.println("");

        System.out.println("( j = "+ j +", n = "+ n +")");
        System.out.println("( e = "+ e +", n = "+ n +")");
        //Create Message
        message = readMessage();
        int i = 0;
        while(i < message.length()){
            char res = message.charAt(i);
            encryptKey += indexOfLetter(Character.toString(res));
            i++;
        }
        BigInteger P  =  new BigInteger(String.valueOf(encryptKey));
        System.out.println("P = " + P);


        System.out.println("("+ j +","+ n +")");
        System.out.println("("+ e +","+ n +")");




        //since Public keys a  re known (j,n)
        // e = P^e
        System.out.println("n = "+  n);
        System.out.println("j = "+  j);
        System.out.println("");
        c = P.pow(j.intValue());
//        System.out.println("c_1 = " + c);
        c = c.mod(n);
  //      System.out.println("c_2 = " + c);

        //c is sent to sender
        //private keys are known (e,n)
        // m = c^j%33
        m = c.pow(e.intValue());
        System.out.println("m_1 = " + m);
        m = m.mod(n);
        System.out.println("m_2 = " + m);
        //fungerar när resten fungerar ordentligt
        decrypt(m);

    }
    public static void decrypt(BigInteger s){
        String lengthString = ""+s;
        int length = lengthString.length();
       // System.out.println( length.length() );

        int encrypter = s.intValue();

        String alph = "abcdefghijlkmnopqrstuvwxyz";
        int i = 0;
        String de = "";
        System.out.println("length of message =" +length);
        if(length %2 == 0){
            String string = ""+encrypter;

            while(i < length)
            {
               // System.out.println(i + " < " + length);
                String sub_s = string.substring(i, i+2);
                de += Character.toString(alph.charAt(Integer.valueOf(sub_s)-1));
                i = i+2;
            }
        }
        else{
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

        String alph = "abcdefghijlkmnopqrstuvwxyz";
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

    public static BigInteger generatePrime()
    {
        // create and assign value to bitLength
        int bitLength = 9;
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