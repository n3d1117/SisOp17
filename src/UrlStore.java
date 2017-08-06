import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class UrlStore {

    private Semaphore sem = new Semaphore(1, true);
    private Semaphore mutex = new Semaphore(1);

    // Contiene le url che devono essere analizzate
    private ArrayList<String> urls = new ArrayList<>();

    public UrlStore(String initial) {
        urls.add(initial);
    }

    public String getUrl() throws InterruptedException {
        sem.acquire();
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
        sem.release();
    }

}
