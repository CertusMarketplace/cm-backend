package pe.edu.certus.uimodule.ui.pages.works.adapters.drivers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.certus.uimodule.ui.config.StorageConfig;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driver.ForWork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping( "/marketplace/dashboard/seller/works" )
@SuppressWarnings( "SpringJavaInjectionPointsAutowiringInspection" )
public class WorkController {

    private final ForWork forWork;
    private final StorageConfig storageConfig;

    public WorkController( ForWork forWork, StorageConfig storageConfig ) {
        this.forWork = forWork;
        this.storageConfig = storageConfig;
    }

    @PostMapping( "/create" )
    public String createWork( @ModelAttribute( "newWork" ) WorkModel workModel,
                              @RequestParam( "imageFiles" ) List< MultipartFile > imageFiles,
                              @RequestParam( "projectFile" ) MultipartFile projectFile,
                              Authentication authentication,
                              RedirectAttributes redirectAttributes ) {

        if ( authentication == null ) {
            redirectAttributes.addFlashAttribute( "errorMessage", "Error de autenticación. Por favor, inicia sesión de nuevo." );
            return "redirect:/marketplace/auth/login";
        }

        if ( imageFiles.isEmpty( ) || imageFiles.get( 0 ).isEmpty( ) ) {
            redirectAttributes.addFlashAttribute( "errorMessage", "Debes subir al menos una imagen para el trabajo." );
            return "redirect:/marketplace/dashboard/seller";
        }

        if ( projectFile.isEmpty( ) ) {
            redirectAttributes.addFlashAttribute( "errorMessage", "Debes subir el archivo del proyecto." );
            return "redirect:/marketplace/dashboard/seller";
        }

        try {
            Long sellerId = Long.parseLong( authentication.getName( ) );
            workModel.setIdSellerUser( sellerId );

            List< String > imageUrls = new ArrayList<>( );
            for ( MultipartFile file : imageFiles ) {
                if ( !file.isEmpty( ) ) {
                    imageUrls.add( storageConfig.storeFile( file, "image" ) );
                }
            }

            if ( !imageUrls.isEmpty( ) ) {
                workModel.setWorkImageUrl( imageUrls.get( 0 ) );
                workModel.setImageUrls( imageUrls );
            }

            String projectPath = storageConfig.storeFile( projectFile, "project" );
            workModel.setWorkFilePath( projectPath );
            workModel.setWorkStatus( WorkModel.WorkStatus.EN_REVISION );

            forWork.createWork( workModel );
            redirectAttributes.addFlashAttribute( "successMessage", "¡Trabajo enviado a revisión con éxito!" );

        } catch ( IOException e ) {
            e.printStackTrace( );
            redirectAttributes.addFlashAttribute( "errorMessage", "Error al subir los archivos: " + e.getMessage( ) );
        } catch ( Exception e ) {
            e.printStackTrace( );
            redirectAttributes.addFlashAttribute( "errorMessage", "Error inesperado al crear el trabajo: " + e.getMessage( ) );
        }

        return "redirect:/marketplace/dashboard/seller";
    }
}