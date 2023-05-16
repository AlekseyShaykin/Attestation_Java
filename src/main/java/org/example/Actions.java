package org.example;

import java.io.IOException;
import java.util.ArrayList;

public interface Actions {

    public Toy createToy();

    public String createItem();

    public void stock();

    public void show() throws IOException;
    public void showWinList() throws IOException;

    public ArrayList<String > getItem() throws IOException;

    public void stockNew(String text);

    public Integer randomToy() throws IOException;

    public void findTodelete(Integer m) throws IOException;

    public void deleteEmptyString(ArrayList<String> a);










}
