package yelpreview;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 
 * @author jackspiderman
 * 
 *         Preprocess raw data. Read each line and write it into the files
 *         decided by ranking number. S
 *         plite the data into train data and test
 *         data.
 *
 */
public class PrepareDataset {

	final static Logger logger = LoggerFactory.getLogger(PrepareDataset.class);

	/***
	 * 
	 * @param path , where yelp reviews dataset locates
	 * @return a list to record numbers of each class
	 */

	private static ArrayList<Integer> countClassNum(String path) {

		FileInputStream inputstream = null;
		BufferedReader bufferReader = null;
		int class1 = 0;
		int class2 = 0;
		int class3 = 0;
		int class4 = 0;
		int class5 = 0;
		try {
			inputstream = new FileInputStream(path);
			bufferReader = new BufferedReader(new InputStreamReader(inputstream));
			String line = bufferReader.readLine(); // skip head

			while ((line = bufferReader.readLine()) != null) {

				String[] lineSplit = line.split("\",\"");

				if (lineSplit.length != 2) {
					continue;
				}

				String classIndex = lineSplit[0].replaceAll("\"", "");
				lineSplit[1].replaceAll("\"", "");

				switch (classIndex) {
				case "1":
					class1 += 1;
					break;
				case "2":
					class2 += 1;
					break;
				case "3":
					class3 += 1;
					break;
				case "4":
					class4 += 1;
					break;
				case "5":
					class5 += 1;
					break;

				default:
					break;
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				inputstream.close();
				bufferReader.close();

			} catch (IOException e) {

				e.printStackTrace();
			}

		}

		ArrayList<Integer> classNums = new ArrayList<Integer>();
		classNums.add(class1);
		classNums.add(class2);
		classNums.add(class3);
		classNums.add(class4);
		classNums.add(class5);
		return classNums;

	}

	public static void main(String[] args) {

		logger.info("Preprocessing...");

		String rawDataPath = "yelp_review_full_csv";
		String dataDir = "";
		float trainRatio = 0.9f;
		float testFactor = 1.1f;

		try {
			rawDataPath = Utils.loadConf(args[0], "rawDataPath");
			dataDir = Utils.loadConf(args[0], "dataDir");
			trainRatio = Float.parseFloat(Utils.loadConf(args[0], "trainRatio"));
			testFactor = Float.parseFloat(Utils.loadConf(args[0], "testFactor"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		logger.info("Set raw data path to {}", rawDataPath);
		logger.info("Set data path to {}", dataDir);
		logger.info("Set the ratio of trainset is {}", String.valueOf(trainRatio));
		logger.info("Set the factor of testset is {}", String.valueOf(testFactor));

		ArrayList<Integer> countClassNum = countClassNum(rawDataPath);
		for (int i = 0; i < countClassNum.size(); i++) {
			logger.info("number of class" + (i + 1) + ": " + countClassNum.get(i));
		}

		int trainNum = (int) (countClassNum.get(0) * trainRatio); // split into train set and test set

		logger.info("Create streams for each training or testing file.");

		FileInputStream inputstream = null;
		BufferedReader bufferReader = null;
		FileWriter fw1 = null;
		FileWriter fw2 = null;
		FileWriter fw3 = null;
		FileWriter fw4 = null;
		FileWriter fw5 = null;

		BufferedWriter bfw1 = null;
		BufferedWriter bfw2 = null;
		BufferedWriter bfw3 = null;
		BufferedWriter bfw4 = null;
		BufferedWriter bfw5 = null;

		FileWriter fw6 = null;
		FileWriter fw7 = null;
		FileWriter fw8 = null;
		FileWriter fw9 = null;
		FileWriter fw10 = null;

		BufferedWriter bfw6 = null;
		BufferedWriter bfw7 = null;
		BufferedWriter bfw8 = null;
		BufferedWriter bfw9 = null;
		BufferedWriter bfw10 = null;

		int countClass1 = 0;
		int countClass2 = 0;
		int countClass3 = 0;
		int countClass4 = 0;
		int countClass5 = 0;

		String trainDir1 = dataDir + "/train/0.txt";
		String trainDir2 = dataDir + "/train/1.txt";
		String trainDir3 = dataDir + "/train/2.txt";
		String trainDir4 = dataDir + "/train/3.txt";
		String trainDir5 = dataDir + "/train/4.txt";

		String testDir1 = dataDir + "/test/0.txt";
		String testDir2 = dataDir + "/test/1.txt";
		String testDir3 = dataDir + "/test/2.txt";
		String testDir4 = dataDir + "/test/3.txt";
		String testDir5 = dataDir + "/test/4.txt";

		List<String> dirList = new ArrayList<String>();
		dirList.add(trainDir1);
		dirList.add(trainDir2);
		dirList.add(trainDir3);
		dirList.add(trainDir4);
		dirList.add(trainDir5);
		dirList.add(testDir1);
		dirList.add(testDir2);
		dirList.add(testDir3);
		dirList.add(testDir4);
		dirList.add(testDir5);

		for (String dir : dirList) {
			File file = new File(dir);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				file.delete();
			}
		}

		try {

			inputstream = new FileInputStream(rawDataPath);
			String line;
			bufferReader = new BufferedReader(new InputStreamReader(inputstream));

			// for training stream
			fw1 = new FileWriter(trainDir1);
			fw2 = new FileWriter(trainDir2);
			fw3 = new FileWriter(trainDir3);
			fw4 = new FileWriter(trainDir4);
			fw5 = new FileWriter(trainDir5);

			bfw1 = new BufferedWriter(fw1);
			bfw2 = new BufferedWriter(fw2);
			bfw3 = new BufferedWriter(fw3);
			bfw4 = new BufferedWriter(fw4);
			bfw5 = new BufferedWriter(fw5);

			// for test stream
			fw6 = new FileWriter(testDir1);
			fw7 = new FileWriter(testDir2);
			fw8 = new FileWriter(testDir3);
			fw9 = new FileWriter(testDir4);
			fw10 = new FileWriter(testDir5);

			bfw6 = new BufferedWriter(fw6);
			bfw7 = new BufferedWriter(fw7);
			bfw8 = new BufferedWriter(fw8);
			bfw9 = new BufferedWriter(fw9);
			bfw10 = new BufferedWriter(fw10);

			logger.info("Start to write data...");
			line = bufferReader.readLine(); // skip head
			while ((line = bufferReader.readLine()) != null) {

				String[] lineSplit = line.split("\",\"");

				if (lineSplit.length != 2) {
					continue;
				}

				String classIndex = lineSplit[0].replaceAll("\"", "");
				String review = lineSplit[1].replaceAll("\"", "") + "\n";

				// put into files classified by ranking number
				switch (classIndex) {
				case "1":

					if (countClass1 <= trainNum * testFactor) {
						if (countClass1 <= trainNum) {
							bfw1.write(review);
						} else {
							bfw6.write(review);
						}
						countClass1 += 1;
					}

					break;
				case "2":

					if (countClass2 <= trainNum * testFactor) {
						if (countClass2 <= trainNum) {
							bfw2.write(review);
						} else {
							bfw7.write(review);
						}
						countClass2 += 1;
					}

					break;
				case "3":
					if (countClass3 <= trainNum * testFactor) {
						if (countClass3 <= trainNum) {
							bfw3.write(review);
						} else {
							bfw8.write(review);
						}
						countClass3 += 1;
					}

					break;
				case "4":
					if (countClass4 <= trainNum * testFactor) {
						if (countClass4 <= trainNum) {
							bfw4.write(review);
						} else {
							bfw9.write(review);
						}
						countClass4 += 1;
					}

					break;
				case "5":
					if (countClass5 <= trainNum * testFactor) {
						if (countClass5 <= trainNum) {
							bfw5.write(review);
						} else {
							bfw10.write(review);
						}
						countClass5 += 1;
					}

					break;

				default:
					break;
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

				bfw1.flush();
				bfw2.flush();
				bfw3.flush();
				bfw4.flush();
				bfw5.flush();
				bfw6.flush();
				bfw7.flush();
				bfw8.flush();
				bfw9.flush();
				bfw10.flush();

				logger.info("Clearing resources.");

				if (null != inputstream)
					inputstream.close();
				if (null != bufferReader)
					bufferReader.close();
				if (null != bfw1)
					bfw1.close();
				if (null != bfw2)
					bfw2.close();
				if (null != bfw3)
					bfw3.close();
				if (null != bfw4)
					bfw4.close();
				if (null != bfw5)
					bfw5.close();
				if (null != bfw6)
					bfw6.close();
				if (null != bfw7)
					bfw7.close();
				if (null != bfw8)
					bfw8.close();
				if (null != bfw9)
					bfw9.close();
				if (null != bfw10)
					bfw10.close();

			} catch (IOException e) {

				e.printStackTrace();
			}

		}

		logger.info("Done");

	}

}
