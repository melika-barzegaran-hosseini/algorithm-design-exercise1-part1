package part1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;

public class SortTest
{
    private static final String TESTCASE_FOLDER = "testcases";
    private static String OUTPUT_FOLDER;

    private static String getTime()
    {
        final String DATE_PATTERN = "EEE_dd-MMM-yyyy_HH-mm-ss_z";
        return new SimpleDateFormat(DATE_PATTERN).format(Calendar.getInstance().getTime());
    }

    private static File[] getTestCases()
    {
        File folder = new File(TESTCASE_FOLDER);

        File[] files = folder.listFiles();

        if(files == null)
        {
            System.err.println("error: testcases folder was not found.");
            System.exit(1);
        }

        for(File file : files)
        {
            try
            {
                Integer.parseInt(file.getName().replace(".txt", ""));
                if(!file.isFile())
                {
                    System.err.println("error: test case file '" + file.getName() + "' is not a file.");
                    System.exit(1);
                }
            }
            catch(NumberFormatException e)
            {
                System.err.println("error: testcase file '" + file.getName() + "' is not properly named. the name of " +
                        "the testcase file should represent the number of numbers contained to be sorted.");
                System.exit(1);
            }
        }

        Arrays.sort(files, new Comparator<File>()
        {
            @Override
            public int compare(File file1, File file2)
            {
                int file1number = Integer.parseInt(file1.getName().replace(".txt", ""));
                int file2number = Integer.parseInt(file2.getName().replace(".txt", ""));

                if(file1number > file2number)
                {
                    return 1;
                }
                if(file1number == file2number)
                {
                    return 0;
                }
                else
                {
                    return -1;
                }
            }
        });

        return files;
    }

    public static ArrayList<Long> test(String name, Sort sort)
    {
        System.out.println("=========================================================================================");
        System.out.println("testing " + name + "...");
        File[] files = SortTest.getTestCases();

        ArrayList<Long> times = new ArrayList<>();
        for(int counter = 0; counter < files.length; counter++)
        {
            sort.read(files[counter].getPath());

            long startTime = System.currentTimeMillis();
            sort.sort();
            long endTime = System.currentTimeMillis();
            times.add(endTime - startTime);

            sort.write(OUTPUT_FOLDER + File.separator + name + File.separator + files[counter].getName().replace("" +
                    ".txt", "").concat("-sorted.txt"));

            System.out.println("testcase with " + files[counter].getName().replace(".txt", "") + " input took " +
                    times.get(counter) + " milliseconds to sort.");
        }

        return times;
    }

    public static void test(String[] names, ArrayList<ArrayList<Long>> sortTimes)
    {
        String path = OUTPUT_FOLDER + File.separator + "results.txt";
        File file = new File(path);

        try
        {
            Files.createFile(Paths.get(path));
            PrintWriter writer = new PrintWriter(new FileWriter(file));

            writer.write("               ");
            File[] files = SortTest.getTestCases();
            for(File currentFile : files)
            {
                writer.format("%-6d ", Integer.parseInt(currentFile.getName().replace(".txt", "")));
            }
            writer.write("\n");

            for(ArrayList<Long> times : sortTimes)
            {
                writer.format("%-15s", names[sortTimes.indexOf(times)]);
                for(Long time : times)
                {
                    writer.format("%-6d ", time);
                }
                writer.write("\n");
            }
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            System.err.println("error: file '" + path + "' could not be created/found.");
            System.exit(1);
        }
    }

    public static void main(String args[])
    {
        OUTPUT_FOLDER = "output_" + getTime();

        String[] names = {"insertion sort", "quick sort", "merge sort", "heap sort"};

        ArrayList<ArrayList<Long>> sortTimes = new ArrayList<>();
        sortTimes.add(SortTest.test("insertion-sort", new InsertionSort()));
        sortTimes.add(SortTest.test("quick-sort", new QuickSort()));
        sortTimes.add(SortTest.test("merge-sort", new MergeSort()));
        sortTimes.add(SortTest.test("heap-sort", new MergeSort()));

        SortTest.test(names, sortTimes);
    }
}