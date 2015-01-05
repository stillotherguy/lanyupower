package cn.lanyu.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

@Service
public class FileServiceImpl implements FileService {
	//@Autowired
	//private StorageService storageService;

	private static final String UPLOAD_DIR_NAME = "temp";

	// 为了达到“不可变”，引用和值都要不可变
	private static final ImmutableList<String> suffix = ImmutableList.<String> of(".gif", ".jpg", ".jpeg", ".png",
			".bmp");
	private static final ImmutableList<String> fileExts = ImmutableList.<String> of(".rar", ".doc", ".docx", ".zip",
			".pdf", ".txt", ".swf", ".wmv", ".avi", ".rm", ".rmvb",
			".mpeg", ".mpg", ".ogg", ".mov", ".wmv", ".mp4");

	// 上传图片逻辑
	@Override
	public List<String> upload(MultipartFile multipartFile, String path) {
		String filename = multipartFile.getOriginalFilename();
		String[] temp = filename.split("\\.");
		String fileExt = "." + temp[temp.length - 1];
		if (!suffix.contains(fileExt) && !fileExts.contains(fileExt))
		{
			return Lists.newArrayList("type");
		}
		if (multipartFile.getSize() > (1024 * 1024 * 5))
		{
			return Lists.newArrayList("size");
		}
		path = path + File.separator + UPLOAD_DIR_NAME;
		File uploadDir = new File(path);
		try
		{
			/*
			 * SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			 * String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
			 * File file = new File(path + "/" + newFileName);
			 * FileOutputStream fos = new FileOutputStream(file);
			 * fos.write(multipartFile.getBytes());
			 * fos.flush();
			 * fos.close();
			 * return newFileName;
			 */
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + fileExt;
			File dest = new File(path + File.separator + newFileName);
			multipartFile.transferTo(dest);
			// Assert.notNull(dest, "multipart转换为file后，file不能为null");
			// dest.delete();
			// Path p = Paths.get(path + File.separator + newFileName);
			//String url = storageService.saveFile(dest, newFileName, dest.length());
			//Assert.notNull(url);
			//Files.deleteIfExists(dest.toPath());
			String url = "";
			return Lists.newArrayList("success", url, newFileName, fileExt);
		} catch (Exception e)
		{
			e.printStackTrace();
			return Lists.newArrayList("exception");
		}
	}

}
