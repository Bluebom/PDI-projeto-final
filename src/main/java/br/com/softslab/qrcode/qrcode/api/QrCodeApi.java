package br.com.softslab.qrcode.qrcode.api;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
public class QrCodeApi {
    @GetMapping("/{productId}")
    public ResponseEntity<FileSystemResource> getQRCodeImage(@PathVariable("productId") String productId) {
        FileSystemResource imageFile = new FileSystemResource("qrcodes/"+productId+".png");

        // Return the image as a response
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                .body(imageFile);
    }
}
