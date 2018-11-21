package com.example.market.service;

import com.example.market.api.FileService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by degang on 2018/8/11
 */
@Service
public class FileServiceImpl implements FileService {
    @Value("${dataImageLocation}")
    private String imageLocation;

    @Override
    public String save(MultipartFile file, String day) {
        int fileNum = 1;
        String completeFileName = "";
        String imagePath = imageLocation +"/" + day;
        try {
            Path path = Paths.get(imagePath);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            } else {
                Path maxNumImage = Files.list(path).max(Comparator.comparing(Path::getFileName)).orElse(null);
                if (maxNumImage != null) {
                    fileNum = extractNumber(maxNumImage.getFileName().toString());
                    fileNum++;
                }

                completeFileName = String.valueOf(fileNum)
                        + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

                BufferedImage image = ImageIO.read(file.getInputStream());
                Thumbnails.of(image)
                        //.size(image.getWidth() / 4, image.getHeight() / 4)
                        .scale(0.2f)
                        .toFile(new File(path.toAbsolutePath()
                                + "/"
                                + completeFileName));
                /*file.transferTo(new File(path.toAbsolutePath()
                        + "/"
                        + completeFileName));*/
            }

            return completeFileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file ", e);
        }
    }

    private Integer extractNumber(String s) {
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(s);
        return Integer.valueOf(m.replaceAll("").trim());

    }
}
