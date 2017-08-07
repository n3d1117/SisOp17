import java.util.concurrent.Semaphore;

public class Parser extends Thread {

    private UrlStore u;
    private DocStore d;

    // Numero di pagine analizzate dai Parser
    static int analyzedPages = 0;

    // Semaforo utilizzato per mutua esclusione tra thread durante l'incremento di analyzedPages
    private Semaphore mutex = new Semaphore(1);

    public Parser(UrlStore u, DocStore d) {
        this.u = u;
        this.d = d;
    }

    @Override
    public void run() {
        try {
            while(true) {
                String page = d.getPage(); // Prelevo pagina HTML
                Thread.sleep(450); // Simulo parsing pagina HTML
                int rand = (int)(Math.random()*11);
                String[] links = new String[rand]; // Genero insieme dei link della pagina prelevata
                for (int i=0; i<links.length; i++) {
                    links[i] = "http://website" + i + page + ".com";
                    u.addUrl(links[i]); // Aggiungo link in UrlStore
                }

                mutex.acquire();
                analyzedPages++;
                mutex.release();
            }
        } catch(InterruptedException e) {
            // ...
        }
    }
}
