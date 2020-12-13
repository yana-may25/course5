package database2;

import classes.Jewellery;
import stats.Statistics1;

import java.util.ArrayList;

public class SQLStatistics1 implements IStatistics1{

    private ConnectionDB dbConnection;
    private static SQLStatistics1 instance;
    public SQLStatistics1() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLStatistics1 getInstance() {
        if (instance == null) {
            instance = new SQLStatistics1();
        }
        return instance;
    }

    public ArrayList<Statistics1> selectStatistics1() {
        String str = "select idorder, count(purchase.idjewellery),  sum(price)\n" +
                "from purchase\n" +
                "inner join jewellery on purchase.idjewellery=jewellery.idjewellery\n" +
                "group by purchase.idorder\n" +
                "order by  count(purchase.idjewellery) DESC";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Statistics1> jewName = new ArrayList<>();

        for (String[] items : result) {
            Statistics1 jew = new Statistics1();
            jew.setIdOrder(Integer.parseInt(items[0]));
            jew.setJewNumber(Integer.parseInt(items[1]));
            jew.setSum(Double.parseDouble(items[2]));
            jewName.add(jew);
        }


        return jewName;
    }
}
