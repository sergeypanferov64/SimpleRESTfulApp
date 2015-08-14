package ru.spanferov.restserver.controllers;

import java.awt.image.BufferedImage;
import ru.spanferov.restserver.messages.UploadFileResponse;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileUploadController {
    
    @Value("${app.save-file-dir}")
    private String serverDir;

    @RequestMapping(method = RequestMethod.GET, value = "{fileName}", produces = "image/jpg")
    public @ResponseBody byte[] getFile(@PathVariable String fileName) {
        try {

            InputStream is = new FileInputStream(serverDir + fileName);
            BufferedImage img = ImageIO.read(is);
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", bao);

            return bao.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {

                if (!file.getContentType().equals("image/jpeg")) {
                    return new ResponseEntity<UploadFileResponse>(HttpStatus.BAD_REQUEST);
                }

                byte[] bytes = file.getBytes();
                File imgFile = new File(serverDir + Integer.toString(file.hashCode()));
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(imgFile));
                stream.write(bytes);
                stream.close();

                UploadFileResponse ufp = new UploadFileResponse(imgFile.getName());

                return new ResponseEntity<UploadFileResponse>(ufp, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<UploadFileResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<UploadFileResponse>(HttpStatus.BAD_REQUEST);
        }
    }

}
