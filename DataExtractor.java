//Importing the Jsoup library and objects associated with it such as the Document object and the Element object. Also importing Lists
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;

//Creating a class DataExtractor that will process the data into non-HTML content that can be used 
public class DataExtractor {

    //Creating a method named extractData that will extract the data concerning Job Titles and represent all of them in a String ArrayList, with the HTML passed as an argument
    public List<String[]> extractData(String htmlContent) {

        //Creating a String ArrayList named jobData that will store the Job Titles 
        List<String[]> jobTitles = new ArrayList<>();

        //Initiating a try-catch block to process the HTML data to extract the Job Titles 
        try {

            //Creating a doccument object named jobDocument that stores the parsed HTML data from using the JSoup 3rd party library 
            Document jobDocument = Jsoup.parse(htmlContent);

            //Letting the user know that Job Titles are being extracted 
            System.out.println("Extracting Job Titles...");

            //Creating an Elements object named jobTitleElements that stores the Job Title Elements by using jobDocument.select() to obtain them
            Elements jobTitleElements = jobDocument.select("h2.jobTitle, span[title]");

            //Using a for loop to get the individual Job Titles from jobTitle elements and add them to the ArrayList- starting at 0 and going up to the size of jobTitleElements
            for (int i = 0; i < jobTitleElements.size(); i++) {

                //Creating a variable named jobTitle that stores the current jobTitleElement value by getting the elements text 
                String jobTitle = jobTitleElements.get(i).text();
                
                //Adding the jobTitle variable to the jobTitles ArrayList
                jobTitles.add(new String[]{jobTitle});

            }

            //Letting the user know that the Job Title extraction was successfully completed
            System.out.println("Job title extraction complete.");

        } 
        
        //If any error occurs with the extraction process, display an error message
        catch (Exception e) {

            //Displaying the error message with the specific issue 
            System.err.println("An error occurred while extracting data: " + e.getMessage());

        }

        //Returning the jobTitles ArrayList 
        return jobTitles;

    }

}