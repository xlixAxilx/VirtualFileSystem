import java.util.HashMap;

/**
 * Represents a virtual drive where files are stored.
*/
public class Drive {

  // A map that acts as the virtual disk, 
  // with character keys representing file names 
  // and string values representing file data.
  private HashMap<Character, String> virtualDisk;

  /**
    * Constructor to initialize the virtual disk.
  */
  public Drive() {
    virtualDisk = new HashMap<>();
  }

  /**
    * Stores a file in the virtual drive.
    * @param name The name of the file.
    * @param data The content of the file.
  */
  public void setFile(char name, String data) {
    virtualDisk.put(name, data);  
  }

  /**
    * Retrieves the content of a file from the virtual drive.
    * @param name The name of the file.
    * @return The content of the file.
  */
  public String getFile(char name) {
    return virtualDisk.get(name);  
  }

  /**
    * Lists the names of all files in the virtual drive.
    * @return An array containing the names of all files.
  */
  public char[] names() {
    char[] names = new char[virtualDisk.size()];
    int index = 0;
    for (char name : virtualDisk.keySet()) {
      names[index++] = name;
    }
    return names;
  }

  /**
    * Checks if a file exists in the virtual drive.
    * @param name The name of the file.
    * @return true if the file exists, false otherwise.
  */
  public boolean fileExists(char name) {
    return virtualDisk.containsKey(name);
  }
  
}
