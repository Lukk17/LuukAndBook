package pl.lukk.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import pl.lukk.entity.User;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService
{
    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    void storeProfilePhoto(MultipartFile file, User user);
}
