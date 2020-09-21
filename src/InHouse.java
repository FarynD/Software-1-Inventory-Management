public class InHouse extends Part{

    int machineId;

    public InHouse(int aId, String aName, double aPrice, int aStock,int aMin,int aMax, int aMachineID)
    {
        super(aId, aName,aPrice,aStock,aMin,aMax);
        machineId = aMachineID;
    }

    public void setMachineId(int aMachineId){machineId = aMachineId;}

    public int getMachineId(){return machineId;}

}
