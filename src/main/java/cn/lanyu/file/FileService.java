package cn.lanyu.file;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	List<String> upload(MultipartFile multipartFile, String path);
}
