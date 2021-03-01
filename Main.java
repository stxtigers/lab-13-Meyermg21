import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;
import java.io.*;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws IOException {
    String c = "Cat";
    String[] pets = new String[]{"Dog", "Pig", "Zebra"};
    int[] num = new int[]{12,15,22,57};
    

    printArray(insert(c, 1, pets, 5));
    printArray(delete(15,num,5));
    stats("sample.txt");
  }

  public static void printArray(String[] array)
  {
    for(int i = 0; i < array.length; i++)
    {
      System.out.println(array[i]);
    }
  }
  public static void printArray(int[] array)
  {
    for(int i = 0; i < array.length; i++)
    {
      System.out.println(array[i]);
    }
  }

  public static String[] insert(String s, int i, String[] a, int logicalSize)
  {
    String[] newList = new String[logicalSize];
    if(a.length == newList.length)
    {
      return null;
    }
    for(int h = 0; h < a.length; h++)
    {
      newList[h] = a[h];
    }
    for(int j = a.length-1; j >= i; j--)
    {
      newList[j+1] = newList[j];
    }
    newList[i] = s;
    return newList;
  }
  public static int[] delete(int target, int[] a, int logicalSize)
  {
    boolean wasFound = false;
    int[] newList = new int[logicalSize];
    for(int h = 0; h < a.length; h++)
    {
      newList[h] = a[h];
    }
    for(int i = 0; i < a.length; i++)
    {
      if(newList[i] == target)
      {
        for(int j = i; j < a.length; j++)
        {
          newList[j] = newList[j+1];
          wasFound = true;
        }
      }
    }
    if(wasFound == false)
    {
      return null;
    }
    return newList;
  }
  public static double average(int[] list)
  {
    double sum = 0.0;
    double average = 0.0;
    for(int i = 0; i < list.length; i++)
    {
      sum += list[i];
    }
    average = sum / list.length;
    return average;
  }
  public static int sum(int[] list)
  {
    int sum = 0;
    for(int i = 0; i < list.length; i++)
    {
      sum += list[i];
    }
    return sum;
  }
  public static double stdev(int[] list)
  {
    double sum = 0.0;
    for(int i = 0; i < list.length; i++)
    {
      sum += Math.pow((list[i] - average(list)), 2);
    }
    return (int)(Math.sqrt(sum/(list.length-1)));
  }
  public static double median(int[] m) {
    int middle = m.length/2;
    if (m.length%2 == 1) {
        return (int)m[middle];
    } else {
        return (int)(m[middle-1] + m[middle]) / 2.0;
    }
}
  
  public static void stats(String filename) throws IOException
  {
    int[] buffer = new int[5000];
    File newFile = new File(filename);
    
    Scanner fileReader = new Scanner(newFile);
    
    File stats = new File("stats.txt");
    int i = 0;
    while(fileReader.hasNext())
    {
      buffer[i] = fileReader.nextInt();
      i++;
    }
    int[] numbers = Arrays.copyOf(buffer, i); //gives us the correct size of array
    buffer = null; //allows the buffer to be thrown away after

    double mean = average(numbers);
    double median = median(numbers);
    double stanDev = stdev(numbers);


    FileWriter myWriter = new FileWriter("stats.txt");
    myWriter.write("The mean is " + mean + "\nThe median is " + median + "\nThe Standard Deviation is " + stanDev);
    myWriter.close();
  }
}