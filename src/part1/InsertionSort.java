package part1;

public class InsertionSort extends Sort
{
    @Override
    protected void sort()
    {
        for(int i = 1; i < values.length; i++)
        {
            int value = values[i];
            int j = i - 1;

            while(j >= 0 && values[j] > value)
            {
                values[j + 1] = values[j];
                j = j - 1;
            }

            values[j + 1] = value;
        }
    }
}