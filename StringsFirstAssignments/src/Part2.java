public class Part2 {

    private static String findSimpleGene(String dna,String startCodon,String stopCodon) {
        if (dna == null)
            return "";
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (stopIndex == -1)
            return "";
        if ((stopIndex - startIndex) % 3 != 0) {
            return "";
        }
        return dna.substring(startIndex, stopIndex + 3);
    }

    private static void testSimpleGene() {
        String gene = "ATGAA";
        System.out.println(findSimpleGene(gene,"ATG","TAA"));

        gene = "AAAAATTAA";
        System.out.println(findSimpleGene(gene,"ATG","TAA"));

        gene = "ATTATGAATTAA";
        System.out.println(findSimpleGene(gene,"ATG","TAA"));

        gene = "ATGAAAATTAA";
        System.out.println(findSimpleGene(gene,"ATG","TAA"));

        gene = "ATGAATTAAAAAA";
        System.out.println(findSimpleGene(gene,"ATG","TAA"));

        gene = "ATGAATTAA";
        System.out.println(findSimpleGene(gene,"ATG","TAA"));
    }
}
