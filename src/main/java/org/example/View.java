package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class View {


    public void start() throws IOException {
        System.out.println("----------------------------");
        System.out.println("МЕНЮ:");
        System.out.println("1 - ввести новую игрушку");
        System.out.println("2 - показать остатки на складах");
        System.out.println("3 - показать список выигранных игрушек");
        System.out.println("4 - запустить случайный выбор игрушки в подарок ");
        System.out.println("5 - Выйти");
        System.out.println("Выберите пункт меню: ");
        Scanner in = new Scanner(System.in);

        int num = in.nextInt();
//        Game game = null;
        switch (num) {
            case 1:
                Toy toy = new Toy();
                toy.createToy();
                toy.deleteEmptyString(toy.getItem());
                toy.show();
                break;
            case 2:
                Toy toys = new Toy();
                try {
                    toys.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:
                Toy toysss = new Toy();
                try {
                    toysss.showWinList();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 4:
                Toy toyss = new Toy();

                try {
                    toyss.randomToy();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 5:
                break;
            default:
                System.out.println("что-то вы не то нажали))");
                start();
        }
    }
}


