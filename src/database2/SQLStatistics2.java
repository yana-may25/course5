package database2;


import stats.Statistics2;

import java.util.ArrayList;

public class SQLStatistics2 implements IStatistics2 {

    private ConnectionDB dbConnection;
    private static SQLStatistics2 instance;
    public SQLStatistics2() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLStatistics2 getInstance() {
        if (instance == null) {
            instance = new SQLStatistics2();
        }
        return instance;
    }

    public ArrayList<Statistics2> selectStatistics2() {
        String str = "select count(idorder)\n" +
                "from purchase\n" +
                "where idorder in( select idclient\n" +
                "from purchase\n" +
                "inner join jewellery on purchase.idjewellery=jewellery.idjewellery\n" +
                "group by purchase.idclient\n" +
                "having count(purchase.idjewellery)=1\n" +
                "order by  count(purchase.idjewellery) DESC )";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Statistics2> jewName = new ArrayList<>();

        for (String[] items : result) {
            Statistics2 jew = new Statistics2();
            jew.setClientNum(Integer.parseInt(items[0]));
            jew.setOrderNum("1");
            jewName.add(jew);
        }

        String str2 = "select count(idclient)\n" +
                "from purchase\n" +
                "where idorder in( select idorder\n" +
                "from purchase\n" +
                "inner join jewellery on purchase.idjewellery=jewellery.idjewellery\n" +
                "group by purchase.idclient\n" +
                "having count(purchase.idjewellery)=3 or count(purchase.idjewellery)=2\n" +
                "order by  count(purchase.idjewellery) DESC )";
        result.clear();
        result = dbConnection.getArrayResult(str2);
        for (String[] items : result) {
            Statistics2 jew = new Statistics2();
            jew.setClientNum(Integer.parseInt(items[0]));
            jew.setOrderNum("2-3");
            jewName.add(jew);
        }
        String str3 = "select count(idclient)\n" +
                "from purchase\n" +
                "where idorder in( select idorder\n" +
                "from purchase\n" +
                "inner join jewellery on purchase.idjewellery=jewellery.idjewellery\n" +
                "group by purchase.idclient\n" +
                "having count(purchase.idjewellery)>=8\n" +
                "order by  count(purchase.idjewellery) DESC )";
        result.clear();
        result = dbConnection.getArrayResult(str3);
        for (String[] items : result) {
            Statistics2 jew = new Statistics2();
            jew.setClientNum(Integer.parseInt(items[0]));
            jew.setOrderNum("4 и более");
            jewName.add(jew);
        }
        return jewName;
    }
}
