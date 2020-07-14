package netty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuantongqin
 * desc:
 * 2020-07-10
 */
public class Ta extends Tb implements Tc<Te>,Td{


    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add("aa");
        a.add("bb");
        a.add("cc");

        for (String s : a) {
            System.out.println(s);
            if(s.equals("bb")){

                System.out.println("bb就别进");
                continue;
            }
            System.out.println("hhhh");
        }


        System.out.println("aa");

    }
}
