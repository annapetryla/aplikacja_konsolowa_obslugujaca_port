package pl;

import java.util.ArrayList;

public class IdGenerator {

    protected int firstId ;

    ArrayList<Integer> arrayList = new ArrayList<>();

    protected IdGenerator(int id) {

        this.firstId = id;
    }

    protected int generateId() {
        arrayList.add(firstId);
        int tmp = firstId++;
        return tmp;
    }
}
