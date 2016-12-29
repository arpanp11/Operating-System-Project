import java.util.Random;

public class Main{
    public static void main(String[] args) {
        Random rand = new Random();
        int size = rand.nextInt(50) + 1;
        int a[] = new int[size];
        size = rand.nextInt(50) + 1;
        int b[] = new int[size];
        for(int i = 0; i < a.length; i++)
            a[i] = rand.nextInt(999);
        for(int i = 0; i < b.length; i++)
            b[i] = rand.nextInt(999);

        //create two sorters passing them arrays a and b which need to be sorted
        Sorter sorta = new Sorter(a, "sorta");
        Sorter sortb = new Sorter(b, "sortb");
        //also declare an array c to store the merged result and create a merger passing it arrays a and b to be merged and c to store the results
        int[] c = new int[a.length + b.length];
        Merger merge = new Merger(a,b,c, "merge");

        //make threads a and b start sorting by calling the start method on both
        sorta.start();
        sortb.start();

        //use join method on threads a and b to make the main thread wait till threads a and b have finished their execution before executing any further steps
        //if join is not called, the main thread may keep executing more code and make the merger thread start merging before the sorters have finished their jobs, resulting in wrong output
        //also, handle the interrupted exception as well
        try {
            sorta.getThread().join();
            sortb.getThread().join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        //after the sorter threads have finished their task, now the merger thread needs to be started by calling start method on it
        //again use join method on this thread also to make main thread wait till merger finishes execution
        //if join is not called main thread may keep on going ahead and print out the array c before merger has finished creating it
        //again, handle the interrupted exception here as well
        merge.start();
        try {
            merge.getThread().join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        //finally, after all sorting and merging has been done, print out the array c
        for(int i = 0; i < c.length; i++)
            System.out.print(c[i] + " ");
    }
}
