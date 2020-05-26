public class Part2in {

    private int howMany(String stringa, String stringb) {
        if (stringa == null || stringa.isEmpty() || stringb == null || stringb.isEmpty() || stringa.length() > stringb.length())
            return 0;

        int count = 0;
        int index = stringb.indexOf(stringa);
        while (index != -1) {
            count += 1;
            stringb = stringb.substring(index + stringa.length());
            index = stringb.indexOf(stringa);
        }
        return count;
    }

    public void  testHowMany()
    {
        System.out.println(howMany("AA","ATAAAA"));
        System.out.println(howMany("GAA","ATGAACGAATTGAATC"));
    }
}
