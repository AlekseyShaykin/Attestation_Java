package org.example;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;


@NoArgsConstructor
@AllArgsConstructor

public class Toy extends Abstract_Toy {
    public String quantity;
    public String frequency;


    @Override
    public String toString() {
        return "TOY : " +
                "ID= " + ID + "; name: " + name + "; quantity: " + quantity + "; frequency: " + frequency;
    }


    @Override
    public String createItem() {
        Scanner in = new Scanner(System.in);
//        System.out.print("Введите ОДИН параметр по порядку: ID; имя; количество; частота: ");
        String item = String.valueOf(in.nextLine());

        return item;

    }


    @Override
    public Toy createToy() {
        System.out.println(("Введите ID: "));
        this.ID = (this.createItem());
        System.out.println(("Введите наименование игрушки: "));
        this.name = (this.createItem());
        System.out.println(("Введите количество: "));
        this.quantity = (this.createItem());
        System.out.println(("Введите вес: "));
        this.frequency = (this.createItem());
        stock();
        return this;
    }


    @Override
    public void stock() {
        try (FileWriter writer = new FileWriter("notes3.txt", true)) {
            // запись всей строки
            // запись по символам
            writer.append('\n');
            writer.append(ID + ";");
            writer.append(name + ";");
            writer.append(quantity + ";");
            writer.append(frequency + ";");

            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void show() throws IOException {
        // метод хорошо всё показывает/выводит в консоль
        String fileName = "notes3.txt";
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);

        //построчно считываем файл
        scanner.useDelimiter(System.getProperty("line.separator"));
        ArrayList<String> arr = new ArrayList<>();
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
        scanner.close();
        System.out.println("*****************************");

    }

    @Override
    public void showWinList() throws IOException {
        // метод хорошо всё показывает/выводит в консоль
        String fileName = "notes4.txt";
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);

        //построчно считываем файл
        scanner.useDelimiter(System.getProperty("line.separator"));
        ArrayList<String> arr = new ArrayList<>();
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
        scanner.close();
        System.out.println("*****************************");

    }

    @Override
    public ArrayList<String> getItem() throws IOException {                // получаем список из файла note3
        // играюсь, хочу чтбы из файлы проивзолилась запись в список
        List result = new ArrayList<>();
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader("notes3.txt"));

            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return (ArrayList<String>) result;

    }


    @Override
    public void stockNew(String text) {     // метол для записи отдельных игрушек (для покупки) в отдельный файл note4
        // запись в файл СПИСКА
        try (FileWriter writer = new FileWriter("notes4.txt", true)) {      // true и false - можно поиграться
            // запись всей строки
            // запись по символам
            writer.append('\n');
            writer.append((CharSequence) text);

            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Игрушка записана в новый файл notes4.txt, а остатки на складе уменьшились");
    }



    @Override
    public Integer randomToy() throws IOException {             // случайным образом выбираем игрушку из файла note3 и записываем в файл note4

        Toy toy = new Toy();
        int n = (int) Math.floor(Math.random() * toy.getItem().size());           // это номер строки из файла note3
        System.out.println("Выиграла игрушка: " + toy.getItem().get(n));

        String str_first = toy.getItem().get(n);
        String[] substr_first;
        String delimetr_first = ";";
        substr_first = str_first.split(delimetr_first);
        int x = parseInt(substr_first[2]);
        x = 1;
        substr_first[2] = String.valueOf(x);
        str_first = String.join(";", substr_first);
        toy.stockNew(str_first);
        toy.findTodelete(n);                                    // метод уменьшает кол-во на складе
        toy.deleteEmptyString(toy.getItem());
                return n;
            }


    @Override
    public void findTodelete(Integer m) throws IOException {    // поиск игрушки в файле note3 для уменьшения количества
        Toy toy = new Toy();
        toy.getItem();
        ArrayList<String> str = new ArrayList<>(toy.getItem());

        for (int i = 0; i < str.size(); i++) {
            if (i == m) {
//                str.remove(i);
                String str2 = toy.getItem().get(m);
                String[] substr;
                String delimetr = ";";
                substr = str2.split(delimetr);
                int x = (parseInt(substr[2])-1);
                substr[2] = String.valueOf(x);        // перевели из int в String
                String result = String.join(";",substr);       // записали строку без квадратных скобок
                str.set(i, result);

            }
            Writer writer = null;
            try {
                writer = new FileWriter("notes3.txt");
                for (String line : str) {
                    writer.write(line);
//                тут мог бы быть пробел если надо в одну строку
                    writer.write(System.getProperty("line.separator"));
                }
                writer.flush();
            } catch (Exception e) {
                Logger.getLogger(Toy.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException ex) {
                    }
                }
            }
        }


    }

    @Override
    public void deleteEmptyString(ArrayList<String> a) {
        a.removeIf(String::isEmpty);
        Writer writer = null;
        try {
            writer = new FileWriter("notes3.txt");
            for (String line : a) {
                writer.write(line);
//                тут мог бы быть пробел если надо в одну строку
                writer.write(System.getProperty("line.separator"));
            }
            writer.flush();
        } catch (Exception e) {
            Logger.getLogger(Toy.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                }
            }
        }

        }

}

