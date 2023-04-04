import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IMDBContentExtractor implements ContentExtractor {
    public List<Content> contentExtractor(String json){
        // Extrair os dados e inserir numa estrutura de dados
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listOfAttributes = parser.parse(json);

        List<Content> listOfContents = new ArrayList<>();

        for (Map<String, String> attribute : listOfAttributes) {
            String title = attribute.get("title");
            String urlImage = attribute.get("image");
            Content content = new Content(title, urlImage);

            listOfContents.add(content);
        }
    
        return listOfContents;
    }
}
