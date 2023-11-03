import java.io.*;

/**
  * Provides the user interface and command-handling for the Virtual File System.
 */
public class UI {

  // An instance of the virtual drive to perform file operations.
  private static Drive virtualDrive = new Drive();

  /**
    * Parses a command string into individual tokens.
    * @param command The command string.
    * @return An array of tokens from the command.
   */
  public static String[] parseCommand(String command) {
    return command.split("\\s+");
  }

  /**
    * Imports a file from the real file system into the virtual drive.
    * @param tokens Tokens from the user's command.
   */
  public static void importFile(String[] tokens) {
    if (tokens.length != 3) {
      System.out.println("Usage: import <PATH> <VFS>");
      return;
    }

    String path = tokens[1];
    char vfsName = tokens[2].charAt(0);

    // Check if the file name is a lowercase letter
    if (!Character.isLowerCase(vfsName)) {
      System.out.println("File names must be lowercase letters.");
      return;  
    }

    // Ensure the imported file is a .txt file
    if (!path.endsWith(".txt")) {
      System.out.println("Only .txt files can be imported.");
      return;  
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
      // Use StringBuilder to efficiently concatenate the file content line by line.
      StringBuilder data = new StringBuilder();
      // Read each line of the file until reaching the end.
      String line;
      while ((line = reader.readLine()) != null) {
        data.append(line).append("\n"); 
      }

      // Store the read content in the virtual drive with the specified name.
      virtualDrive.setFile(vfsName, data.toString());
    }
      catch (IOException e) {
        System.out.println("Error importing file: " + e.getMessage());
    }
  }
    
  /**
    * Exports a file from the virtual drive to the real file system.
    * @param tokens Tokens from the user's command.
  */
  public static void exportFile(String[] tokens) {
    if (tokens.length != 3) {
      System.out.println("Usage: export <VFS> <PATH>");
      return; 
    }
    char vfsName = tokens[1].charAt(0);
    String path = tokens[2];
    
    // Ensure the exported file is a .txt file
    if (!path.endsWith(".txt")){
      System.out.println("Only .txt files can be exported.");
      return;  
    }

    // Ensure that the file exists in the virtual drive
    if (!virtualDrive.fileExists(vfsName)) {
      System.out.println("Error, file not found in virtual drive.");
      return;
    }

    // Ensure the file does not exist in the real file system
    File outputFile = new File(path);    
    if (outputFile.exists()) {
      System.out.println("Error, file already exists in the real file system.");
      return;
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
      // Write the content from the virtual drive to the specified output file.
      writer.write(virtualDrive.getFile(vfsName));
      
      System.out.println("File exported successfully.");
    } catch (IOException e) {
      System.out.println("Error exporting file: " + e.getMessage());
    }
  }
  
  /**
    * Lists the names and sizes of files stored in the virtual drive.
    * @param tokens Tokens from the user's command (not used).
  */
  public static void ls(String[] tokens) {
    char[] fileNames = virtualDrive.names();
    for (char name : fileNames) {
      System.out.println(name + " " + virtualDrive.getFile(name).length());
    }
  }
  
}