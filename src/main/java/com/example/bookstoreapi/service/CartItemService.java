package com.example.bookstoreapi.service;

import com.example.bookstoreapi.entity.CartItem;
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

@Service("cartItemService")
public class CartItemService {

    private static final String CARTITEMS_COLLECTION = "cartItems";

    public List<CartItem> getCartItems(String userId) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        CollectionReference cartItems = DB.collection(CARTITEMS_COLLECTION);
        Query query = cartItems.whereEqualTo("userId", userId);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<CartItem> listCartItem = new ArrayList<>();

        for (DocumentSnapshot snapshot : querySnapshot.get().getDocuments()) {
            DocumentReference docRef = cartItems.document(snapshot.getId());
            ApiFuture<DocumentSnapshot> docRefApi = docRef.get();
            DocumentSnapshot document = docRefApi.get();
            listCartItem.add(document.toObject(CartItem.class));
        }

        return listCartItem;
    }

    public String createCartItem(CartItem cartItem) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApi = DB.collection(CARTITEMS_COLLECTION).document().set(cartItem);
        return "Successfully created at " + collectionApi.get().getUpdateTime().toString();
    }

    public String updateCartItem(String userId, String productId, CartItem cartItem) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        CollectionReference cartItems = DB.collection(CARTITEMS_COLLECTION);
        Query query = cartItems.whereEqualTo("productId", productId).whereEqualTo("userId", userId);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot snapshot : querySnapshot.get().getDocuments()) {
            ApiFuture<WriteResult> future = DB.collection(CARTITEMS_COLLECTION).document(snapshot.getId()).set(cartItem);
            return "Successfully updated at " + future.get().getUpdateTime().toString();
        }

        return "Failed to update";
    }

    public String deleteCartItem(String userId, String productId) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        CollectionReference cartItems = DB.collection(CARTITEMS_COLLECTION);
        Query query = cartItems.whereEqualTo("productId", productId).whereEqualTo("userId", userId);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot snapshot : querySnapshot.get().getDocuments()) {
            ApiFuture<WriteResult> future = cartItems.document(snapshot.getId()).delete();
            return "Successfully deleted at " + future.get().getUpdateTime().toString();
        }
        
        return "Failed to delete";
    }

}
