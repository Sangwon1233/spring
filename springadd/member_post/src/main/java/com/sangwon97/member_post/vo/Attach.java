package com.sangwon97.member_post.vo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@Log4j2
@Data
@Builder
@Alias("attach")
@AllArgsConstructor
@NoArgsConstructor
public class Attach {
	private String uuid; //아이디
	private String origin; 
	private String path; //경로(날짜별)
	private boolean image;
	private Long pno;
	
	// @Value("")
	public static String UPLOAD_PATH = "c:/upload";
	
	public Attach(MultipartFile file){
		String origin = file.getOriginalFilename();
		int dotIdx= origin.lastIndexOf(".");
		String ext= "";
		if(dotIdx !=-1){
			ext = origin.substring(dotIdx);
		}
		log.info(UPLOAD_PATH);
		uuid = UUID.randomUUID().toString();
		String realName = uuid + ext;
		path = getTodayStr();
		File parenPath = new File(UPLOAD_PATH,path);
		if(!parenPath.exists()){
			parenPath.mkdirs();
		}
		try{
			File f = new File(parenPath,realName);
			file.transferTo(f);
			Files.probeContentType(f.toPath());
		//마임타입 체크
		String mime = Files.probeContentType(f.toPath());
		image = mime != null && mime.startsWith("image");

	
		//thumbnailtor
		//new
		if(image){
			File thumb = new File(parenPath,"t" + realName);
			Thumbnailator.createThumbnail(file.getInputStream(),new FileOutputStream(thumb),100,100);
		}
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	public File toFile(){
		return new File(new File(UPLOAD_PATH,path),uuid);
	}
	public String getTodayStr() {
    return new SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis());
    }
}