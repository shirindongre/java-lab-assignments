import java.util.*;

public class ArrayListEx {
    public static void main(String[] args) {
        ArrayListEx ex = new ArrayListEx();
        ex.One(args);
        ex.Two(args);
        ex.Three(args);
        ex.Four(args);
        ex.Five(args);
    }

    public void One(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        list.add("Java");
        
        for (int i=0; i<10; i++) {
            list.add(Integer.toString(i));
        }
        System.out.println(list.size());
        System.out.println(list);
    }

    public void Two(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2,3,4,5));
        list.add(6);
        list.add(7);
        System.out.println(list);
    }

    public void Three(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(4);
        list.add(2);
        list.add(3);
        System.out.println(list);
    }

    public void Four(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2,3,4,5));
        list.add(6);
        list.add(7);
        list.remove(Integer.valueOf(3));
        list.remove(0);
        System.out.println(list);
    }

    public void Five(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("woods");
        list.add("are");
        list.add("lovely");
        list.add("dark");
        list.add("and");
        list.add("deep");

        for (String s : list) {
            if (s.charAt(0) == 'd') {
                System.out.println(s);
            }
        }
    }
}