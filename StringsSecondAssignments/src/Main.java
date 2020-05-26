public class Main {

    public static void main(String[] args) {
//        Part1 part1=new Part1();
//        part1.testFindStopCodon();
//        part1.testFindGene();

//        Part2in part2in=new Part2in();
//        part2in.testHowMany();
        Main main=new Main();
        main.test();
    }

    public void findAbc(String input){
        int index = input.indexOf("abc");
        while (true){
            if (index == -1 || index >= input.length() - 3){
                break;
            }
            System.out.println(index);
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc",index+3);
            System.out.println("index after updating " + index);
        }
    }

    public void test() {
    findAbc("abcabcabcabca");
    }
}
