package com.app.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Mycollection {

    private List mylist;
    private Set myset;
    private Map mymap;

    public Mycollection(List mylist, Set myset, Map mymap) {
        this.mylist = mylist;
        this.myset = myset;
        this.mymap = mymap;
    }

    @Override
    public String toString() {
        return "Mycollection [mylist=" + mylist + ", myset=" + myset + ", mymap=" + mymap + "]";
    }
}
