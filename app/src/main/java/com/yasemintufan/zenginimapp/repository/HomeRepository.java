package com.yasemintufan.zenginimapp.repository;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.yasemintufan.zenginimapp.DataLoadListener;
import com.yasemintufan.zenginimapp.adapters.NewProductsAdapter;
import com.yasemintufan.zenginimapp.fragments.HomeFragment;
import com.yasemintufan.zenginimapp.models.NewProductsModel;

import java.util.ArrayList;
import java.util.List;

public class HomeRepository {

    static HomeRepository instance;
    private ArrayList<NewProductsModel>newProductsModels = new ArrayList<>();
    FirebaseFirestore firebaseFirestore;
    NewProductsAdapter newProductsAdapter;

    static HomeFragment mContext;
    static DataLoadListener dataLoadListener;
    public static HomeRepository getInstance(HomeFragment context) {

         mContext = context;
        if (instance == null) {
            instance = new HomeRepository();
        }
        dataLoadListener = (DataLoadListener) mContext;
        return instance;

    }
    public MutableLiveData<ArrayList<NewProductsModel>> getNewProducts () {
        loadNewProducts();

        MutableLiveData<ArrayList<NewProductsModel>> newProducts = new MutableLiveData<>();
        newProducts.setValue(newProductsModels);
        return newProducts;
    }

    private void loadNewProducts() {

        firebaseFirestore = firebaseFirestore.getInstance();

        firebaseFirestore.collection("NewProduct")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot documentSnapshot :list) {
                                newProductsModels.add(documentSnapshot.toObject(NewProductsModel.class));

                            }
                            Log.e(TAG,"onSuccess:added");
                            dataLoadListener.onNameLoaded();
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
