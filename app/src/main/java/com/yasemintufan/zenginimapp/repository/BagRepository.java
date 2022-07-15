package com.yasemintufan.zenginimapp.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.yasemintufan.zenginimapp.DataLoadListener;
import com.yasemintufan.zenginimapp.DataUsage;
import com.yasemintufan.zenginimapp.fragments.BagFragment;
import com.yasemintufan.zenginimapp.fragments.HomeFragment;
import com.yasemintufan.zenginimapp.models.BagProductModel;
import com.yasemintufan.zenginimapp.models.NewProductsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BagRepository {

    FirebaseFirestore firebaseFirestore;
    DataUsage dataUsage;

    private MutableLiveData<List<BagProductModel>> mutableBagList;

    public LiveData<List<BagProductModel>> getBagProduct () {

        if (mutableBagList == null) {
            mutableBagList = new MutableLiveData<>();
            loadBagProduct();
        }
        return mutableBagList;
    }
    private void loadBagProduct () {

        List<BagProductModel> bagList = new ArrayList<>();
        mutableBagList.setValue(bagList);

        firebaseFirestore = firebaseFirestore.getInstance();

        firebaseFirestore.collection("BagProducts")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot documentSnapshot :list) {
                                bagList.add(documentSnapshot.toObject(BagProductModel.class));
                            }
                            Log.e(TAG,"onSuccess:addedadded");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG,"onFailure:",e);
            }
        });


    }
}
