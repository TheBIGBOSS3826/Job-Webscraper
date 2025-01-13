//Importing Lists, FileWriter, and IOException
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

//Creating a class named OutputHandler that will save the Job Titles to a csv and a HTML file 
public class OutputHandler {

    //Creating a method named saveToCSV that will save the Job Titles to a csv file, with the ArrayList that stores the Job Titles and the file path to the desktop passed as arguments
    public void saveToCSV(List<String[]> data, String filePath) {

        //Initializing a try-catch block that will FileWrite the Job Titles to a csv file with a FileWriter named writer that points to the desktop 
        try (FileWriter writer = new FileWriter(filePath)) {

            //Writing the column of "Job Title"
            writer.write("Job Title\n"); 

            //Using a for loop to iterate through each row in the data list such that each row is a Job Title and append it to the csv file
            for (String[] row : data) {

                //Write the Job Title to the csv 
                writer.write(row[0] + "\n"); 

            }

            //Telling the user that the Job Titles were sucessfully saved to the csv file 
            System.out.println("Job data saved to CSV: " + filePath);

        } 
        
        //If any error occurs saving the csv file, an error message is displayed
        catch (IOException e) {

            //Displaying error message and the exact error 
            System.err.println("Error saving CSV file: " + e.getMessage());

        }

    }

    //Creating a method named saveToHTML that will save the Job Titles to a HTML file, with the ArrayList that stores the Job Titles and the file path to the desktop passed as arguments
    public void saveToHTML(List<String[]> data, String filePath) {

        //Initializing a try-catch block that will FileWrite the Job Titles to a HTML file with a FileWriter named writer that points to the desktop
        try (FileWriter writer = new FileWriter(filePath)) {

            //Creating the layout of the HTML file 
            writer.append("<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n");
            writer.append("<meta charset=\"UTF-8\">\n<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
            writer.append("<title>Job Data</title>\n");
            writer.append("<style>\n");
            writer.append("body { font-family: Arial, sans-serif; margin: 20px; padding: 0; }\n");
            writer.append("table { width: 100%; border-collapse: collapse; margin-top: 20px; }\n");
            writer.append("table, th, td { border: 1px solid #ddd; }\n");
            writer.append("th, td { padding: 10px; text-align: left; }\n");
            writer.append("th { background-color: #f4f4f4; }\n");
            writer.append("</style>\n</head>\n<body>\n");
            writer.append("<h1>Scraped Job Titles</h1>\n");
            writer.append("<table>\n");
            writer.append("<tr><th>Job Title</th></tr>\n");
    
            //Using a for loop to iterate through each row in the data list such that each row is a Job Title and append it to the HTML file
            for (String[] row : data) {

                //Appending the Job Title to the HTML file 
                writer.append("<tr><td>").append(formatHTMLField(row[0])).append("</td></tr>\n"); 
                        
            }
    
            //Closing out the HTML code 
            writer.append("</table>\n");
            writer.append("</body>\n</html>");

            //Telling the user that the Job Data was sucessfully saved 
            System.out.println("Job data saved to HTML: " + filePath);

        } 
        
        //If any error occurs writing/saving to the HTML file, display an error message
        catch (IOException e) {
            
            //Displaying error message and the exact error 
            System.err.println("Error writing to HTML file: " + e.getMessage());

        }

    }

    //Creating a method named formatHTMLField that will format a String field for HTML output, with the String field passed as the argument 
    private String formatHTMLField(String field) {

        //If the field is null or empty, return "N/A" if true, otherwise escape special characters for HTML
        return field == null || field.isEmpty() ? "N/A" : field.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");

    }

}