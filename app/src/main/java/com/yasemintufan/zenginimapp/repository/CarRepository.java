package com.yasemintufan.zenginimapp.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.yasemintufan.zenginimapp.DataUsage;
import com.yasemintufan.zenginimapp.fragments.CarFragment;
import com.yasemintufan.zenginimapp.models.CarProductModel;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {

    FirebaseFirestore firebaseFirestore;
    static CarRepository instance;
    private ArrayList <CarProductModel> carProductModels = new ArrayList<>();
    String TAG;

    static CarFragment mContext;
    static DataUsage dataUsage;

    private MutableLiveData<List<CarProductModel>> mutableCarList;

    public LiveData<List<CarProductModel>> getCarModel() {

        if (mutableCarList == null) {
            mutableCarList = new MutableLiveData<>();

            loadProducts();
        }
        return mutableCarList;
    }

    private void loadProducts() {
        List<CarProductModel> getCarModel = new ArrayList<>();
        mutableCarList.setValue(getCarModel);

        firebaseFirestore = firebaseFirestore.getInstance();
        firebaseFirestore.collection("CarProducts")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot>list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot documentSnapshot :list) {
                                getCarModel.add(documentSnapshot.toObject(CarProductModel.class));
                            }
                            Log.e(TAG,"onSuccess:LALALAL");
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG,"onFailure:",e);

            }
        });



        /*getCarModel.add(new CarProductModel("Porsche",2500,"https://firebasestorage.googleapis.com/v0/b/zenginim-c84d5.appspot.com/o/araba1.jpg?alt=media&token=e9051483-72c8-4100-af68-43b733b223d7"));
        getCarModel.add(new CarProductModel("Lamborghini",3500,"https://firebasestorage.googleapis.com/v0/b/zenginim-c84d5.appspot.com/o/araba2.jpg?alt=media&token=96c55c11-3ab7-4a92-b40b-a4e412e5fc46"));
        getCarModel.add(new CarProductModel("Porsche",3800,"https://firebasestorage.googleapis.com/v0/b/zenginim-c84d5.appspot.com/o/araba3.jpg?alt=media&token=7454aa74-6cb8-4446-9966-a24e3378d37e"));
        getCarModel.add(new CarProductModel("McLaren",4000,"https://firebasestorage.googleapis.com/v0/b/zenginim-c84d5.appspot.com/o/bluecar.png?alt=media&token=097f4edd-69ce-4cb4-89b2-db3712546753"));
        getCarModel.add(new CarProductModel("Bugatti",4200,"https://firebasestorage.googleapis.com/v0/b/zenginim-c84d5.appspot.com/o/orangecar.jpg?alt=media&token=30c2d71e-e30e-4dcc-abbe-476ba212d95d"));
        getCarModel.add(new CarProductModel("Koenigsegg",4200,"https://firebasestorage.googleapis.com/v0/b/zenginim-c84d5.appspot.com/o/redcar.jpg?alt=media&token=66fd9ae0-5c9a-4798-b2eb-17677d086c50"));



    }
}

         */
    }
}
