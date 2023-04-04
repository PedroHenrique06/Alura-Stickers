import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class StickerGenerator {
    public void create( InputStream inputStream, String filename, String message) throws Exception{
        // Ler a imagem
        BufferedImage originalImage = ImageIO.read(inputStream);

        // Cria nova imagem
        int width = originalImage.getWidth();
        int heigth = originalImage.getHeight();
        int newHeigth = heigth + 200;

        BufferedImage newImage = new BufferedImage(width, newHeigth, BufferedImage.TRANSLUCENT);

        // Copiar a imagem original para a nova imagem
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // Configurar a fonte e cor da frase
        graphics.setColor(Color.YELLOW);
        
        Font font = new Font(Font.SANS_SERIF, Font.BOLD , 40);
        graphics.setFont(font);

        // Escrever uma frase na nova imagem 
        graphics.drawString(message, 200, (newHeigth-20));

        // Escrever a nova imagem em um arquivo 
        ImageIO.write(newImage, "png", new File(filename));
    }
}
