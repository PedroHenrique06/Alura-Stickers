import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {  

        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        // Extrator para a API da IMDB
        // ContentExtractor extractor = new IMDBContentExtractor();
        
        // Fazer a conexão HTTP e buscar a lista de conteúdos
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2023-03-28&end_date=2023-04-03";

        // Extrator para a API da NASA
        ContentExtractor extractor = new NasaContentExtractor();

        HTTPClient http = new HTTPClient();
        String json = http.seachData(url);

        // Lista de conteúdos extraidos
        List<Content> listOfContent = extractor.contentExtractor(json);

        // Manipular e exibir os filmes
        for (Content content : listOfContent) {            
            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            String filename = "output/" + content.getTitle() + ".png"; 
            
            StickerGenerator generator = new StickerGenerator();
            generator.create(inputStream, filename, content.getTitle());

            System.out.println(content.getTitle());
        }
    }
}
