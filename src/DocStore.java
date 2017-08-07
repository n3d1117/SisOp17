import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class DocStore {

    // Semaforo utilizzato per essere sicuri che getPage() aspetti finchè l'array htmlPages è vuoto
    private Semaphore s = new Semaphore(0, true);

    // Semaforo utilizzato per mutua esclusione tra thread
    private Semaphore mutex = new Semaphore(1);

    // Array di pagine html di cui effettuare il parsing
    private ArrayList<String> htmlPages = new ArrayList<>();

    public String getPage() throws InterruptedException {
        s.acquire();
        mutex.acquire();
        String page = htmlPages.get(0);
        htmlPages.remove(0);
        mutex.release();
        return page;
    }

    public void addPage(String page) throws InterruptedException {
        mutex.acquire();
        htmlPages.add(page);
        mutex.release();
        s.release();
    }

}
