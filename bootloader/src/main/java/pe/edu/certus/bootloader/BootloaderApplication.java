package pe.edu.certus.bootloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication( scanBasePackages = "pe.edu.certus")
public class BootloaderApplication {

    public static void main( String[] args ) {
        SpringApplication.run( BootloaderApplication.class, args );
    }

}
