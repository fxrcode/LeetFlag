package basic;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Test for IO
 * 1. How to read from Resources directory?
 */
public class Tio {

    @Test
    public void test1() {
        try {
            List<List<Integer>> ll = readLint793("test1.txt");
            Integer[][] ans = new Integer[ll.size()][];
            for (int i = 0; i < ll.size(); i++) {
                List<Integer> row = ll.get(i);
                ans[i] = row.toArray(new Integer[row.size()]);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * https://www.baeldung.com/reading-file-in-java#2-reading-a-large-file
     * Somehow, classLoader getResources or Google's Resources.getResource is not working, so I have to give full relative path
     * @param filename
     * @return
     * @throws IOException
     */
    public static List<List<Integer>> readLint793(String filename) throws IOException {
        String file = "src/test/resources/" + filename;

        Path path = Paths.get(file);

        BufferedReader reader = Files.newBufferedReader(path);
        String line = reader.readLine();
        // System.out.println(line);

        List<List<Integer>> ans = new ArrayList<>();

        int left = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '[') {
                left = i;
            } else if (line.charAt(i) == ']') {
                String numStr = line.substring(left + 1, i);
                ans.add(convert(numStr));
            }
        }

        return ans;

        // String filename = "test1.txt";
        // URL url = Resources.getResource("/test1.txt");
        // try {
        // System.out.println( Resources.toString(url, Charsets.UTF_8));
        // } catch (IOException ex) {
        // throw new RuntimeException(ex);
        // }
    }

    private static List<Integer> convert(String s) {
        List<String> ss = Arrays.asList(s.split(","));
        List<Integer> ret = new ArrayList<>();
        for (String n : ss) {
            ret.add(Integer.parseInt(n));
        }
        return ret;
    }
}
