
public class Part3 {


    /**
     * twoOccurrences that has two String parameters named stringa and stringb.
     * This method returns true if stringa appears at least twice in stringb
     *
     * @param stringa the sub-string to search for
     * @param stringb the main string
     * @return boolean representing if stringa exists in stringb two times
     */
    private boolean twoOccurrences(String stringa, String stringb) {
        if (stringa == null || stringa.isEmpty() || stringb == null || stringb.isEmpty() || (stringa.length() > stringb.length()))
            return false;
        int pos = 0;
        int count = 0;
        while (count < 2) {
            pos = stringb.indexOf(stringa, pos);
            if (pos == -1) {
                break;
            }
            count++;
            pos += stringa.length();
        }
        return count == 2;

    }

    /**
     * lastPart that has two String parameters named stringa and stringb.
     * This method finds the first occurrence of stringa in stringb,
     * and returns the part of stringb that follows stringa.
     *
     * @param stringa the sub-string to search for
     * @param stringb the main string
     * @return result string (substring after stringa in stringb)
     */

    private String lastPart(String stringa, String stringb) {
        if (stringa == null || stringa.isEmpty() || stringb == null || stringb.isEmpty() || (stringa.length() > stringb.length()))
            return stringb;

        int pos = stringb.indexOf(stringa);
        if (pos == -1) {
            return stringb;
        }

        return stringb.substring(pos + stringa.length());
    }


    public void testing() {
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));

        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
    }


}
