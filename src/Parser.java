public class Parser extends Thread {

    private UrlStore u;
    private DocStore d;

    public Parser(UrlStore u, DocStore d) {
        this.u = u;
        this.d = d;
    }

    @Override
    public void run() {
        try {
            while(true) {
                String url = d.getPage(); // Prelevo pagina HTML
                Thread.sleep(1000); // Simulo parsing pagina HTML
                int rand = (int)(Math.random()*11);
                String[] links = new String[rand]; // Insieme dei link della pagina prelevata
                for (int i=0; i<links.length; i++) {
                    links[i] = "http://website" + i + ".com";
                    u.addUrl(links[i]); // Aggiungo link in UrlStore
                }
                d.increaseAnalyzedPages();
            }
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
