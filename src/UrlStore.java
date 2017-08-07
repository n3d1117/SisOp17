import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class UrlStore {

    // Semaforo utilizzato per essere sicuri che getUrl() aspetti finchè l'array urls è vuoto
    private Semaphore s = new Semaphore(1, true);

    // Semaforo utilizzato per mutua esclusione tra thread
    private Semaphore mutex = new Semaphore(1);

    // Array di url che devono essere analizzati
    private ArrayList<String> urls = new ArrayList<>();

    public UrlStore(String initial) {
        urls.add(initial);
    }

    public String getUrl() throws InterruptedException {
        s.acquire();
        mutex.acquire();
        String url = urls.get(0);
        urls.remove(0);
        mutex.release();
        return url;
    }

    public void addUrl(String url) throws InterruptedException {
        mutex.acquire();
        urls.add(url);
        mutex.release();
        s.release();
    }

}
