package main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Main class of CBZ-Creator. Requires 7-zip installed.
 * 
 * @author Alberto Casas Ortiz
 */
public class Main {
	private static String imageFormats[] = {"ANI", "BMP", "CAL", "FAX", "GIF", "IMG", "JBG", "JPE", "JPEG", "JPG", "MAC", "PBM", "PCD", "PCX", "PCT", "PGM", "PNG", "PPM", "PSD", "RAS", "TGA", "TIFF", "WMF"};
	
	private static String fileName = "";
	private static String outputPath = "";
	private static String imageFormat = "";
	private static int amount = 0;


	public static void main(String[] args) {
		//If first argument is -h show help.
		if(args[0].compareTo("-h") == 0){
			showHelp();
		//If first argument is -m and image format is valid, execute multiple folders.
		} else if (args[0].compareTo("-m") == 0 && Arrays.asList(imageFormats).contains(args[1])) {
			imageFormat = args[1];
			fileName = args[2];
			outputPath = args[3];
			amount = Integer.parseInt(args[4]);
			compressAssCbzMultipleFolders(amount);
		//If first argument is not -m and is a valid image format, execute for one folder.
		} else if(Arrays.asList(imageFormats).contains(args[0])){
			imageFormat = args[0];
			fileName = args[1];
			outputPath = args[2];
			compressAssCbzOneFolder();
		//Otherwise, show help.
		} else{
			showHelp();
		}
	}
	
	public static void showHelp(){
		System.out.println("Format for multiple files: CBZ-Creator.jar -m imageFormat filenameInit outputPathInit amountFiles");
		System.out.println("Format for single files: CBZ-Creator.jar imageFormat filename outputPath");
		System.out.println("");
		System.out.println("Example:");
		System.out.println("If we have the next 3 folders with jpg images:");
		System.out.println("Capitulo 1");
		System.out.println("Capitulo 2");
		System.out.println("Capitulo 3");
		System.out.println("We will call this command: CBZ-Creator.jar -m jpg \"Capitulo \" \"Output \"  3");
		System.out.println("It will create 3 files:");
		System.out.println("Capitulo 01.cbz");
		System.out.println("Capitulo 02.cbz");
		System.out.println("Capitulo 03.cbz");
	}

	public static void compressAssCbzMultipleFolders(int num) {
		for (int i = 0; i <= num; i++) {
			String path = "\"" + fileName + i + "\\*." + imageFormat + "\"";
			if(new File(fileName + i + "\\").exists()){
				String Output = "\"" + outputPath + (i < 10 ? "0" + i : i) + ".cbz" + "\"";
				String command = "7z a " + Output + " " + path;
				try {
					Runtime.getRuntime().exec(command);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void compressAssCbzOneFolder() {
		String path = "\"" + fileName + "\\*." + imageFormat + "\"";
		String Output = "\"" + outputPath + ".cbz" + "\"";
		String command = "7z a " + Output + " " + path;
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
