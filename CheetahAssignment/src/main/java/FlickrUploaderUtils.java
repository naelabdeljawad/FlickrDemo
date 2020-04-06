import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.flickr4java.flickr.uploader.Uploader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FlickrUploaderUtils {

    private static String basefilename;

    /**
     * Load file to upload
     *
     * @param filename
     * @return
     */
    public static String processFileAndUpload(String filename) {
        try {
            if (filename.equals("")) {
                System.out.println("filename must be entered for uploadfile");
                return null;
            }
            if (filename.lastIndexOf(File.separatorChar) > 0)
                basefilename = filename.substring(filename.lastIndexOf(File.separatorChar) + 1);
            else
                basefilename = filename;

            File f = new File(filename);
            if (!f.exists() || !f.canRead()) {
                System.out.println(" File: " + filename + " cannot be processed, does not exist or is unreadable.");
                return null;
            }
            return uploadFile(filename, AuthorizeUser.getFlickrInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Upload file after being loaded by uploader
     *
     * @param filename
     * @param flickr
     * @return
     */
    private static String uploadFile(String filename, Flickr flickr) {
        String photoId = null;
        String title;
        List<String> tags = new ArrayList<>();

        UploadMetaData metaData = new UploadMetaData();
        metaData.setFamilyFlag(true);
        metaData.setDescription("minions for cheetah task");//TODO  Hardcoded for the task only, must be changed.

        if (basefilename == null || basefilename.equals(""))
            basefilename = filename;

        if (basefilename.lastIndexOf('.') > 0) {
            title = basefilename.substring(0, basefilename.lastIndexOf('.')).concat("fornael");//TODO  Hardcoded for the task only, must be changed.
            metaData.setTitle(title);
        }

        tags.add("OrigFileName='" + basefilename + "'");
        metaData.setTags(tags);
        Uploader uploader = flickr.getUploader();

        try {
            metaData.setFilename(basefilename);
            File f = new File(filename);
            photoId = uploader.upload(f, metaData);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Calling uploader is finished!");
        }
        return photoId;
    }
}



