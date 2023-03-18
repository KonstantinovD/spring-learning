package ch5_spring_security_in_action.p401_methods_permissions.controllers;

import ch5_spring_security_in_action.p401_methods_permissions.model.Document;
import ch5_spring_security_in_action.p401_methods_permissions.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/documents/{code}")
    public Document getDetails(@PathVariable String code) {
        return documentService.getDocument(code);
    }

    @GetMapping("/documents/owner/{code}")
    public String getOwner(@PathVariable String code) {
        return documentService.getDocumentOwner(code);
    }

    @GetMapping("/documents/extension/{code}")
    public String getExtension(@PathVariable String code) {
        return documentService.getDocumentExtension(code);
    }
}
