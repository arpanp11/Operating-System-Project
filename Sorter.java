//to make Sorter suitable for threading, first we need to implement the interface Runnable in the Sorter class
public class Sorter implements Runnable{

    //create private instance variables for Sorter class which can be used to store the data passed to it for sorting, as well a Thread variable which can be sued to create a sorter thread whenever the sorting needs to be started
    private Thread t;
    private String threadName;
    private int[] data;

    //modify the constructor for the Sorter class to include a name for the thread which will now do the sorting. Also store the data being passed to the Sorter into the data instance variable
    public Sorter(int[] a, String threadName){
        System.out.println("Creating thread: "+threadName);
        this.data = a;
        this.threadName = threadName;
    }

    //create a getThread method which can be used to access the private thread variable which will be running the sorting procedure
    public Thread getThread(){
        return t;
    }

    //create a start method which instantiates the thread variable by creating a thread and then calling the thread's own start method
    //the thread's start method invokes the run method of the thread automatically
    public void start() {
        System.out.println("Starting " + this.threadName);
        if (t == null) {
            t = new Thread(this, this.threadName);
            t.start();
        }
    }

    //this is the heart of the thread
    //when a thread is started using the start method, it internally invokes the run method and runs whatever code is present in this method
    //inside run simply call the sort method on the data which was passed to the sorter to make the sorting thread start sorting
    public void run() {
        System.out.println("Running thread: "+this.threadName);
        sort(data);
    }

    //this is the sort method from the previous program, without any modifications
    private void sort(int[] a){
        for(int i = 0; i < a.length; i++){
            int pos = i;
            int min = a[i];
            for (int j = i + 1; j < a.length; j++)
                if(a[j] < min){
                    min = a[j];
                    pos = j;
                }
            a[pos] = a[i];
            a[i] = min;
        }
    }

}