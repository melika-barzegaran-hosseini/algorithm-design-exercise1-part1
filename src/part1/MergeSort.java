package part1;

/**
 * A merge sort implementation which sorts integer numbers in O(nlogn) in average case.
 *
 * @author melika barzegaran hosseini
 */
public class MergeSort extends Sort
{
    private int[] temp;

    @Override
    protected void sort()
    {
        temp = new int[values.length];
        mergeSort(0, values.length - 1);
    }

    private void mergeSort(int low, int high)
    {
        if(low < high)
        {
            int middle = low + (high - low) / 2;
            mergeSort(low, middle);
            mergeSort(middle + 1, high);
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high)
    {
        for(int counter = low; counter <= high; counter++)
        {
            temp[counter] = values[counter];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        while(i <= middle && j <= high)
        {
            if(temp[i] <= temp[j])
            {
                values[k] = temp[i];
                i++;
            }
            else
            {
                values[k] = temp[j];
                j++;
            }
            k++;
        }

        while(i <= middle)
        {
            values[k] = temp[i];
            k++;
            i++;
        }
    }
}