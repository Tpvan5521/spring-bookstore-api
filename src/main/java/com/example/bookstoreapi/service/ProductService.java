package com.example.bookstoreapi.service;

import com.example.bookstoreapi.entity.Product;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductService {

    private static final String PRODUCTS_COLLECTION = "products";

    private List<Product> snapshotToList(Query query, CollectionReference products) throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<Product> listProduct = new ArrayList<>();
        for (DocumentSnapshot snapshot : querySnapshot.get().getDocuments()) {
            DocumentReference docRef = products.document(snapshot.getId());
            ApiFuture<DocumentSnapshot> docRefApi = docRef.get();
            DocumentSnapshot document = docRefApi.get();
            listProduct.add(document.toObject(Product.class));
        }
        return listProduct;
    }

    public String getProductId(String productSlug) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        CollectionReference products = DB.collection(PRODUCTS_COLLECTION);
        Query query = products.whereEqualTo("productSlug", productSlug);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot snapshot : querySnapshot.get().getDocuments()) {
            return snapshot.getId();
        }

        return "";
    }

    public Product getProductByDocId(String productId) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        DocumentReference docRef = DB.collection(PRODUCTS_COLLECTION).document(productId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        Product product = null;
        
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            product = document.toObject(Product.class);
        }
        
        return product;
    }

    public List<Product> getProducts() throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        CollectionReference products = DB.collection(PRODUCTS_COLLECTION);
        ApiFuture<QuerySnapshot> future = products.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Product> listProduct = new ArrayList<>();
        documents.forEach(document -> {
            listProduct.add(document.toObject(Product.class));
        });
        return listProduct;
    }

    public List<Product> getProductsByValue(String FIELD, Object value) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        CollectionReference products = DB.collection(PRODUCTS_COLLECTION);
        Query query = products.whereEqualTo(FIELD, value);
        return snapshotToList(query, products);
    }

    public List<Product> getProductsByRange(String FIELD, int min, int max) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        CollectionReference products = DB.collection(PRODUCTS_COLLECTION);
        Query query = products.whereGreaterThanOrEqualTo(FIELD, min).whereLessThanOrEqualTo(FIELD, max);
        return snapshotToList(query, products);
    }

    public List<Product> getProductsOrderedAndLimited(String FIELD, int limitValue) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        CollectionReference products = DB.collection(PRODUCTS_COLLECTION);
        if (limitValue == -1) {
            Query query = products.orderBy(FIELD, Query.Direction.DESCENDING);
            return snapshotToList(query, products);
        }
        Query query = products.orderBy(FIELD, Query.Direction.DESCENDING).limit(limitValue);
        return snapshotToList(query, products);
    }

    public Product getProductDetails(String productSlug) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        CollectionReference products = DB.collection(PRODUCTS_COLLECTION);
        Query query = products.whereEqualTo("productSlug", productSlug);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        Product product = null;

        for (DocumentSnapshot snapshot : querySnapshot.get().getDocuments()) {
            DocumentReference docRef = products.document(snapshot.getId());
            ApiFuture<DocumentSnapshot> docRefApi = docRef.get();
            DocumentSnapshot document = docRefApi.get();
            product = document.toObject(Product.class);
        }
        return product;
    }

    public String createProduct(Product product) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = DB.collection(PRODUCTS_COLLECTION).document().set(product);
        return "Successfully created at " + future.get().getUpdateTime().toString();
    }

    public String updateProduct(String productSlug, Product product) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        CollectionReference products = DB.collection(PRODUCTS_COLLECTION);
        Query query = products.whereEqualTo("productSlug", productSlug);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot snapshot : querySnapshot.get().getDocuments()) {
            ApiFuture<WriteResult> future = DB.collection(PRODUCTS_COLLECTION).document(snapshot.getId()).set(product);
            return "Successfully updated at " + future.get().getUpdateTime().toString();
        }
        return "Failed to update";
    }

    public String deleteProduct(String productSlug) throws InterruptedException, ExecutionException {
        Firestore DB = FirestoreClient.getFirestore();
        CollectionReference products = DB.collection("products");
        Query query = products.whereEqualTo("productSlug", productSlug);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot snapshot : querySnapshot.get().getDocuments()) {
            ApiFuture<WriteResult> future = products.document(snapshot.getId()).delete();
            return "Successfully deleted at " + future.get().getUpdateTime().toString();
        }
        return "Failed to delete.";
    }

}
