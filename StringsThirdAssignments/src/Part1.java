import edu.duke.FileResource;
import edu.duke.StorageResource;

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
                System.out.println(dna);
                int startIndex = dna.indexOf(gene) + gene.length();
                dna = dna.substring(startIndex);
            }

        }

    }

    private StorageResource getAllGenes(String dna) {
        StorageResource resource = new StorageResource();
        while (true) {
            String gene = findGene(dna);
            if (gene.isEmpty()) {
                break;
            } else {
                resource.add(gene);
                int startIndex = dna.indexOf(gene) + gene.length();
                dna = dna.substring(startIndex);
            }

        }
        return resource;
    }

    public double cgRatio(String dna) {
        int cgCount = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'C' ||dna.charAt(i) == 'G')
                cgCount++;
        }
        return ((float) cgCount) / dna.length();
    }

    private int countCTG(String dna) {
        int CTGCount = 0;
        int index = dna.indexOf("CTG");
        while (index != -1) {
            CTGCount++;
            index = dna.indexOf("CTG", index + 3);
        }
        return CTGCount;
    }

    private void processGenes(StorageResource sr) {
        int count = 0;
        int cgCount = 0;
        int longestGeneLength = 0;
        for (String gene : sr.data()) {
            if (gene.length() > 60) {
                count++;
                System.out.println(gene);
            }
            if (cgRatio(gene) > 0.35) {
                cgCount++;
                System.out.println(">0.35 cg" + gene);
            }

            if (longestGeneLength < gene.length()) {
                longestGeneLength = gene.length();
            }

        }
        System.out.println("Genes count:" + sr.size());
        System.out.println("number of Strings in sr that are longer than 60 characters:" + count);
        System.out.println("number of strings in sr whose C-G-ratio is higher than 0.35:" + cgCount);
        System.out.println("Longest Gene length:" + longestGeneLength);

    }

    public void testProcessGenes() {
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString().toUpperCase();
        StorageResource allGenes = getAllGenes(dna);
        processGenes(allGenes);
        System.out.println(countCTG(dna));

    }

    public String mystery(String dna) {
        int pos = dna.indexOf("T");
        int count = 0;
        int startPos = 0;
        String newDna = "";
        if (pos == -1) {
            return dna;
        }
        while (count < 3) {
            count += 1;
            newDna = newDna + dna.substring(startPos, pos);
            startPos = pos + 1;
            pos = dna.indexOf("T", startPos);
            if (pos == -1) {
                break;
            }
        }
        newDna = newDna + dna.substring(startPos);
        return newDna;
    }
}
