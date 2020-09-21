public class Outsourced extends Part{

    String companyName;

    public Outsourced(int aId, String aName, double aPrice, int aStock, int aMin, int aMax, String aCompanyName)
    {
        super(aId, aName,aPrice,aStock,aMin,aMax);
        companyName = aCompanyName;
    }

    public void setCompanyName(String aCompanyName){companyName = aCompanyName;}

    public String getCompanyName(){return companyName;}
}
