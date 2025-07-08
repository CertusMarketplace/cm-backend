<<<<<<< Updated upstream
//package pe.edu.certus.bootloader.config;
//
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@ComponentScan(basePackages = {
//        "pe.edu.certus.authmodule",
//        "pe.edu.certus.peoplemodule",
//        "pe.edu.certus.usersmodule",
//        "pe.edu.certus.worksmodule",
//        "pe.edu.certus.paypalmodule",
//        "pe.edu.certus.uimodule"})
//public class BootComponentScan {
//}
=======
package pe.edu.certus.bootloader.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "pe.edu.certus.authmodule",
        "pe.edu.certus.peoplemodule",
        "pe.edu.certus.ratingsmodule",
        "pe.edu.certus.usersmodule",
        "pe.edu.certus.worksmodule",
        "pe.edu.certus.paypalmodule",
        "pe.edu.certus.uimodule",
})
public class BootComponentScan {
}
>>>>>>> Stashed changes
