// package com.it332.edudeck.Repository;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import com.it332.edudeck.Entity.DocumentEntity;
// import com.it332.edudeck.Entity.UserEntity;

// @Repository
// public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {
//     List<DocumentEntity> findByIsDeletedFalse();
//     List<DocumentEntity> findByDocumentIDAndIsDeletedFalse(int documentID);
//     List<DocumentEntity> findByUserAndIsDeletedFalse(UserEntity user);
//     Optional<DocumentEntity> findByUser_UseridAndDocumentIDAndIsDeletedFalse(int userid, int documentID);
//     Optional<DocumentEntity> findByDocumentTitleAndIsDeletedFalse(String fileName);
// }


package com.it332.edudeck.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it332.edudeck.Entity.Document;
import com.it332.edudeck.Entity.User;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    List<Document> findByIsDeletedFalse();
    List<Document> findByDocumentIDAndIsDeletedFalse(int documentID);
    List<Document> findByUserAndIsDeletedFalse(User user);
    Optional<Document> findByUser_UseridAndDocumentIDAndIsDeletedFalse(int userid, int documentID);
}
