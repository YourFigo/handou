package com.handou.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Figo
 * @Date 2020/4/2 12:38
 */
@Service
public class UploadService {

    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif", "image/png",
            "image/bmp", "image/webp", "image/x-icon", "image/vnd.microsoft.icon");

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    @Autowired
    private FastFileStorageClient storageClient;

    /**
     * 图片上传的业务逻辑
     * @param file
     * @return
     */
    public String uploadImage(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        // 校验文件类型
        String contentType = file.getContentType();
        if (!CONTENT_TYPES.contains(contentType)){
            LOGGER.info("文件类型不合法：{}", originalFilename);
            return null;
        }

        try {
            // 检验文件内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null || bufferedImage.getHeight() == 0 || bufferedImage.getWidth() == 0){
                LOGGER.info("文件内容不合法：{}", originalFilename);
                return null;
            }

            // 保存到文件服务器
            String ext = StringUtils.substringAfterLast(originalFilename, ".");
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);
            /*
            // 一开始保存在本地
            // D:\\1_Code\\java\\handou\\handou-upload\\upload\\image
            // String directory = new File("").getAbsolutePath() + "\\handou-upload\\upload\\image\\";
            // file.transferTo(new File(directory + originalFilename));
             */

            // 返回url，进行回写
            // 由于我的图片服务器在腾讯云主机上，不能通过未备案的域名访问，得换成自己备案过的域名
            return "http://www.figogo.cn/" + storePath.getFullPath();
            /*
            // 开始存在本地
            // return "http://image.handou.com/" + originalFilename;
            // 回来换成图片服务器
            // return "http://image.handou.com/" + storePath.getFullPath();
             */

        } catch (IOException e) {
            LOGGER.info("服务器内部错误：{}", originalFilename);
            e.printStackTrace();
        }
        return null;
    }
}
