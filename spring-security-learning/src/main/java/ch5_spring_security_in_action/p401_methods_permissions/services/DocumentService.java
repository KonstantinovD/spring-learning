package ch5_spring_security_in_action.p401_methods_permissions.services;

import ch5_spring_security_in_action.p401_methods_permissions.model.Document;
import ch5_spring_security_in_action.p401_methods_permissions.model.DocumentInfo;
import ch5_spring_security_in_action.p401_methods_permissions.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @PostAuthorize("hasPermission(returnObject, 'ROLE_admin')")
    public Document getDocument(String code) {
        return documentRepository.findDocument(code);
    }

    // мы хотим применять правила авторизации до выполнения метода
    @PreAuthorize("hasPermission(#code, 'owner', 'ROLE_admin')")
    public String getDocumentOwner(String code) {
        Document d = documentRepository.findDocument(code);
        if (d != null) {
            return d.getOwner();
        } else {
            return null;
        }
    }

    // мы хотим применять правила авторизации до выполнения метода
    @PreAuthorize("hasPermission(#code, 'extension', 'ROLE_admin')")
    public String getDocumentExtension(String code) {
        Document d = documentRepository.findDocument(code);
        if (d instanceof DocumentInfo) {
            return ((DocumentInfo) d).getExtension();
        } else {
            return null;
        }
    }
}
