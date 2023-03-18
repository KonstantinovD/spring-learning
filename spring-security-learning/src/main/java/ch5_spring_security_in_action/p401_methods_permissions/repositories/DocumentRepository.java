package ch5_spring_security_in_action.p401_methods_permissions.repositories;

import ch5_spring_security_in_action.p401_methods_permissions.model.Document;
import ch5_spring_security_in_action.p401_methods_permissions.model.DocumentInfo;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class DocumentRepository {

    private Map<String, Document> documents =
            Map.of("abc123", new Document("abc123", "emma"),
                "qwe123", new Document("qwe123", "emma"),
                "asd555", new Document("asd555", "natalie"),
                "a123", new DocumentInfo("a123", "natalie", "doc"),
                "b456", new DocumentInfo("b456", "natalie", "pdf"),
                "c789", new DocumentInfo("c789", "emma", "docx")
            );

    public Document findDocument(String code) {
        return documents.get(code);
    }
}
