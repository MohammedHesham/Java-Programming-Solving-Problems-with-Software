import edu.duke.URLResource;

public class Part4 {

    public void printYoutubeLinks() {
        URLResource urlResource = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String word : urlResource.words()) {
            if (word.toLowerCase().contains("www.youtube.com")) {
                int start = word.indexOf("\"");
                if (start == -1) {
                    continue;
                }
                int end = word.indexOf("\"", start + 1);
                System.out.println(word.substring(start + 1, end));
            }
        }

    }
}
