import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.auth.Permission;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuth1Token;

public class AuthorizeUser {
    public static Auth auth;
    private static Flickr flickr;

    /**
     * Get flickr instance - Singleton
     *
     * @return
     */
    public static synchronized Flickr getFlickrInstance() {
        if (flickr == null) {
            return new Flickr(PropertiesReader.getInstance().getProperty("apiKey"), PropertiesReader.getInstance().getProperty("secret"), new REST());
        }
        return flickr;
    }

    /**
     * Authenticate flickr user
     */
    public boolean flickrAuthenticator() {
        try {
            flickr = getFlickrInstance();
            AuthInterface authInterface = flickr.getAuthInterface();
            OAuth1RequestToken token = authInterface.getRequestToken();
            String url = authInterface.getAuthorizationUrl(token, Permission.DELETE);
            String verifier = new WebPagesObject().getVerifierFromChromeBrowser(url);
            System.out.println("Verifier: " + verifier);

            OAuth1Token accessToken = authInterface.getAccessToken(token, verifier);
            System.out.println("Authentication success");
            auth = authInterface.checkToken(accessToken);
            RequestContext.getRequestContext().setAuth(auth);
            // This token can be used until the user revokes it.
            System.out.println("Token: " + accessToken.getToken());
            System.out.println("Secret: " + accessToken.getTokenSecret());
            System.out.println("nsid: " + auth.getUser().getId());
            System.out.println("Realname: " + auth.getUser().getRealName());
            System.out.println("Username: " + auth.getUser().getUsername());
            System.out.println("Permission: " + auth.getPermission().getType());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


}
