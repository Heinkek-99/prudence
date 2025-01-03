package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import dao.DocumentDAO;
import model.Document;

public class DocumentController {

    private static final Logger LOGGER = Logger.getLogger(DocumentController.class.getName());
    private final DocumentDAO documentDAO;

    public DocumentController(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    public void createDocument(int idStudent, String type, String path) throws SQLException {
        Document document = new Document();
        document.setIdStudent(idStudent);
        document.setType(type);
        document.setPath(path);
        documentDAO.create(document);
    }

    public List<Document> getAllDocument() throws SQLException {
        return documentDAO.readAll();
    }

    public Document getDocumentById(int id) throws SQLException {
        return documentDAO.read(id);
    }
}
