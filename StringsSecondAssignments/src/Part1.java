public class Part1 {

    private int findStopCodon(String dna, int startIndex, String stopCodon) {
        if (dna == null || dna.isEmpty()) {
            return 0;
        }

        int index = dna.indexOf(stopCodon, startIndex + 3);
        while (index != -1) {
            int diff = index - startIndex;
            if (diff % 3 == 0) {
                return index;
            } else {
                index = dna.indexOf(stopCodon, index + 3);
            }
        }
        return -1;
    }

    public void testFindStopCodon() {
        String dna = "ATGAATTTTAAAATAA";
        System.out.println(findStopCodon(dna, dna.indexOf("ATG"), "TAA"));
    }

    private String findGene(String dna) {
        if (dna == null || dna.isEmpty())
            return "";

        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1)
            return "";

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        int minIndex;
        if (taaIndex == -1 || (tagIndex != -1 && taaIndex > tagIndex)) {
            minIndex = tagIndex;
        } else {
            minIndex = taaIndex;
        }

        if (minIndex == -1 || (tgaIndex != -1 && tgaIndex < minIndex)) {
            minIndex = tgaIndex;
        }

        if (minIndex == -1) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }

    public void testFindGene() {
        String dna = "AATTAAAAGGTA";
        System.out.println(findGene(dna));

        dna = "AATTATGAAAAGGTAG";
        System.out.println(findGene(dna));

        dna = "AATTATGAAATGAAGGTAGATTTAA";
        System.out.println(findGene(dna));

        dna = "AATTATGAAAGGAAGGAAGATTAAA";
        System.out.println(findGene(dna));
    }

    private void printAllGenes(String dna) {
        while (true) {
            String gene = findGene(dna);
            if (gene.isEmpty()) {
                break;
            } else {
                int startIndex = dna.indexOf(gene) + gene.length();
                dna = dna.substring(startIndex);
            }

        }

    }
}
