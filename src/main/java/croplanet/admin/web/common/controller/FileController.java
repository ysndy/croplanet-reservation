package croplanet.admin.web.common.controller;

import croplanet.admin.web.common.util.FileManager;
import croplanet.admin.web.common.util.UploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.UUID;

@Controller
@RequestMapping("/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileManager fileManager;

    @ResponseBody
    @PostMapping("/images")
    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile file) throws IOException {

        if(!file.isEmpty()) {
            log.info("contentType = {}", file.getContentType());
            String fullPath = fileManager.fileDir + UUID.randomUUID();

            if(file.getContentType().equals("image/jpeg")){
                fullPath+=".jpg";
            }else if(file.getContentType().equals("image/png")){
                fullPath+=".png";
            }else {
                throw new IOException("이미지 형식 오류");
            }

            log.info("파일 저장 fullPath={}", fullPath);
            file.transferTo(new File(fullPath));

            String fileUri = fullPath.replace(fileManager.fileDir, "");
            return new ResponseEntity<>(fileUri, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource readImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:"+fileManager.getFullPath(filename));
    }

}
