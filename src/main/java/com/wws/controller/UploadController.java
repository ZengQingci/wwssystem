package com.wws.controller;

import com.wws.vo.PictureVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class UploadController {
    @RequestMapping("/inupload")
    @ResponseBody
    public PictureVO inUpload(@RequestParam MultipartFile file){
        PictureVO pictureVO = new PictureVO();
        String picName = inUploads(file);
        pictureVO.setCode(0);
        pictureVO.setMsg("ok");
        pictureVO.setData(picName);
        return pictureVO;
    }
    public String inUploads(MultipartFile file){
        String str = "";
        try {
            //获取图片名称
            String fileName = file.getOriginalFilename();
            //截取后缀jpg||png
            String sufName = fileName.substring(fileName.lastIndexOf("."));
            //生成一个随机数作为前缀
            String preName = UUID.randomUUID().toString();
            //重新拼接一个新的名称
            String newName = preName + sufName;
            File f = new File("E:\\image\\" + newName);
            file.transferTo(f);
            str = newName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }
}
