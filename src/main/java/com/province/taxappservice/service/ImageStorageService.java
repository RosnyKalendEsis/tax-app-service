package com.province.taxappservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageStorageService {

    private final String uploadDir = "./signatures";

    public String save(MultipartFile file, String directory) {
        if (file.isEmpty()) {
            throw new RuntimeException("Le fichier est vide.");
        }

        try {
            // Définir le chemin complet avec ou sans sous-dossier
            Path targetDir = (directory == null || directory.trim().isEmpty())
                    ? Paths.get(uploadDir)
                    : Paths.get(uploadDir, directory);

            // Créer le dossier s’il n’existe pas
            Files.createDirectories(targetDir);

            // Générer un nom de fichier unique
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = targetDir.resolve(fileName);

            // Copier le fichier
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Retourne le chemin relatif à partir d'uploadDir
            return targetDir.getFileName() + "/" + fileName;

        } catch (IOException e) {
            throw new RuntimeException("Erreur de sauvegarde du fichier : " + e.getMessage());
        }
    }
}


