package com.isimtl.waitingline.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.isimtl.waitingline.entity.FBUser;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FBUserService {

    public String save(FBUser user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("waiting_list").document(user.getEmail()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String delete(FBUser user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("waiting_list").document(user.getEmail()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
}
