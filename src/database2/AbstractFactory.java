package database2;

//import database.*;

public abstract class AbstractFactory {
    public abstract SQLUsers getUsers();
    public abstract SQLMaterial getMaterial();
    public abstract SQLCollection getCollection();

    public abstract SQLClientCard getClientCard();

    public abstract SQLJewellery getJewellery();
    public abstract SQLPurchase getPurchase();

    public abstract SQLDiscount getDiscount();
//    public abstract SQLDiscount getStatistics1();

}
