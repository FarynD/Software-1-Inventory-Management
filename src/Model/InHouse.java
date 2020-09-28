package Model;

public class InHouse extends Part{

    int machineId;

    /**
     * Creates an InHouse Part
     * @param aId Part ID
     * @param aName Part name
     * @param aPrice Part price
     * @param aStock Number of parts in stock
     * @param aMin
     * @param aMax
     * @param aMachineID ID of machine that made the part
     */
    public InHouse(int aId, String aName, double aPrice, int aStock,int aMin,int aMax, int aMachineID)
    {
        super(aId, aName,aPrice,aStock,aMin,aMax);
        machineId = aMachineID;
    }

    /**
     * Sets the Machine ID
     * @param aMachineId The Machines ID
     */
    public void setMachineId(int aMachineId){machineId = aMachineId;}

    /**
     * Gets the Machines ID
     * @return the Machines ID
     */
    public int getMachineId(){return machineId;}

}
