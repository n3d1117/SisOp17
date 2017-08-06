import java.util.Scanner;

public class Esercizio {

    public static void main(String[] args) throws InterruptedException {
        int N, M;

        // Prendo N e M da input
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("Inserisci N numero di Crawler (>0) -->");
            N = s.nextInt();

            System.out.println("Inserisci M numero di Parser (>0) -->");
            M = s.nextInt();
        } while(N<=0 || M<=0);

        UrlStore u = new UrlStore("https://google.com");
        DocStore d = new DocStore();

        Crawler[] c = new Crawler[N];
        Parser[] p = new Parser[M];

        for (int i=0; i<c.length; i++) {
            c[i] = new Crawler(u, d, i);
            c[i].start();
        }

        for (int i=0; i<p.length; i++) {
            p[i] = new Parser(u, d, i);
            p[i].start();
        }

        for(int i=1; i<=60; i++) {
            Thread.sleep(1000);
            System.out.print("Secondo " + i + " --> ");
            System.out.print("Sono stati effettuati " + Crawler.downloads + " download ");
            System.out.print("e " + Parser.analyzedPages + " parsing.");
            System.out.println();
        }

        for (int i=0; i<c.length; i++) {
            c[i].interrupt();
        }
        for (int i=0; i<p.length; i++) {
            p[i].interrupt();
        }

    }
}
