import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Fazer a conexão HTTP e buscar a lista de filmes
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
       //  String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2023-04-02&end_date=2023-04-03";
       
       String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2023-04-01";

        HTTPClient http = new HTTPClient();
        String json = http.seachData(url);

        // Extrair os dados e inserir numa estrutura de dados
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listOfContent = parser.parse(json);

        // Manipular e exibir os filmes
        for (Map<String,String> content : listOfContent) {
            String urlImage = content.get("url");
            String title = content.get("title");
            
            InputStream inputStream = new URL(urlImage).openStream();
            
            String filename = "output/" + title + ".png"; 
            StickerGenerator generator = new StickerGenerator();
            generator.create(inputStream, filename);

            System.out.println(title);

        }
    }
}
