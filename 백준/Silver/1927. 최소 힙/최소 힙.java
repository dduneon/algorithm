import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Heap heap = new Heap(N);

        for(int i=0; i<N; i++) {
            int input = Integer.parseInt(br.readLine());
            if(input == 0) {
                System.out.println(heap.pop());
            } else {
                heap.push(input);
            }
        }
    }
}

class Heap {
    private final int[] heap;
    private int size;

    public Heap(int maxSize) {
        this.heap = new int[maxSize];
        size = 0;
    }

    public void push(int number) {
        heap[size] = number;
        compareWithParent(size);
        size++;
    }

    public void compareWithParent(int index) {
        if(index == 0) {
            return;
        }
        int parent = (index - 1) / 2;
        if(heap[parent] > heap[index]) {
            swap(index, parent);
            compareWithParent(parent);
        }
    }

    public int pop(){
        if(size == 0) {
            return 0;
        }

        int min = heap[0];
        heap[0] = heap[size-1];
        heap[size-1] = -1;
        size--;
        compareWithChild(0);

        return min;
    }

    public void compareWithChild(int index) {
        int leftChild = index * 2 + 1;
        int rightChild = index * 2 + 2;


        if(leftChild < size && rightChild < size) {
            int minChild = heap[leftChild] < heap[rightChild] ? leftChild : rightChild;

            if(heap[index] > heap[minChild]) {
                swap(index, minChild);
                compareWithChild(minChild);
            }
        } else if(leftChild < size) {
            if(heap[index] > heap[leftChild]) {
                swap(index, leftChild);
                compareWithChild(leftChild);
            }
        }
    }

    public void swap(int index1, int index2) {
        int tmp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = tmp;
    }

}