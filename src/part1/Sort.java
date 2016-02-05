package part1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Represents a base implementation for different sorting algorithms.
 *
 * @author melika barzegaran hosseini
 */
public abstract class Sort
{
    protected int[] values;

    protected void read(String path)
    {
        File file = new File(path);
        byte[] bytes = new byte[(int) file.length()];
        FileInputStream input;
        try
        {
            input = new FileInputStream(file);
            input.read(bytes);
            input.close();

            String[] valuesString = new String(bytes).trim().split("\\s+");
            values = new int[valuesString.length];
            for(int counter = 0; counter < valuesString.length; counter++)
            {
                values[counter] = Integer.parseInt(valuesString[counter]);
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println("error: file '" + path + "' was not found.");
            System.exit(1);
        }
        catch(IOException e)
        {
            System.err.println("error: an IO error occurred during reading from the file '" + path + "'.");
            System.exit(1);
        }
    }

    protected void write(String path)
    {
        File file = new File(path);

        try
        {
            Files.createDirectories(Paths.get(path).getParent());
            Files.createFile(Paths.get(path));

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(int value : values)
            {
                writer.write(value + " ");
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

    protected abstract void sort();
}