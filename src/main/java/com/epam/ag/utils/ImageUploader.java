package com.epam.ag.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Part;
import java.io.*;

/**
 * @author Govorov Andrey
 */
public class ImageUploader {

    private static final Logger log = LoggerFactory.getLogger(ImageUploader.class);

    // http://docs.oracle.com/javaee/6/tutorial/doc/glraq.html
    public String fromRequest(Part filePart, String path) {
        final String fileName = getFileName(filePart);
        OutputStream out = null;
        InputStream imageContent = null;
        try {
            out = new FileOutputStream(new File(path + File.separator + fileName));
            imageContent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = imageContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            log.trace("File{} being uploaded to {}", fileName, path);
        } catch (IOException e) {
            log.error("Unable to upload gallery", e);
        } finally {
            try {
                if (out != null) out.close();
                if (imageContent != null) imageContent.close();
            } catch (IOException e) {
                log.warn("Unable to close resource {}", e);
            }
        }

        return path + fileName;
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        log.trace("Part Header = {}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
