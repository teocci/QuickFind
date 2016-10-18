import java.util.Random;

/**
 * Created by teocci on 10/18/16.
 * Generate random integers in a certain range.
 *
 */
public final class RandomRange
{
    public static final void main(String... aArgs){
        log("Generating random integers in the range 1..10.");

        int START = 1;
        int END = 10;

        /*for (int idx = 1; idx <= 10; ++idx){
            showRandomInteger(START, END);
        }*/

        log("Done.");
    }

    public static int randomInt(int start, int end){
        if (start > end) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        Random random = new Random();
        //get the range, casting to long to avoid overflow problems
        long range = (long)end - (long)start + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * random.nextDouble());
        int randomNumber =  (int)(fraction + start);
        return randomNumber;
        //log("Generated : " + randomNumber);
    }

    private static void log(String aMessage){
        System.out.println(aMessage);
    }
}
