import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.people.User;
import com.flickr4java.flickr.photos.*;
import com.flickr4java.flickr.tags.Tag;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.testng.Assert.*;

public class UploadFileTest {

    private static final String resourcesPath = System.getProperty("user.dir").concat(File.separator).concat("resources").concat(File.separator);
    private PropertiesReader propsReader;
    private PhotosInterface photos;
    private Flickr flickr;
    private String photoID;
    private String unsupportedPhotoID;
    private Collection<Photocount> counts;
    private int previousPhotosCount = 0;

    @BeforeClass
    public void setup() {
        Log.info("*** Before classs setup method ***");

        // Get props reader
        propsReader = PropertiesReader.getInstance();

        // Authorize
        AuthorizeUser authorizeUser = new AuthorizeUser();
        assertTrue(authorizeUser.flickrAuthenticator());

        flickr = AuthorizeUser.getFlickrInstance();
        assertNotNull(flickr, "Didn't authorize user! Flickr instance is null.");

        photos = flickr.getPhotosInterface();
        assertNotNull(photos, "Can't get photos interface, it's null!");

        Log.info("*** Before classs setup method passed ***");
    }

    @Test
    public void photoUploadTest() throws InterruptedException {
        Log.info("*** Photo upload test started ***");

        previousPhotosCount = getCountOfPhotos();
        photoID = FlickrUploaderUtils.processFileAndUpload(resourcesPath.concat(propsReader.getProperty("jpgfile")));

        Log.info("Uploaded photo id is ".concat(photoID));
        assertNotNull(photoID, "Photo ID is null!");

        // Wait for photo to be refreshed on website
        Thread.sleep(15000);

        Log.info("*** Photo upload test passed ***");
    }

    @Test(dependsOnMethods = "photoUploadTest")
    public void uploadedPhotoSearchTest() {
        Log.info("*** Photo search test started ***");

        boolean isPhotoFound = false;
        AtomicBoolean isEqual = new AtomicBoolean(false);
        String photoTitle = propsReader.getProperty("jpgfile").split("\\.")[0];

        try {
            SearchParameters params = new SearchParameters();
            params.setMedia("all"); // One of "photos", "videos" or "all"
            params.setExtras(Stream.of("media").collect(Collectors.toSet()));
            params.setText(photoTitle);
            params.setUserId(propsReader.getProperty("userid"));

            PhotoList<Photo> results = photos.search(params, 50, 0);

            for (Photo p : results) {
                Log.info("Searching photo ".concat(p.getId()));
                if (p.getId().equals(photoID)) {
                    assertTrue(p.getTitle().contains(photoTitle));
                    isPhotoFound = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(isPhotoFound);

        Log.info("*** Photo search test passed ***");
    }


    @Test(dependsOnMethods = "photoUploadTest")
    public void photoCount() {
        Log.info("*** Photo count test started ***");

        assertEquals(getCountOfPhotos(), previousPhotosCount + 1, "Images count is wrong!");

        Log.info("*** Photo count test passed ***");
    }

    @Test(dependsOnMethods = "photoCount")
    public void unsupportedPhotoUpload() {
        Log.info("*** Unsupported photo upload test started ***");
        Photo photo = null;

        try {
            unsupportedPhotoID = FlickrUploaderUtils.processFileAndUpload(resourcesPath.concat(propsReader.getProperty("bmpfile")));
            Log.info("Unsupported photo id is: ".concat(unsupportedPhotoID));

            // Photo converted to jpg
            assertNotNull(unsupportedPhotoID, "Photo ID is null!");

            photo = photos.getPhoto(unsupportedPhotoID, null);
            assertNotNull(photo);
            assertEquals(photo.getOriginalFormat(), "jpg", "Photo is not converted from bmp to jpg!");
            Log.info("Media: ".concat(photo.getOriginalFormat()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.info("*** Unsupported photo upload test passed ***");
    }

    @Test
    public void invalidPhotoExtensionUploadTest() throws InterruptedException {
        Log.info("*** Invalid photo file upload test started ***");

        try {
            String response = FlickrUploaderUtils.processFileAndUpload(resourcesPath.concat(propsReader.getProperty("pdffile")));
            assertNull(response, "Photo ID is not null!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.info("*** Invalid photo upload test passed ***");
    }

    @Test(dependsOnMethods = "photoUploadTest")
    public void uploadedPhotoInformationTest() {
        Log.info("*** Photo info test started ***");

        try {
            Photo photo = photos.getInfo(photoID, null);
            assertNotNull(photo);
            assertNotNull(photo.getUrl());
            assertNotNull(photo.getTitle());
            assertEquals(photoID, photo.getId());
            assertEquals(photo.getOriginalFormat(), "jpg");

            User owner = photo.getOwner();
            assertEquals(propsReader.getProperty("userid"), owner.getId());
            assertEquals(propsReader.getProperty("username"), owner.getUsername());

            List<Tag> tags = (List<Tag>) photo.getTags();
            assertTrue(tags.get(0).getValue().contains(propsReader.getProperty("jpgfile").split("\\.")[0]));
            assertEquals(photo.getOriginalFormat(), "jpg", "Photo is not converted from bmp to jpg!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.info("*** Photo info test passed ***");
    }

    private int getCountOfPhotos() {
        try {
            Date[] dates = new Date[2];
            dates[0] = new Date(100000);
            dates[1] = new Date(); // now

            counts = photos.getCounts(dates, null);
            assertNotNull(counts);

            return counts.iterator().next().getCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}