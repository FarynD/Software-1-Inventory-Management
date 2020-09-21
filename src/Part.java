public abstract class Part {

    int id;
    String name;
    double price;
    int stock;
    int min;
    int max;

    public Part(int aId, String aName, double aPrice, int aStock, int aMin, int aMax)
    {
        id = aId;
        name = aName;
        price = aPrice;
        stock = aStock;
        min = aMin;
        max = aMax;
    }

    public void setID(int aId){id = aId;}

    public void setName(String aName){name = aName;}

    public void setPrice(double aPrice){price = aPrice;}

    public void setStock(int aStock){stock = aStock;}

    public void setMin(int aMin){min = aMin;}

    public void setMax(int aMax){max = aMax;}



    public int getId(){return id;}

    public String getName(){return name;}

    public double getPrice(){return price;}

    public int getStock(){return stock;}

    public int getMin(){return min;}

    public int getMax(){return max;}


}
