/**
 * Class for Outsourced parts
 * @author Faryn Dumont
 */
package Model;

public class Outsourced extends Part{

    String companyName;

    /**
     * Creates an Outsourced Part
     * @param aId Part ID
     * @param aName Part name
     * @param aPrice Part price
     * @param aStock Number of parts in stock
     * @param aMin
     * @param aMax
     * @param aCompanyName Name of company that made the part
     */
    public Outsourced(int aId, String aName, double aPrice, int aStock, int aMin, int aMax, String aCompanyName)
    {
        super(aId, aName,aPrice,aStock,aMin,aMax);
        companyName = aCompanyName;
    }

    /**
     * Sets the Company name
     * @param aCompanyName the Companies name
     */
    public void setCompanyName(String aCompanyName){companyName = aCompanyName;}

    /**
     * Gets the Company Name
     * @return the Companies name
     */
    public String getCompanyName(){return companyName;}
}
