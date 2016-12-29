//to make Merger suitable for threading, first we need to implement the interface Runnable in the Merger class
public class Merger implements Runnable{

    //create private instance variables for Merger class which can be used to store the arrays passed to it for merging, the merged array, as well a Thread variable which can be sued to create a merger thread whenever the merging needs to be started
    private Thread t;
    private String threadName;
    private int[] data1;
    private int[] data2;
    private int[] finaldata;

    //modify the constructor for the Merger class to include a name for the thread which will now do the merging. Also store the data being passed to the Merger into the data1,data2 and finaldata instance variables
    public Merger(int[]a, int[] b, int[] c, String threadName){
        System.out.println("Creating thread: "+threadName);
        this.data1 = a;
        this.data2 = b;
        this.finaldata = c;
        this.threadName = threadName;
    }

    //create a getThread method which can be used to access the private thread variable which will be running the merging procedure
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
    //inside run simply call the merge method on the data which was passed to the merger to make the merger thread start merging
    public void run() {
        System.out.println("Running thread: "+this.threadName);
        merge(data1,data2,finaldata);
    }

    //this is the merge method from the previous program, without any modifications
    private void merge(int[] a, int[] b, int[] c){
        int index = 0, i = 0, j = 0;
        while(i < a.length && j < b.length)
            if(a[i] < b[j])
                c[index++] = a[i++];
            else
                c[index++] = b[j++];
        if(i < a.length)
            for(int k = i; i < a.length; i++)
                c[index++] = a[i];
        if(j < b.length)
            for(int k = j; j < b.length; j++)
                c[index++] = b[j];
    }
}