package pe.edu.certus.uimodule.ui.pages.works.ports.drivers;

import org.springframework.ui.Model;

public interface ForWorkRoute {
    String showWorksPage( Model model, String category, String priceRange, Integer popularity, int page, int size );

    String showWorkDetailPage( Long workId, Model model );
}