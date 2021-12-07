package com.example.bookstoreapi.service;

import com.example.bookstoreapi.entity.FavoriteItem;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

@Service("favoriteItemService")
public class FavoriteItemService {
    
    private static final String FAVORITEITEMS_COLLECTION = "favoriteItems";

    public List<FavoriteItem> getFavoriteItems(String userId) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        CollectionReference favoriteItems = DB.collection(FAVORITEITEMS_COLLECTION);
        Query query = favoriteItems.whereEqualTo("userId", userId);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<FavoriteItem> listFavoriteItem = new ArrayList<>();

        for (DocumentSnapshot snapshot : querySnapshot.get().getDocuments()) {
            DocumentReference docRef = favoriteItems.document(snapshot.getId());
            ApiFuture<DocumentSnapshot> docRefApi = docRef.get();
            DocumentSnapshot document = docRefApi.get();
            listFavoriteItem.add(document.toObject(FavoriteItem.class));
        }

        return listFavoriteItem;
    }

    public String createFavoriteItem(FavoriteItem favoriteItem) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApi = DB.collection(FAVORITEITEMS_COLLECTION).document().set(favoriteItem);
        return "Successfully created at " + collectionApi.get().getUpdateTime().toString();
    }

    public String deleteFavoriteItem(String userId, String productId) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        CollectionReference favoriteItems = DB.collection(FAVORITEITEMS_COLLECTION);
        Query query = favoriteItems.whereEqualTo("productId", productId).whereEqualTo("userId", userId);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot snapshot : querySnapshot.get().getDocuments()) {
            ApiFuture<WriteResult> future = favoriteItems.document(snapshot.getId()).delete();
            return "Successfully deleted at " + future.get().getUpdateTime().toString();
        }
        
        return "Failed to delete";
    }

}
