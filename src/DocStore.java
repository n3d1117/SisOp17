import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class DocStore {

    private Semaphore sem = new Semaphore(0, true);
    private Semaphore mutex = new Semaphore(1);

    // Pagine html di cui effettuare il parsing
    private ArrayList<String> htmlPages = new ArrayList<>();

    public String getPage() throws InterruptedException {
        sem.acquire();
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
        sem.release();
    }

}
