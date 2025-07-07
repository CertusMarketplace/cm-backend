package pe.edu.certus.uimodule.ui.config;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class StorageConfig {

    private final Path imageStorageLocation = Paths.get( "ui-module/src/main/resources/static/img/works/uploads" );
    private final Path projectStorageLocation = Paths.get( "ui-module/src/main/resources/static/projects" );

    public StorageConfig( ) throws IOException {
        Files.createDirectories( imageStorageLocation );
        Files.createDirectories( projectStorageLocation );
    }

    public String storeFile( MultipartFile file, String type ) throws IOException {
        if ( file.isEmpty( ) ) {
            throw new IOException( "Failed to store empty file." );
        }

        // Generar un nombre de archivo único para evitar colisiones
        String originalFilename = file.getOriginalFilename( );
        String extension = "";
        if ( originalFilename != null && originalFilename.contains( "." ) ) {
            extension = originalFilename.substring( originalFilename.lastIndexOf( "." ) );
        }
        String uniqueFilename = UUID.randomUUID( ).toString( ) + extension;

        Path targetLocation;
        String webPath;

        if ( "image".equals( type ) ) {
            targetLocation = this.imageStorageLocation.resolve( uniqueFilename );
            webPath = "/img/works/uploads/" + uniqueFilename;
        } else if ( "project".equals( type ) ) {
            targetLocation = this.projectStorageLocation.resolve( uniqueFilename );
            webPath = "/projects/" + uniqueFilename;
        } else {
            throw new IllegalArgumentException( "Invalid file type specified." );
        }

        Files.copy( file.getInputStream( ), targetLocation );

        return webPath;
    }
}