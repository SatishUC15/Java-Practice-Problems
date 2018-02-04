package DynamicProgramming;

/**
 Imagine you are trying to cross a stream full of rocks. Write an algorithm to determine if you can make it to the other
 side with the following assumptions:
 1) A rock is represented as a 1, water is represented as 0, so a stream of rocks could be represented as [1,1,1,0,0,1,0,1]
 2) With each jump to a rock, the next maximum distance you can jump is equal to the previous jump + 1.
 So starting out, you can only jump from index 0 to index 1 (if there's a rock at index 1).
 Then from index 1, you can jump to index 2 or 3 (if there's a rock). Then from index 2, you can jump to 3,4, or 5, etc.
**/
public class StreamCrossingProblem {
    private static final int ROCK = 1;
    private int[] stream;
    private int[] failureCache;

    public StreamCrossingProblem(int[] stream) {
        this.stream = stream;
        this.failureCache = new int[stream.length];
    }

    private boolean isValidStep(int position) {
        return position < stream.length && stream[position] == ROCK;
    }

    public boolean canYouMakeIt(int position, int prevJump) {
        if(position == stream.length-1) return true;
        if(failureCache[position] > 0 && failureCache[position] >= prevJump) {
            return false;
        }
        for(int step = 1; step <= prevJump + 1; step++) {
            if(isValidStep(position + step)) {
                if(canYouMakeIt(position + step, step)) {
                    return true;
                }
            }
        }
        if(failureCache[position] < prevJump){
            failureCache[position] = prevJump;
        }
        return false;
    }

    public static void main(String[] args) {
        StreamCrossingProblem obj = new StreamCrossingProblem(new int[]{1,1,0,1,1,0,1,1,0,0,1,1,1});
        System.out.println(obj.canYouMakeIt(0, 0)?"Yes":"No");
    }

}
