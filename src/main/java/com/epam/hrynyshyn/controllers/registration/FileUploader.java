package com.epam.hrynyshyn.controllers.registration;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

import static com.epam.hrynyshyn.constants.Constants.User.EMAIL;
import static com.epam.hrynyshyn.constants.Constants.User.USER_AVATAR;
import static com.epam.hrynyshyn.constants.Constants.Validation.*;

/**
 * Avatars uploading logic.
 */
public class FileUploader {
    private static Logger logger = Logger.getLogger(FileUploader.class);

    public static void uploadAvatar(HttpServletRequest request) throws IOException, ServletException {
        logger.info("uploading avatar");

        String email = request.getParameter(EMAIL);
        Part filePart = request.getPart(USER_AVATAR);
        File destinationFolder = new File(AVATARS_STORAGE_PATH);

        if (!destinationFolder.exists()) {
            destinationFolder.mkdir();
        }
        if (isInputDataCorrect(filePart, email)) {
            filePart.write(destinationFolder.getAbsolutePath() + File.separator + email + AVATARS_EXTENSION);
        }
        logger.info("upload successful");
    }

    private static boolean isInputDataCorrect(Part filePart, String email) {
        return filePart != null && email != null;
    }
}
