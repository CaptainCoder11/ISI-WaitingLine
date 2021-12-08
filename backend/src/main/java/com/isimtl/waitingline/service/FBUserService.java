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
    public String savePatientDetails(FBUser patient) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("waiting_list").document(patient.getName()).set(patient);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
}
