package ua.training.quotes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("download")
@RequiredArgsConstructor
public class DownloadController {
    @GetMapping(value = "template.xml", produces = "application/octet-stream")
    public byte[] getTemplate() {
        try(InputStream is = new ClassPathResource("quotes_template.xml").getInputStream()) {
            return is.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
