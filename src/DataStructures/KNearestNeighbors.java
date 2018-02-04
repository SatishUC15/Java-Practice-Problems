package DataStructures;

import java.util.*;

/**
 * Program to identify the K nearest neighbors of a given 3-dimensional data point, from a list of 3-dimensional data points.
 * Assume the list will be updated frequently.
 * Data Structure used: Priority Queue
 * Time complexity: O(nlogn)
 * Space complexity: O(n)
 */
public class KNearestNeighbors {

    private PriorityQueue<DataPt> pq;
    private DataPt ref;

    /**
     * Constructor
     * @param dataPts 2-d array containing a list of data points
     * @param ref reference point w.r.t which the neighbors are identified
     */
    public KNearestNeighbors(int[][] dataPts, int[] ref) {
        this.pq = new PriorityQueue<>(dataPts.length);
        this.ref = new DataPt(ref[0], ref[1], ref[2], null);
        this.populateQueue(dataPts);
    }

    /**
     * Populates the priority queue
     * @param dataPts
     */
    private void populateQueue(int[][] dataPts) {
        if (dataPts == null || dataPts.length == 0) return;
        for(int i=0; i<dataPts.length; i++) {
            DataPt datapoint = new DataPt(dataPts[i][0], dataPts[i][1], dataPts[i][2], ref);
            pq.offer(datapoint);
        }

    }

    /**
     * Add data points to the list one at a time
     * @param dataPt
     */
    public void addDataPoint(int[] dataPt) {
        if (dataPt.length != 3) return;
        DataPt datapoint = new DataPt(dataPt[0], dataPt[1], dataPt[2], ref);
        pq.offer(datapoint);
    }

    /**
     * Returns the K Nearest Neighbors, in the increasing order of distance
     * @param k
     * @return
     */
    public List<DataPt> getKNearestNeighbors(int k) {
        List<DataPt> result = new ArrayList<>();
        if (k > pq.size()) return result;
        for(int idx = 0; idx < k; idx++) {
            result.add(pq.poll());
        }
        return result;
    }

    public static void main(String[] args) {
        // Setting the reference point as origin for ease of validation
        int[] ref = new int[]{0,0,0};
        int[][] dataPts = new int[][]{{1,0,1}, {1,1,1}, {0,1,1}, {2,3,4}, {1,2,3}};
        KNearestNeighbors obj = new KNearestNeighbors(dataPts, ref);
        List<DataPt> result = obj.getKNearestNeighbors(3);
        Iterator<DataPt> itr = result.iterator();
        while(itr.hasNext()) {
            itr.next().display();
            System.out.println("");
        }

    }


    /**
     *  Inner class to represent the 3-d data points
     */
    class DataPt implements Comparable{
        private int x, y, z;
        private double distance = 0;

        /**
         * Computes the Euclidean distance w.r.t ref
         * @param ref
         */
        private void computeDistance(DataPt ref) {
            if(ref != null) {
                this.distance = Math.sqrt((x - ref.x)*(x - ref.x) + (y-ref.y)*(y-ref.y) + (z-ref.z)*(z-ref.z));
            }
        }

        public DataPt(int x, int y, int z, DataPt ref) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.computeDistance(ref);
        }

        public int compareTo(Object o) {
            DataPt datapt2 = (DataPt)o;
            if(this.distance == datapt2.distance) return 0;
            return (this.distance > datapt2.distance) ? 1 : -1;
        }

        public void display() {
            System.out.print(x + " " + y + " " + z);
        }

    }
}
