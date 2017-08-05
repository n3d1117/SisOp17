import java.util.Scanner;

public class Esercizio {

    public static void main(String[] args) {
        int N, M;

        // Prendo N e M da input
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("Inserisci N (>0) -->");
            N = s.nextInt();

            System.out.println("Inserisci M (>0) -->");
            M = s.nextInt();
        } while(N<=0 || M<=0);

        System.out.println("N è " + N + ", M è " + M + ".");

        Crawler[] c = new Crawler[N];
        Parser[] p = new Parser[M];

    }
}
