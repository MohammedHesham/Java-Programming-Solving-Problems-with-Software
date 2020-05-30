import edu.duke.FileResource;

public class Main {

    public static void main(String[] args) {
        Birth birth = new Birth();
        FileResource fileResource = new FileResource("/home/hesham/Downloads/us_babynames/us_babynames_by_year/yob" + 1900 + ".csv");
        System.out.println(birth.getAverageRank("Susan","F"));

    }
}
