package a3;
import java.io.*;


public class Main {
    /**
     * Replace "-1" by the time you spent on A3 in hours.
     * Example: for 3 hours 15 minutes, use 3.25
     * Example: for 4 hours 30 minutes, use 4.50
     * Example: for 5 hours, use 5 or 5.0
     */
    public static double timeSpent = 9;

    public static LList<LList<String>> csvToList(String csv_name) {
        LList<LList<String>> ret = new SLinkedList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(csv_name));
            String line = br.readLine();
            while (line != null){
                String[] data = line.split(",");
                LList<String> list = new SLinkedList<>();
                for (int i = 0; i < data.length; i++) list.append(data[i]);
                ret.append(list);
                line = br.readLine();
            }
        }
        catch (IOException e){ System.out.println("The file name was not found."); }
        return ret;
    }

    //TA Eugene helped
    public static LList<LList<String>> join(LList<LList<String>> table1,
                                            LList<LList<String>> table2) {
        LList<LList<String>> ret = new SLinkedList<>();
        int size1 = table1.size();
        int size2 = table2.size();
        boolean sameHead = false;
        boolean hasDup = false;
        for (int i = 0; i < size1; i++) {
            LList<String> curr1 = table1.get(i);
            String c1 = curr1.head();
            for (int j = 0; j < size2; j++) {
                LList<String> curr2 = table2.get(j);
                if (c1.equals(curr2.head())) {
                    sameHead = true;
                    LList<String> list1 = new SLinkedList<>();
                    for (int k = 0; k < curr1.size(); k++) list1.append(curr1.get(k));
                    for (int l = 0; l < curr2.size(); l++) {
                        if (hasDup == false) hasDup = true;
                        else list1.append(curr2.get(l));
                    }
                    hasDup = false;
                    ret.append(list1);
                }
            }
            LList<String> list2 = new SLinkedList<>();
            if (sameHead == false) {
                for (String z: curr1) list2.append(z);
                ret.append(list2);
            }
            sameHead = false;
        }
        return ret;
    }


    /** Effect: Print a usage message to standard error. */
    public static void usage() {
        System.err.println("Usage: a3.Main <file1.csv> <file2.csv>");
    }

    private static String stringFormat(LList<String> list){
        StringBuilder res = new StringBuilder("");
        String original = list.toString();
        res.append(original);
        res.delete(0,1);
        res.delete(original.length()-2, original.length()-1);

        return res + "";
    }

    public static void main(String[] args) {
        
        LList<LList<String>> table1 = csvToList(args[0]);
        LList<LList<String>> table2 = csvToList(args[1]);
        LList<LList<String>> jointTable = join(table1, table2);
        for (int i = 0; i < jointTable.size(); i++){
            System.out.println(stringFormat(jointTable.get(i)));
        }

    }

}
