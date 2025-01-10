

import android.graphics.Bitmap;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.ByteArrayOutputStream;
import java.util.Map;

public class CloudinaryHelper {

    private static final String CLOUD_NAME = "your_cloud_name"; // Replace with your Cloudinary cloud name
    private static final String API_KEY = "your_api_key"; // Replace with your API key
    private static final String API_SECRET = "your_api_secret"; // Replace with your API secret

    private static Cloudinary cloudinary;

    static {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUD_NAME,
                "api_key", API_KEY,
                "api_secret", API_SECRET));
    }

    public static String uploadImage(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            Map uploadResult = cloudinary.uploader().upload(byteArray, ObjectUtils.emptyMap());
            return (String) uploadResult.get("secure_url"); // Returning the URL of the uploaded image
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
