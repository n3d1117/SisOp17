import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class UrlStore {

    private Semaphore sem = new Semaphore(1, true);

    // Contiene le url che devono essere analizzate
    private ArrayList<String> urls = new ArrayList<>();

    public UrlStore(String initial) {
        urls.add(initial);
    }

    public String getUrl() throws InterruptedException {
        sem.acquire();
        String url = urls.get(0);
        urls.remove(0);
        return url;
    }

    public void addUrl(String url) {
        urls.add(url);
        sem.release();
    }

}
