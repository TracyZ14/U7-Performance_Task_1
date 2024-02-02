import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class Main
{
    public static void main(String[] args)
    {
        ArrayList<String> fileData = getFileData("data/brick_layout");
        ArrayList<Brick> bricks = new ArrayList<Brick>();
        for (String line : fileData)
        {
            String[] points = line.split(",");
            int start = Integer.parseInt(points[0]);
            int end = Integer.parseInt(points[1]);
            Brick b = new Brick(start, end);
            bricks.add(b);
        }


        System.out.println(partOne(bricks));
        System.out.println(partTwo(bricks));
    }

    private static int partOne(ArrayList<Brick> bricks)
    {
        int totalLength = 0;
        for(Brick brick : bricks)
        {
            int length = brick.getEnd() - brick.getStart();
            totalLength += length;
        }
        return totalLength;
    }

    private static int partTwo(ArrayList<Brick> bricks)
    {
        int mostBricksPosition = 0;
        int mostBricks = 0;
        ArrayList<Integer> numberOfBricks = new ArrayList<Integer>();
        numberOfBricks.add(0);
        for(int i = 0; i < bricks.size(); i++)
        {
            int beginAddBricks = bricks.get(i).getStart();
            int endAddBricks = bricks.get(i).getEnd();
            while((numberOfBricks.size() - 1) < endAddBricks)
            {
                numberOfBricks.add(0);
            }
            int length = endAddBricks - beginAddBricks + 1;
            for(int j = 0; j < length; j++)
            {
                int newNumberOfBricks = numberOfBricks.get(beginAddBricks + j) + 1;
                numberOfBricks.set((beginAddBricks + j), newNumberOfBricks);
            }
        }
        for(int i = 0; i < numberOfBricks.size(); i++)
        {
            if(numberOfBricks.get(i) > mostBricks)
            {
                mostBricks = numberOfBricks.get(i);
                mostBricksPosition = i;
            }
        }
        return mostBricksPosition;
    }

    public static ArrayList<String> getFileData(String fileName)
    {
        File f = new File(fileName);
        Scanner s = null;
        try
        {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
            System.exit(1);
        }
        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        return fileData;
    }
}