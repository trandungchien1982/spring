package com.example.demo;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class MainController {

  private static final int DEFAULT_IMAGE_SIZE = 200;
  private static final int MIN_IMAGE_SIZE = 50;
  private static final int MAX_IMAGE_SIZE = 2000;

  @GetMapping(
          value = "/test-barcode",
          produces = MediaType.IMAGE_PNG_VALUE
  )
  public ResponseEntity<byte[]> generateQrCode(
          @RequestParam("code") String code,
          @RequestParam(
                  value = "imageSize",
                  defaultValue = "200"
          ) int imageSize
  ) throws WriterException, IOException {

    validateInput(code, imageSize);

    Map<EncodeHintType, Object> hints = Map.of(
            EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name(),
            EncodeHintType.MARGIN, 1
    );

    BitMatrix matrix = new MultiFormatWriter().encode(
            code,
            BarcodeFormat.QR_CODE,
            imageSize,
            imageSize,
            hints
    );

    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

      MatrixToImageWriter.writeToStream(
              matrix,
              "PNG",
              outputStream
      );

      return ResponseEntity.ok()
              .contentType(MediaType.IMAGE_PNG)
              .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
              .body(outputStream.toByteArray());
    }
  }

  private void validateInput(String code, int imageSize) {

    if (code == null || code.isBlank()) {
      throw new IllegalArgumentException(
              "Parameter 'code' must not be empty."
      );
    }

    if (imageSize < MIN_IMAGE_SIZE || imageSize > MAX_IMAGE_SIZE) {
      throw new IllegalArgumentException(
              "Parameter 'imageSize' must be between "
                      + MIN_IMAGE_SIZE
                      + " and "
                      + MAX_IMAGE_SIZE
                      + "."
      );
    }
  }
}
