import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class Creater {

    private static final ArrayList<String> imageUrls = new ArrayList<>();

    public static void fillImages(){
        try(Stream<Path> paths = Files.walk(Paths.get("images"))){
            paths.filter(Files::isRegularFile).forEach(e ->imageUrls.add(e.toString()));
        }
        catch (IOException e){
            System.out.println("Ошибка чтения файлов");
        }
    }
    public static void createPaints(HashMap<String, Paints> paintsMap, ArrayList<String> names){
        fillImages();
        for (int i = 0; i < names.size(); i++){
            try{
                Paints paint = new Paints(imageUrls.get(i), names.get(i));
                paintsMap.put(paint.getName(), paint);
            }
            catch (IOException e){
                System.out.println("Нет файла");
            }
        }
    }
}
