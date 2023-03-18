package ch5_spring_security_in_action.p401_methods_permissions.security;

import ch5_spring_security_in_action.p401_methods_permissions.model.Document;
import ch5_spring_security_in_action.p401_methods_permissions.model.DocumentInfo;
import ch5_spring_security_in_action.p401_methods_permissions.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;

@Component
public class DocumentsPermissionEvaluator implements PermissionEvaluator {

    // если permission == ROLE_admin, то конкретный юзер может получить только
    // документы с его именем, а admin - все документы
    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object target,
                                 Object permission) {
        Document document = (Document) target;
        String p = (String) permission;
        // пользователь с role которая передана в expression - всегда имеет доступ
        boolean permission_role_matches =
           authentication.getAuthorities()
           .stream()
           .anyMatch(a -> a.getAuthority().equals(p));
        // если есть специфичная role или owner документа равен текущему пользователю
        return permission_role_matches || document.getOwner().equals(authentication.getName());
    }

    @Autowired
    private DocumentRepository documentRepository;

    private static final Set<String> allowedExtensions = Set.of("txt", "doc", "docx");

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        // сначала проверяем permissions
        String p = (String) permission;
        // пользователь с role которая передана в expression - всегда имеет доступ
        boolean permission_role_matches =
            authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(p));
        if (permission_role_matches) return true;
        // логика для "owner" аналогична другому методу hasPermission(Authentication, Object, Object)
        // логика для "extension" - не для 'admin' разрешены только расширения "txt", "doc", "docx"
        String code = targetId.toString();
        Document document = documentRepository.findDocument(code);
        switch (targetType) {
            case "extension": {
                if (document instanceof DocumentInfo) {
                    String extension = ((DocumentInfo) document).getExtension();
                    return allowedExtensions.contains(extension);
                } else {
                    return true;
                }
            }
            case "owner": {
                return document.getOwner().equals(authentication.getName());
            } default: {
                return false;
            }
        }
    }
}
