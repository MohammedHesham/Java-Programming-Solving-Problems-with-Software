public class Part1 {

    private static String findSimpleGene(String dna) {
        if (dna == null)
            return "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        if (stopIndex == -1)
            return "";
        if ((stopIndex - startIndex) % 3 != 0) {
            return "";
        }
        return dna.substring(startIndex, stopIndex + 3);
    }

    private static void testSimpleGene() {
        String gene = "ATGAA";
        System.out.println(findSimpleGene(gene));

        gene = "AAAAATTAA";
        System.out.println(findSimpleGene(gene));

        gene = "ATTATGAATTAA";
        System.out.println(findSimpleGene(gene));

        gene = "ATGAAAATTAA";
        System.out.println(findSimpleGene(gene));

        gene = "ATGAATTAAAAAA";
        System.out.println(findSimpleGene(gene));

        gene = "ATGAATTAA";
        System.out.println(findSimpleGene(gene));
    }

    public static void main(String[] args) {
        testSimpleGene();
    }
}
