package com.epam.hrynyshyn.controllers.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.epam.hrynyshyn.constants.Constants.User.EMAIL;
import static com.epam.hrynyshyn.constants.Constants.User.IMAGE_MIME_TYPE;
import static com.epam.hrynyshyn.constants.Constants.Validation.AVATARS_EXTENSION;
import static com.epam.hrynyshyn.constants.Constants.Validation.AVATARS_STORAGE_PATH;

/**
 * Functionality for showing user's avatar in profile bar.
 */
@WebServlet("/receiveAvatar")
public class AvatarReceiveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute(EMAIL);
        File avatar = new File(AVATARS_STORAGE_PATH + email + AVATARS_EXTENSION);
        sendAvatar(avatar, resp);
    }

    private void sendAvatar(File avatar, HttpServletResponse response) throws IOException {
        response.setContentType(IMAGE_MIME_TYPE);
        try (OutputStream outputStream = response.getOutputStream();
             FileInputStream fileInputStream = new FileInputStream(avatar)) {
            byte[] buf = new byte[1024];
            int count;
            while ((count = fileInputStream.read(buf)) >= 0) {
                outputStream.write(buf, 0, count);
            }
        }
    }
}
