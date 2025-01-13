//Importing Lists and a Scanner object
import java.util.List;
import java.util.Scanner;

//Creating a class Main that will sucessfully scrape Job Titles and store them in a CSV and XML file
public class Main {

    //Creating the main method
    public static void main(String[] args) {

        //Creating the scraper, extractor, and outputHandler objects from their respective classes 
        Scraper scraper = new Scraper();
        DataExtractor extractor = new DataExtractor();
        OutputHandler outputHandler = new OutputHandler();

        //Creating a scanner object 
        Scanner scanner = new Scanner(System.in);

        //Asking the user for a URL to scrape and storing it in a String object named url 
        System.out.print("Enter the URL to scrape: ");
        String url = scanner.nextLine();

        //Closing the scanner 
        scanner.close();

        //Creating a String object named htmlContent that will store the raw HTML content by using the scraper object to call scrapeHTML with the url passed as an argument
        String htmlContent = scraper.scrapeHTML(url);

        //Checking to see if the htmlContent variable is null such that there was an error in scraping the website 
        if (htmlContent != null) { 

            //Creating an ArrayList String object named extractedData that stores the processed HTML data by using the extractor object to call extractData with the htmlContent passed as the argument 
            List<String[]> extractedData = extractor.extractData(htmlContent);

            //Creating a String object named desktopPath that stores the path to the user's desktop 
            String desktopPath = System.getProperty("user.home") + "/Desktop/";

            //Creating String objects named csvFilePath and htmlFilePath that will be the path to their respective files on the user's computer 
            String csvFilePath = desktopPath + "internships.csv";
            String htmlFilePath = desktopPath + "internships.html";

            //Calling saveToCSV and saveToHTML such that the processed data is saved to their respective file types on the user's desktop, with the extractedData and csvFilePath/htmlFilePath as the arguments passed
            outputHandler.saveToCSV(extractedData, csvFilePath);
            outputHandler.saveToHTML(extractedData, htmlFilePath);

            //Letting the user know that the data extraction was completed and to check the following files with their location given by displaying their paths to the desktop 
            System.out.println("Data extraction complete. Check the following files:");
            System.out.println("CSV File: " + csvFilePath);
            System.out.println("HTML File: " + htmlFilePath);

        } 
        
        //If the htmlContent is null, display an error message 
        else {

            //Displaying an error message that tells the user that the website could not be scraped 
            System.err.println("Failed to scrape the website.");

        }

    }

}