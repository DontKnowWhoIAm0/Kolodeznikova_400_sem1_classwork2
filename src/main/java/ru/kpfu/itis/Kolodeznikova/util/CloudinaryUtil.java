package ru.kpfu.itis.Kolodeznikova.util;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtil {

    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {

        if (cloudinary == null) {
            Map<String, String> config = new HashMap<>();
            config.put("cloud_name", "dhprf58b7");
            config.put("api_key", "947665791614985");
            config.put("api_secret", "Htr7Q7jorRClW0b8ajYjI437cvo");
            cloudinary = new Cloudinary(config);
        }

        return cloudinary;
    }

}
