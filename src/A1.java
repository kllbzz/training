import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kirill on 12/28/2014.
 */
public class A1 {


    static int[] minPath(int[] a){

        int prev[] = new int[a.length];
        int maxReached = 0;
        for (int i = 0; i < a.length; i++){
            for(int j = maxReached + 1; j <= i + a[i] && j < a.length; j++) {
                prev[j] = i;
                maxReached = j;
            }
        }

        return getPathByPrev(prev);
    }

    static int[] getPathByPrev(int[] prev){
        ArrayList<Integer> path = new ArrayList<Integer>();
        int p = prev.length - 1;
        do{
            path.add(p);
            p = prev[p];
        }
        while(p > 0);
        path.add(0);
        Collections.reverse(path);
        return path.stream().mapToInt(i->i).toArray();
    }


    static boolean isPath(int[] a){
        if(a.length < 2) return true;
        int maxReached = 0;
        for(int i = 0; i < a.length; i++) {
            if(maxReached < i)
                return false;
            if(maxReached < i + a[i])
                maxReached = i + a[i];
        }

        return true;
    }



    static void test() {
        assert(isPath(new int[]{1,0}) == true);
        assert(isPath(new int[]{0,0}) == false);

        assert(isPath(new int[]{1}) == true);
        assert(isPath(new int[]{}) == true);

        assert(isPath(new int[]{1,0,0}) == false);
        assert(isPath(new int[]{2,0,0}) == true);


        assert(Arrays.equals(minPath(new int[]{1, 0}), new int[]{0, 1}));
        assert(Arrays.equals(minPath(new int[]{2, 0}), new int[]{0, 1}));
        assert(Arrays.equals(minPath(new int[]{3, 0}), new int[]{0, 1}));

        assert(Arrays.equals(minPath(new int[]{1, 1, 0}), new int[]{0, 1, 2}));
        assert(Arrays.equals(minPath(new int[]{1, 2, 0}), new int[]{0, 1, 2}));
        assert(Arrays.equals(minPath(new int[]{2, 0, 0}), new int[]{0, 2}));

        assert(Arrays.equals(minPath(new int[]{1, 1, 1, 0}), new int[]{0, 1, 2, 3}));
        assert(Arrays.equals(minPath(new int[]{1, 2, 0, 0}), new int[]{0, 1, 3}));
        assert(Arrays.equals(minPath(new int[]{2, 0, 1, 0}), new int[]{0, 2, 3}));

        assert(Arrays.equals(minPath(new int[]{2, 1, 1, 0}), new int[]{0, 2, 3}));
    }



    public static void main(String[] a){
        test();
    }
}
