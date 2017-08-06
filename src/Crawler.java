public class Crawler extends Thread {

    private UrlStore u;
    private DocStore d;

    public Crawler(UrlStore u, DocStore d) {
        this.u = u;
        this.d = d;
    }

    @Override
    public void run() {
        try {
            while(true) {
                String url = u.getUrl(); // Prelevo URL
                Thread.sleep(1000); // Simulo download pagina HTML
                String html = "html_from_url"; // Pagina HTML scaricata
                d.addPage(html); // Salvo pagina in DocStore
                u.increaseDownloads(); // Incrementa numero download
            }
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
