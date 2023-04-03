import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Fazer a conexão HTTP e buscar a lista de filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Extrair os dados e inserir numa estrutura de dados
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilme = parser.parse(body);

        // Manipular e exibir os filmes
        for (Map<String,String> filme : listaDeFilme) {
            String urlImage = filme.get("image");
            String title = filme.get("title");
            
            InputStream inputStream = new URL(urlImage).openStream();
            
            String filename = "output/" + title + ".png"; 
            StickerGenerator generator = new StickerGenerator();
            generator.create(inputStream, filename);

            System.out.println(title);

        }
    }
}
