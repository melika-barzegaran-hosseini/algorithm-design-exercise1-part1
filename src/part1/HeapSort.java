package part1;

/**
 * A heap sort implementation which sorts integer numbers in O(nlogn) in average case.
 *
 * @author melika barzegaran hosseini
 */
public class HeapSort extends Sort
{
    @Override
    protected void sort()
    {
        heapify();

        int end = values.length - 1;
        while(end > 0)
        {
            swap(0, end);
            siftDown(0, end - 1);
            end--;
        }
    }

    private void heapify()
    {
        int start = (values.length - 2) / 2;

        while(start >= 0)
        {
            siftDown(start, values.length - 1);
            start--;
        }
    }

    private void siftDown(int start, int end)
    {
        int root = start;

        while((root * 2 + 1) <= end)
        {
            int child = root * 2 + 1;

            if(child + 1 <= end && values[child] < values[child + 1])
            {
                child = child + 1;
            }
            if(values[root] < values[child])
            {
                swap(root, child);
                root = child;
            }
            else
            {
                return;
            }
        }
    }

    private void swap(int i, int j)
    {
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }
}