package pl.lukk.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import pl.lukk.entity.User;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService
{
    /**
     * Create directories where files will be saved.
     * 
     */
    void init();

    /**
     * Copy file from input stream to directories specified in init method.
     * 
     * @param file      File which will be copy.
     */
    void store(MultipartFile file);

    
    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    /**
     * Upload photo which will be given user's profile photo.
     * 
     * @param file      Photo to upload.
     * @param user      User which profile photo will be changed.
     */
    void storeProfilePhoto(MultipartFile file, User user);
}
