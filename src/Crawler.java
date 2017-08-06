import java.util.concurrent.Semaphore;

public class Crawler extends Thread {

    private UrlStore u;
    private DocStore d;

    // Numero di download effettuati dai Crawler
    static int downloads = 0;
    static Semaphore mutex = new Semaphore(1);

    private int id = 0;

    public Crawler(UrlStore u, DocStore d, int id) {
        this.u = u;
        this.d = d;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while(true) {
                String url = u.getUrl(); // Prelevo URL
                Thread.sleep(550); // Simulo download pagina HTML
                String html = "html_from_" + url; // Pagina HTML scaricata
                d.addPage(html); // Salvo pagina in DocStore

                mutex.acquire();
                downloads++;
                mutex.release();
            }
        } catch(InterruptedException e) {
        }
    }

}
