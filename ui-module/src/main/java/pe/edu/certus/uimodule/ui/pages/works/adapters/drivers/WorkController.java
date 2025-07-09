package pe.edu.certus.uimodule.ui.pages.works.adapters.drivers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.certus.uimodule.ui.config.StorageConfig;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driver.ForWork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/marketplace/dashboard/seller/works")
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class WorkController {

    private final ForWork forWork;
    private final StorageConfig storageConfig;

    public WorkController(ForWork forWork, StorageConfig storageConfig) {
        this.forWork = forWork;
        this.storageConfig = storageConfig;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createWork(@ModelAttribute("newWork") WorkModel workModel,
                                        @RequestParam("imageFiles") List<MultipartFile> imageFiles,
                                        @RequestParam("projectFile") MultipartFile projectFile,
                                        Authentication authentication) {

        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("errorMessage", "Error de autenticación. Por favor, inicia sesión de nuevo."));
        }

        if (imageFiles.isEmpty() || imageFiles.get(0).isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("errorMessage", "Debes subir al menos una imagen para el trabajo."));
        }

        if (projectFile.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("errorMessage", "Debes subir el archivo del proyecto."));
        }

        try {
            Long sellerId = Long.parseLong(authentication.getName());
            workModel.setIdSellerUser(sellerId);

            List<String> imageUrls = new ArrayList<>();
            for (MultipartFile file : imageFiles) {
                if (!file.isEmpty()) {
                    imageUrls.add(storageConfig.storeFile(file, "image"));
                }
            }

            if (!imageUrls.isEmpty()) {
                workModel.setWorkImageUrl(imageUrls.get(0));
                workModel.setImageUrls(imageUrls);
            }

            String projectPath = storageConfig.storeFile(projectFile, "project");
            workModel.setWorkFilePath(projectPath);
            workModel.setWorkStatus(WorkModel.WorkStatus.PUBLICADO);

            forWork.createWork(workModel);

            return ResponseEntity.ok(Map.of("successMessage", "¡Trabajo publicado con éxito!"));

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("errorMessage", "Error al subir los archivos: " + e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("errorMessage", "Error inesperado al crear el trabajo: " + e.getMessage()));
        }
    }
}