import java.util.*;
import gridsim.*;
class Example3 extends GridSim
{
    private String entityName_;
    private GridletList list_;
    private GridletList receiveList_;
    Example3(String name, double baud_rate, GridletList list) throws Exception
    {
        super(name);
        this.list_ = list;
        receiveList_ = new GridletList();
        entityName_ = "Test";
        new Test(entityName_, baud_rate);
    }
    public void body()
    {
        int size = list_.size();
        Gridlet obj, gridlet;
        for (int i = 0; i < size; i++)
        {
            obj = (Gridlet) list_.get(i);
            System.out.println("Inside Example3.body() => Sending Gridlet " +
                    obj.getGridletID());
            super.send(entityName_, GridSimTags.SCHEDULE_NOW,
                       GridSimTags.GRIDLET_SUBMIT, obj);
            gridlet = super.gridletReceive();
            System.out.println("Inside Example3.body() => Receiving Gridlet "+
                    gridlet.getGridletID());
            receiveList_.add(gridlet);
        }
        super.send(entityName_, GridSimTags.SCHEDULE_NOW,
                   GridSimTags.END_OF_SIMULATION);
    }
    public GridletList getGridletList() {
        return receiveList_;
    }
    public static void main(String[] args)
    {
        System.out.println("Starting Example3");
        System.out.println();
        try
        {
            int num_user = 0;   // number of users need to be created
            Calendar calendar = Calendar.getInstance();
            boolean trace_flag = true;  // mean trace GridSim events
            String[] exclude_from_file = { "" };
            String[] exclude_from_processing = { "" };
            String report_name = null;
            System.out.println("Initializing GridSim package");
            GridSim.init(num_user, calendar, trace_flag, exclude_from_file,
                    exclude_from_processing, report_name);
            GridletList list = createGridlet();
            System.out.println("Creating " + list.size() + " Gridlets");
            Example3 obj = new Example3("Example3", 560.00, list);
            GridSim.startGridSimulation();
            GridletList newList = obj.getGridletList();
            printGridletList(newList);
            System.out.println("Finish Example3");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Unwanted errors happen");
        }
    }
    private static GridletList createGridlet()
    {
        GridletList list = new GridletList();
        int id = 0;
        double length = 3500.0;
        long file_size = 300;
        long output_size = 300;
        Gridlet gridlet1 = new Gridlet(id, length, file_size, output_size);
        id++;
        Gridlet gridlet2 = new Gridlet(id, 5000, 500, 500);
        id++;
        Gridlet gridlet3 = new Gridlet(id, 9000, 900, 900);
        list.add(gridlet1);
        list.add(gridlet2);
        list.add(gridlet3);
        long seed = 11L*13*17*19*23+1;
        Random random = new Random(seed);
        GridSimStandardPE.setRating(100);
        int count = 5;
        for (int i = 1; i < count+1; i++)
        {
            length = GridSimStandardPE.toMIs(random.nextDouble()*50);
            file_size = (long) GridSimRandom.real(100, 0.10, 0.40,
                                    random.nextDouble());
            output_size = (long) GridSimRandom.real(250, 0.10, 0.50,
                                    random.nextDouble());
            Gridlet gridlet = new Gridlet(id + i, length, file_size,
                                    output_size);
            list.add(gridlet);
        }
        return list;
    }
    private static void printGridletList(GridletList list)
    {
        int size = list.size();
        Gridlet gridlet;
        String indent = "    ";
        System.out.println();
        System.out.println("========== OUTPUT ==========");
        System.out.println("Gridlet ID" + indent + "STATUS");
        for (int i = 0; i < size; i++)
        {
            gridlet = (Gridlet) list.get(i);
            System.out.print(indent + gridlet.getGridletID() + indent
                    + indent);
            if (gridlet.getGridletStatus() == Gridlet.SUCCESS)
                System.out.println("SUCCESS");
        }
    }
}

