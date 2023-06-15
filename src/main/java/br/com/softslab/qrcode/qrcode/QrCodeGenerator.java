package br.com.softslab.qrcode.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QrCodeGenerator {
    public QrCodeGenerator(String barcodeContent) {
        new QrCodeGenerator(barcodeContent, "png");
    }

    QrCodeGenerator(String barcodeId, String format)
    {
        int width = 900;
        int height = 900;

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        QRCodeWriter writer = new QRCodeWriter();
        try {
            String content = "http://54.161.160.66:8080/produtos/"+barcodeId;
            BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            String barcodePath = "qrcodes/"+barcodeId+"."+format;
            Path path = FileSystems.getDefault().getPath(barcodePath);
            MatrixToImageWriter.writeToPath(matrix, format.toUpperCase(), path);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
