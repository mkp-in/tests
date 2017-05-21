package in.mkp.sandbox.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 *
 * REF: http://homes.cs.washington.edu/~djg/teachingMaterials/spac/grossmanSPAC_forkJoinFramework.html#useful
 *
 * Created by mkumar on 4/12/17.
 */
public class Sum extends RecursiveTask<Long> {
    static final int SEQUENTIAL_THRESHOLD = 1500000;

    int low;
    int high;
    int[] array;

    Sum(int[] arr, int lo, int hi) {
        array = arr;
        low   = lo;
        high  = hi;
    }

    protected Long compute() {
        if(high - low <= SEQUENTIAL_THRESHOLD) {
            long sum = 0;
            for(int i=low; i < high; ++i)
                sum += array[i];
            return sum;
        } else {
            /**
             * It might seem more natural to call fork twice for the two subproblems and then call join twice.
             * This is naturally a little less efficient than just calling compute for no benefit since you are
             * creating more parallel tasks than is helpful. But it turns out to be a lot less efficient,
             * for reasons that are specific to the current implementation of the library and related to the
             * overhead of creating tasks that do very little work themselves.
             */
            int mid = low + (high - low) / 2;
            Sum left  = new Sum(array, low, mid);
            Sum right = new Sum(array, mid, high);
            left.fork();
            long rightAns = right.compute();
            long leftAns  = left.join();
            return leftAns + rightAns;
        }
    }

    static long sumArrayParallel(int[] array) {
        return ForkJoinPool.commonPool().invoke(new Sum(array,0,array.length));
    }

    static long sumArraySequence(int[] array) {
        long sum=0;
        for (int i=0; i < array.length; ++i) {
            sum+=array[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        final int size = 200000000;
        int[] a = new int[size];

        for (int i=0; i < size; ++i) {
            a[i]=i+1;
        }

        long start = System.currentTimeMillis();
        long sum = Sum.sumArrayParallel(a);
        long end = System.currentTimeMillis();
        System.out.println("SUM (PARALLEL) ="+sum+" computed in "+(end-start)+" ms.");

        start = System.currentTimeMillis();
        sum = Sum.sumArraySequence(a);
        end = System.currentTimeMillis();
        System.out.println("SUM (SEQUENCE) ="+sum+" computed in "+(end-start)+" ms.");
    }
}

