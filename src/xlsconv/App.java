package xlsconv;

import java.io.File;

public class App {

	private static String inputFile;
	public static void main(String[] args) throws Exception {
		// Print help messages if first argument not exists
		if (args.length < 1) {
			System.out.println("Usage:");
			System.out.println("\t\tXLSX2CSV <xlsx file> [min columns]");
			System.exit(0);
		}

		// File checking
		inputFile = new String(args[0]);
		File xlsxFile = new File(inputFile);
		if (!xlsxFile.exists()) {
			System.err.println("Not found or not a file: " + xlsxFile.getPath());
			System.exit(1);
		}
		
		// The second parameter is minimum Columns need to convert
		int minColumns = -1;
		if (args.length >= 2) minColumns = Integer.parseInt(args[1]);
		
		String extension = inputFile.substring(inputFile.lastIndexOf(".") + 1, inputFile.length());
		
		if (extension.equalsIgnoreCase("xls")){
			System.out.println("Gonna convert an Excel file: " + inputFile);
			XLS2CSV handle = new XLS2CSV(xlsxFile.getPath(), minColumns);
			handle.process();
		} else if (extension.equalsIgnoreCase("xlsx")){
			System.out.println("Gonna convert an xExcel file: " + inputFile);
			// The package open is instantaneous, as it should be.
			XLSX2CSV handle = new XLSX2CSV(xlsxFile.getPath(), minColumns);
			handle.process();
		} else {
			System.err.println("Unknown extension: " + extension + ", file :" + inputFile);
		}
		System.exit(0);
	}

}
