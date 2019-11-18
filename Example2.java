import java.util.*;
import gridsim.*;
public class Example2
{
    public static void main(String[] args) 
    {
        System.out.println("Starting example of how to create Grid users");
        System.out.println();
        try 
        { 
            GridletList list = createGridlet();
            System.out.println("Creating " + list.size() + " Gridlets");
            ResourceUserList userList = createGridUser(list);
            System.out.println("Creating " + userList.size() + " Grid users");
            printGridletList(list);
            System.out.println("Finish the example");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Unwanted error happens");
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
        Random random = new Random();   
        GridSimStandardPE.setRating(100);
        int count = 5;
        double min_range = 0.10;
        double max_range = 0.50;
        for (int i = 1; i < count+1; i++)
        {
            length = GridSimStandardPE.toMIs(random.nextDouble()*output_size);
            file_size = (long) GridSimRandom.real(100, min_range, max_range, 
                                    random.nextDouble());
            output_size = (long) GridSimRandom.real(250, min_range, max_range,
                                    random.nextDouble());
            Gridlet gridlet = new Gridlet(id + i, length, file_size,
                                    output_size);
            list.add(gridlet);
        }

        return list;
    }
    private static ResourceUserList createGridUser(GridletList list)
    {
        ResourceUserList userList = new ResourceUserList();
        
        userList.add(0);    // user ID starts from 0
        userList.add(1);
        userList.add(2);

        int userSize = userList.size();
        int gridletSize = list.size();
        int id = 0;
        for (int i = 0; i < gridletSize; i++)
        {
            if (i != 0 && i % userSize == 0)
                id++;

            ( (Gridlet) list.get(i) ).setUserID(id);
        }
        
        return userList;
    }
    private static void printGridletList(GridletList list)
    {
        int size = list.size();
        Gridlet gridlet;
        
        String indent = "    ";
        System.out.println();
        System.out.println("Gridlet ID" + indent + "User ID" + indent +
                "length" + indent + " file size" + indent +
                "output size");
        
        for (int i = 0; i < size; i++)
        {
            gridlet = (Gridlet) list.get(i);
            System.out.println(indent + gridlet.getGridletID() + indent + 
                    indent + indent + gridlet.getUserID() + indent + indent +
                    (int) gridlet.getGridletLength() + indent + indent +
                    (int) gridlet.getGridletFileSize() + indent + indent +
                    (int) gridlet.getGridletOutputSize() );
        }
    }   
}

