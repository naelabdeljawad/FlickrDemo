import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

	private FileInputStream inputStream;
	private static PropertiesReader propertiesReader;

	public static synchronized PropertiesReader getInstance() {
		if (propertiesReader == null)
			return new PropertiesReader();
		return propertiesReader;
	}

	public String getProperty(String property) {
		try {
			return getPropertyValue(property);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getProperty(String propFilePath, String property) {
		try {
			return getPropertyValue(propFilePath, property);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public PropertiesReader() {
	}

	private synchronized String getPropertyValue(String property) throws IOException {
		String prop = null;
		try {
			Properties props = new Properties();
			String propFileName = "props.properties";

			inputStream = new FileInputStream(System.getProperty("user.dir").concat(File.separator).concat("resources")
					.concat(File.separator).concat(propFileName));

			if (inputStream != null) {
				props.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			prop = props.getProperty(property);

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}

		return prop;
	}

	private synchronized String getPropertyValue(String propFilePath, String property) throws IOException {
		String prop = null;
		try {
			Properties props = new Properties();
			String propFileName = "config.properties";

			inputStream = new FileInputStream(propFilePath.concat(propFileName));

			if (inputStream != null) {
				props.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			prop = props.getProperty(property);

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return prop;
	}
}
