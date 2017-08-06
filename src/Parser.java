import java.util.concurrent.Semaphore;

public class Parser extends Thread {

    private UrlStore u;
    private DocStore d;

    // Numero di pagine analizzate dai Parser
    static int analyzedPages = 0;
    static Semaphore mutex = new Semaphore(1);

    private int id = 0;

    public Parser(UrlStore u, DocStore d, int id) {
        this.u = u;
        this.d = d;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while(true) {
                String page = d.getPage(); // Prelevo pagina HTML
                Thread.sleep(400); // Simulo parsing pagina HTML
                int rand = (int)(Math.random()*11);
                String[] links = new String[rand]; // Insieme dei link della pagina prelevata
                for (int i=0; i<links.length; i++) {
                    links[i] = "http://website" + i + ".com";
                    u.addUrl(links[i]); // Aggiungo link in UrlStore
                }

                mutex.acquire();
                analyzedPages++;
                mutex.release();
            }
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
