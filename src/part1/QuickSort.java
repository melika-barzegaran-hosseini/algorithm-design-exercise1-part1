package part1;

public class QuickSort extends Sort
{
    @Override
    protected void sort()
    {
        quickSort(0, values.length - 1);
    }

    private void quickSort(int low, int high)
    {
        int i = low;
        int j = high;
        int pivot = values[low + (high - low) / 2];

        while (i <= j)
        {
            while (values[i] < pivot)
            {
                i++;
            }

            while (values[j] > pivot)
            {
                j--;
            }

            if (i <= j)
            {
                swap(i, j);
                i++;
                j--;
            }
        }

        if (low < j)
        {
            quickSort(low, j);
        }

        if (i < high)
        {
            quickSort(i, high);
        }
    }

    private void swap(int i, int j)
    {
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }
}