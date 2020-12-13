package database2;



public class SQLFactory extends AbstractFactory {
    @Override
    public database2.SQLUsers getUsers() {
        return SQLUsers.getInstance();
    }

    @Override
    public SQLMaterial getMaterial() {
        return SQLMaterial.getInstance();
    }
    @Override
    public SQLCollection getCollection() {
        return SQLCollection.getInstance();
    }

    @Override
    public SQLClientCard getClientCard() {
        return SQLClientCard.getInstance();
    }

    @Override
    public SQLJewellery getJewellery() {
        return SQLJewellery.getInstance();
    }

    @Override
    public SQLPurchase getPurchase() {
        return SQLPurchase.getInstance();
    }
    @Override
    public SQLDiscount getDiscount() {
        return SQLDiscount.getInstance();
    }

}
