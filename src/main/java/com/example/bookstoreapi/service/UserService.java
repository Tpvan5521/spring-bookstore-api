package com.example.bookstoreapi.service;

import com.example.bookstoreapi.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    private static final String USERS_COLLECTION = "users";

    public String getUserId(String UID) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        CollectionReference users = DB.collection(USERS_COLLECTION);
        Query query = users.whereEqualTo("uid", UID);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot snapshot : querySnapshot.get().getDocuments()) {
            return snapshot.getId();
        }

        return "";
    }

    public User getUserByDocId(String userId) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        DocumentReference docRef = DB.collection(USERS_COLLECTION).document(userId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        User user = null;
        
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            user = document.toObject(User.class);
        }
        
        return user;
    }

    public User getUser(String UID) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        CollectionReference users = DB.collection(USERS_COLLECTION);
        Query query = users.whereEqualTo("uid", UID);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        User user = null;

        for (DocumentSnapshot snapshot : querySnapshot.get().getDocuments()) {
            DocumentReference docRef = users.document(snapshot.getId());
            ApiFuture<DocumentSnapshot> docRefApi = docRef.get();
            DocumentSnapshot document = docRefApi.get();
            user = document.toObject(User.class);
        }

        return user;
    }

    public String createUser(User user) throws InterruptedException, ExecutionException {
        User existUser = getUser(user.getUID());
        if (existUser == null) {
            Firestore DB = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> future = DB.collection(USERS_COLLECTION).document().set(user);
            return future.get().getUpdateTime().toString();
        }
        return "";
    }

    public String updateUser(String UID, User user) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        CollectionReference users = DB.collection(USERS_COLLECTION);
        Query query = users.whereEqualTo("uid", UID);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot snapshot : querySnapshot.get().getDocuments()) {
            ApiFuture<WriteResult> future = DB.collection(USERS_COLLECTION).document(snapshot.getId()).set(user);
            return "Successfully updated at " + future.get().getUpdateTime().toString();
        }

        return "Failed to update";
    }

}
