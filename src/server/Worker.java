package server;

import classes.*;
import database2.*;
import stats.Statistics1;
import stats.Statistics2;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;


public class Worker implements Runnable {
    protected Socket clientSocket = null;
    ObjectInputStream sois;
    ObjectOutputStream soos;

    public Worker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            SQLFactory sqlFactory = new SQLFactory();

            sois = new ObjectInputStream(clientSocket.getInputStream());
            soos = new ObjectOutputStream(clientSocket.getOutputStream());
            while (true) {
                String choice = sois.readObject().toString();
                switch (choice) {

                    case "enter": {
                        String user = sois.readObject().toString();
                        String pass = (String) sois.readObject().toString();
                        String url = "jdbc:mysql://localhost/course5" +
                                "?serverTimezone=Europe/Minsk&useSSL=false";
                        Connection myConn = DriverManager.getConnection(url, "root", "root");
                        Statement mySt = myConn.createStatement();
                        ResultSet myRs = mySt.executeQuery("select * from user");
                        int flag = 0;
                        while (myRs.next()) {
                            if (user.equals(myRs.getString("login"))
                                    && pass.equals(myRs.getString("pass"))) {
                                if (myRs.getBoolean("isAdmin")) {
                                    soos.writeObject("Yes1");
                                    System.out.println("Вход под администратором");
                                    flag = 1;
                                } else {
                                    soos.writeObject("Yes0");
                                    System.out.println("Вход под пользователем");
                                    flag = 1;
                                }

                            }

                        }
                        if (flag == 0)
                            soos.writeObject("No");
                        System.out.println("Вход не осуществлен");

                        System.out.println(user + " " + pass);
                        break;
                    }


// ----------------------------------Users----------------------------------------------


                    case "showUsers": {
                        System.out.println("Запрос на отправку таблицы пользователей");

                        SQLUsers s = new SQLUsers();
                        ArrayList<Users> user1 = s.selectUsers();
                        soos.writeObject(user1);
                        break;
                    }
                    case "addUser": {
                        System.out.println("Запрос на добавление пользователей в БД");

                        Users user = (Users) sois.readObject();
                        sqlFactory.getUsers().add(user);
                        break;
                    }
                    case "deleteUser": {
                        System.out.println("Запрос на удаление пользователя из БД");
                        Users user = (Users) sois.readObject();
                        sqlFactory.getUsers().delete(user);
                        break;
                    }
                    case "updUsers": {
                        System.out.println("Запрос на редактирование данных пользователя в БД");

                        int num = (Integer) sois.readObject();
                        String name = (String) sois.readObject();
                        String str = (String) sois.readObject();

                        switch (str){
                            case "имя": {
                                System.out.println("Запрос на редактирование имени пользователя в БД");
                                sqlFactory.getUsers().updateName(name, num);
                                break;}
                            case "логин": {
                                System.out.println("Запрос на редактирование логина в БД");
                                sqlFactory.getUsers().updateLogin(name, num);
                                break;}
                            case "пароль": {
                                System.out.println("Запрос на редактирование пароля пользователя в БД");
                                sqlFactory.getUsers().updatePassword(name, num);break;}
                            case "номер паспорта": {
                                System.out.println("Запрос на редактирование номера паспорта пользователя в БД");

                                sqlFactory.getUsers().updatePassport(name, num);
                                break;}
                            case "фамилия": {
                                System.out.println("Запрос на фамилии пользователя в БД");
                                sqlFactory.getUsers().updateSurname(name, num);
                                break;
                            }
                        }

                        break;
                    }


                    //----------------Materials   -----------------------


                    case "showMaterials": {
                        System.out.println("Запрос показ материалов для украшений");

                        SQLMaterial s = new SQLMaterial();
                        ArrayList<Material> user1 = s.selectMaterials();
                        soos.writeObject(user1);
                        break;
                    }
                    case "addMaterial": {
                        System.out.println("Запрос на добавление материала в БД");

                        Material user = (Material) sois.readObject();
                        sqlFactory.getMaterial().add(user);
                        break;
                    }
                    case "deleteMaterial": {
                        System.out.println("Запрос на удаление материала из БД");

                        Material user = (Material) sois.readObject();
                        sqlFactory.getMaterial().delete(user);
                        break;
                    }
                    case "updateMaterial": {
                        System.out.println("Запрос на редактирование материалв в БД");

                        int num = (Integer) sois.readObject();
                        double str = (Double) sois.readObject();
                        sqlFactory.getMaterial().updateValue(str, num);
                        break;
                    }

                    //----------------Collections   -----------------------

                    case "showCollections": {
                        System.out.println("Запрос на отправку коллекций");

                        SQLCollection s = new SQLCollection();
                        ArrayList<JCollection> user1 = s.selectCollection();
                        soos.writeObject(user1);
                        break;
                    }
                    case "addCollection": {
                        System.out.println("Запрос на добавление коллекции в БД");

                        JCollection user = (JCollection) sois.readObject();
                        sqlFactory.getCollection().add(user);
                        break;
                    }
                    case "deleteCollection": {
                        System.out.println("Запрос на удаление коллекции из БД");

                        JCollection user = (JCollection) sois.readObject();
                        sqlFactory.getCollection().delete(user);
                        break;
                    }
                    case "updateCollection": {

                        System.out.println("Запрос на редактирование коллекций в БД");

                        int num = (int) sois.readObject();
                        double str = (Double) sois.readObject();
                        sqlFactory.getCollection().updateValue(str, num);
                        break;
                    }
                    case "getCollectionNames": {
                        SQLCollection s2 = new SQLCollection();
                        ArrayList<String> list2 = s2.selectCollectionNames();
                        soos.writeObject(list2);
                        break;
                    }

                    //----------------Clients   -----------------------

                    case "showClients": {
                        System.out.println("Запрос на предоставление данных клиентов");

                        SQLClientCard s = new SQLClientCard();
                        ArrayList<ClientCard> user1 = s.selectCollection();
                        soos.writeObject(user1);
                        break;
                    }
                    case "addClient": {
                        System.out.println("Запрос на добавление клиента в БД");

                        ClientCard user = (ClientCard) sois.readObject();
                        sqlFactory.getClientCard().add(user);
                        break;
                    }

                    //-----------------Jewellery-----------------------

                    case "getJewelleryNames": {
                        System.out.println("Запрос на предоставление названий изделий");
                        SQLJewellery s = new SQLJewellery();
                        ArrayList<String> user1 = s.selectNames();
                        soos.writeObject(user1);
                        break;

                    }


                    case "fillToAddJewellery": {
                        System.out.println("Запрос на предоставление типов и материалов изделий для последующего добавления в БД");

                        SQLMaterial s = new SQLMaterial();
                        ArrayList<String> user1 = s.selectMaterialNames("основа");
                        soos.writeObject(user1);
                        ArrayList<String> list = s.selectMaterialNames("вставки");
                        soos.writeObject(list);
                        SQLCollection s2 = new SQLCollection();
                        ArrayList<String> list2 = s2.selectCollectionNames();
                        soos.writeObject(list2);
                        break;

                    }
                    case "calculatePrice": {
                        System.out.println("Запрос на расчет цен");
                        SQLJewellery s = new SQLJewellery();
                        String jcollection = (String) sois.readObject();
                        String material1 = (String) sois.readObject();
                        Double weight1 = Double.parseDouble(sois.readObject().toString());
                        String material2 = (String) sois.readObject();
                        Double weight2 = (Double) sois.readObject();
                        Double newPrice = s.calculate(material1, weight1, material2, weight2, jcollection);
                        soos.writeObject(newPrice);
                        break;
                    }
                    case "addJewellery": {
                        System.out.println("Запрос на добавление нового изделия в БД");

                        Jewellery user = new Jewellery();
                        user = (Jewellery) sois.readObject();

                        soos.writeObject(sqlFactory.getJewellery().add(user));
                        break;
                    }
                    case "showJewellery": {
                        System.out.println("Запрос на просмотр украшений");
                        SQLJewellery s = new SQLJewellery();
                        ArrayList<Jewellery> user1 = s.selectAll();
                        soos.writeObject(user1);
                        break;

                    }
                    case "deleteJewellery": {
                        System.out.println("Запрос на удаление ювелирного изделия");
                        Jewellery jewellery = (Jewellery) sois.readObject();
                        sqlFactory.getJewellery().delete(jewellery);
                        break;
                    }
                    //----------------Purchase------------
                    case "checkCard": {
                        System.out.println("Запрос на проверку существования карты");
                        String telNumber = (String) sois.readObject();
                        SQLPurchase s = new SQLPurchase();
                        ArrayList<String> clientCreds = s.checkClientTel(telNumber);
                        if (clientCreds.size() == 0) {
                            clientCreds.add("0");
                            clientCreds.add("0");
                        }
                        soos.writeObject(clientCreds);
                        break;

                    }
                    case "setPurchase": {
                        SQLPurchase s = new SQLPurchase();
                        ArrayList<String> names = (ArrayList<String>) sois.readObject();
                        String idclient = (String) sois.readObject();
                        s.setPurchases(names, idclient);
                        break;
                    }
                    case "getPrice": {
                        System.out.println("Запрос на показ цен  изделия");
                        SQLPurchase s = new SQLPurchase();
                        String name = (String) sois.readObject();
                        soos.writeObject(s.selectPrice(name));
                        break;

                    }

                    //------------------ Statistics ------------------
                    case "showAllStatistics": {
                        System.out.println("Запрос на просмотр расширенной статистики");
                        SQLPurchase s = new SQLPurchase();
                        soos.writeObject(s.showItemsNumber());
                        soos.writeObject(s.showOrdersNumber());
                        soos.writeObject(s.itemsPopularity());
                        break;

                    }
                    case "showPopularity": {
                        System.out.println("Запрос на просмотр популярности");
                        SQLPurchase s = new SQLPurchase();
                        soos.writeObject(s.itemsPopularity());
                        break;
                    }

                    //--------------------------Discounts----------------

                    case "showDiscRate": {
                        System.out.println("Запрос на просмотр размеров скидок");

                        SQLDiscount s = new SQLDiscount();
                        ArrayList<Discount> user1 = s.selectDiscounts();
                        soos.writeObject(user1);
                        break;
                    }

                    case "updDiscRate": {

                        System.out.println("Запрос на редактирование размеров скидок в БД");
                        int num = (int) sois.readObject();
                        int str = (int) sois.readObject();
                        sqlFactory.getDiscount().updateValue(str, num);
                        break;
                    }

                    //--------Statistics--------
                    case "Statistics1": {
                        System.out.println("Запрос на статистику 1");
                        SQLStatistics1 s = new SQLStatistics1();
                        ArrayList<Statistics1> user1 = s.selectStatistics1();
                        double avg=0;
                        int k=0;
                        for (int i=0; i<user1.size(); i++) {
                            avg += user1.get(i).getSum();
                            k++;
                        }
                        soos.writeObject(user1);
                        soos.writeObject(avg/k);
                        avg=0;
                        k=0;
                        for (int i=0; i<user1.size(); i++) {
                            avg += user1.get(i).getJewNumber();
                            k++;
                        }
                        soos.writeObject(avg/k);

                        break;
                    }
                    case "Statistics2": {

                        System.out.println("Запрос на статистику 2");
                        SQLStatistics2 s = new SQLStatistics2();
                        ArrayList<Statistics2> user1 = s.selectStatistics2();
                        soos.writeObject(user1);
                        break;

                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            System.out.println("Клиент завершил работу");
        } catch (ClassNotFoundException e) {
            System.out.println("Клиент завершил работу");
        }
    }
}
