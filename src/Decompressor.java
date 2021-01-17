import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//Uncompressing a file using an InflaterInputStream
class Decompressor {
    public static void main(String[] args) throws IOException {
        unzip("C:\\Users\\bouhl\\OneDrive\\Bureau\\Road To\\CodingGamesAndChallenges\\JavaTraining\\TestFolder");
        FileGrouper();
    }

    private static void unzip(String zipFilePath) {
        File dir = new File("C:\\Users\\bouhl\\OneDrive\\Bureau\\Road To\\CodingGamesAndChallenges\\JavaTraining\\Uncompressed");
        // create output directory if it doesn't exist
        if (!dir.exists())
            dir.mkdirs();
        FileInputStream fis;
        // buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(dir+ File.separator + fileName);
                System.out.println("Unzipping to " + newFile.getAbsolutePath());
                // create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                // close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            // close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void FileGrouper() throws FileNotFoundException, IOException {
        File dir = new File("C:\\Users\\bouhl\\OneDrive\\Bureau\\Road To\\CodingGamesAndChallenges\\JavaTraining\\Uncompressed");

        // create obejct of PrintWriter for output file
        PrintWriter pw = new PrintWriter("output.txt");

        // Get list of all the files in form of String Array
        String[] fileNames = dir.list();

        // loop for reading the contents of all the files
        // in the directory GeeksForGeeks
        for (String fileName : fileNames) {
            System.out.println("Reading from " + fileName);

            // create instance of file from Name of
            // the file stored in string Array
            File f = new File(dir, fileName);

            // create object of BufferedReader
            BufferedReader br = new BufferedReader(new FileReader(f));
            //pw.println("Contents of file " + fileName);

            // Read from current file
            String line = br.readLine();
            while (line != null) {

                // write to the output file
                pw.println(line);
                line = br.readLine();
            }
            pw.flush();
        }
        System.out.println("Reading from all files" +
                " in directory " + dir.getName() + " Completed");
    }


}
