import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class DocStore {

    private Semaphore sem = new Semaphore(1, true);

    // Numero di pagine analizzate dai Parser
    private int analyzedPages;

    // Pagine html di cui effettuare il parsing
    private ArrayList<String> htmlPages = new ArrayList<>();

    public String getPage() throws InterruptedException {
        sem.acquire();
        String page = htmlPages.get(0);
        htmlPages.remove(0);
        return page;
    }

    public void addPage(String page) {
        htmlPages.add(page);
        sem.release();
    }

    public void increaseAnalyzedPages() {
        analyzedPages++;
    }

    public int numberOfAnalyzedPages() {
        return analyzedPages;
    }

}
