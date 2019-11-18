import java.util.Calendar;
import java.util.LinkedList;
import gridsim.*;
class Example1
{
       public static void main(String[] args)
    {
        System.out.println("Starting example of how to create one Grid " +
                "resource");
        try
        {
            int num_user = 0;   
            Calendar calendar = Calendar.getInstance();
            boolean trace_flag = true; // mean trace GridSim events/activities
                       String[] exclude_from_file = { "" };
            String[] exclude_from_processing = { "" };
                       String report_name = null;
                        System.out.println("Initializing GridSim package");

            GridSim.init(num_user, calendar, trace_flag, exclude_from_file,
                    exclude_from_processing, report_name);

    
            GridResource gridResource = createGridResource();
            System.out.println("Finish the 1st example");
                  }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Unwanted error happens");
        }
    }
      private static GridResource createGridResource()
    {
        System.out.println("Starting to create one Grid resource with " +
                "3 Machines ...");
              MachineList mList = new MachineList();
        System.out.println("Creates a Machine list");
                int mipsRating = 377;
        mList.add( new Machine(0, 4, mipsRating));  
        System.out.println("Creates the 1st Machine that has 4 PEs and " +
                "stores it into the Machine list");
                mList.add( new Machine(1, 4, mipsRating));   
        System.out.println("Creates the 2nd Machine that has 4 PEs and " +
                "stores it into the Machine list");
        mList.add( new Machine(2, 2, mipsRating));  
        System.out.println("Creates the 3rd Machine that has 2 PEs and " +
                "stores it into the Machine list");
        String arch = "Sun Ultra";    
        String os = "Solaris";        
        double time_zone = 9.0;       
        double cost = 3.0;              
        ResourceCharacteristics resConfig = new ResourceCharacteristics(
                arch, os, mList, ResourceCharacteristics.TIME_SHARED,
                time_zone, cost);
        System.out.println();
        System.out.println("Creates the properties of a Grid resource and " +
                "stores the Machine list");
        String name = "Resource_0";        
        double baud_rate = 100.0;     
        long seed = 11L*13*17*19*23+1;
        double peakLoad = 0.0;       
        double offPeakLoad = 0.0;     
        double holidayLoad = 0.0;  
        LinkedList<Integer> Weekends = new LinkedList<Integer>();
        Weekends.add(new Integer(Calendar.SATURDAY));
        Weekends.add(new Integer(Calendar.SUNDAY));
               LinkedList<Integer> Holidays = new LinkedList<Integer>();
        GridResource gridRes = null;
        try
        {
            gridRes = new GridResource(name, baud_rate, seed,
                resConfig, peakLoad, offPeakLoad, holidayLoad, Weekends,
                Holidays);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finally, creates one Grid resource and stores " +
                "the properties of a Grid resource");
        return gridRes;
    }
} 
