package br.com.stecnology.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.stecnology.config.FileStorageConfig;
import br.com.stecnology.errorConstants.ErrorsConstats;
import br.com.stecnology.exception.FileStorageExecption;
import br.com.stecnology.exception.MyFileNotFoundStorageExecption;
@Service
public class FileStorageService {

	private final Path fileStorageLocation;
	
	@Autowired
	public FileStorageService(FileStorageConfig fileStorageConfig) {
		
		this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageExecption(ErrorsConstats.ERROR_DIRECTORY_NOT_FOUND, e);
		}
	}

	public String storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if(fileName.contains("..")) {
				throw new FileStorageExecption(ErrorsConstats.ERROR_INVALID_FILE + fileName);
			}
			
			//Linhas que salva no disco a imagens, mudar aqui para banco e nuvens
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return fileName;
		} catch (Exception e) {
			throw new FileStorageExecption(ErrorsConstats.ERROR_NOT_STORE_FILE + fileName + ErrorsConstats.ERROR_TRY_AGAIN, e);
		}
	}
	
	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName);
			Resource resources = new UrlResource(filePath.toUri());
			if(resources.exists()) {
				return resources;
			} else {
				throw new MyFileNotFoundStorageExecption(ErrorsConstats.ERROR_TRY_AGAIN + fileName);
			}
		} catch (Exception e) {
			throw new MyFileNotFoundStorageExecption(ErrorsConstats.ERROR_TRY_AGAIN + fileName, e);
		}
	}
}










