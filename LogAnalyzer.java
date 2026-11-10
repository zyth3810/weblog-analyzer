
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 7.0
 */
public class LogAnalyzer
{
    public static final int HOURS_PER_DAY = 24;
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[HOURS_PER_DAY];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }

    
    //Question 5.
    public LogAnalyzer(String fileName)
    {
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[HOURS_PER_DAY];
        // Create the reader to obtain the data.
        reader = new LogfileReader(fileName);

    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */

    // Question 4
    public void printHourlyCounts()
    {
        int hour = 0;
        while(hour<hourCounts.length){
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }

        // System.out.println("Hr: Count");
        // for(int hour = 0; hour < hourCounts.length; hour++) {
        // System.out.println(hour + ": " + hourCounts[hour]);
        // }
    }

    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }

    
    //Question 6.
    /** 
     * Return the number of accesses recorded in the log file.
     */

    public int numberOfAccesses()
    {
        int total = 0;
        for(int hour = 0; hour < hourCounts.length; hour++){
            total = total + hourCounts[hour];
        }
        // Add the value in each element of hourCounts to total. ...
        return total;
    } 
    
    
    //Question 8
    public int busiestHour(){
        int busiest = 0;
        for(int hours = 1; hours < hourCounts.length; hours++){
            if(hourCounts[hours] > hourCounts[busiest]){
                busiest = hours;
            }
        }
        return busiest;
    }
    
    
    //Question 9
    public int quietestHour(){
        int quiet = 0;
        for(int hour = 1 ; hour < hourCounts.length; hour++){
            if(hourCounts[hour] < hourCounts[quiet])
            {
                quiet = hour;
            }
        }
        return quiet;
    }
    
    
    //Question 10
    //It's 18
    
    //Question 11
    public int busiestPeriod(){
     int busiestPeriod = 0;
     int maxAccess = hourCounts[0] + hourCounts[1];
     for (int hour = 1; hour < hourCounts.length - 1; hour++){
         int currentSum = hourCounts[hour] + hourCounts[hour + 1];
         if (currentSum > maxAccess){
             maxAccess = currentSum;
             busiestPeriod = hour;
         }
     }
     return busiestPeriod;
    }
}