package com.lakhan.databaseupload;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Order extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList = new ArrayList<String>();
    FirebaseDatabase mydata;
    DatabaseReference myref;
    StorageReference mStorageRef;
    ArrayAdapter<String> arrayadapter;
    Uri filePath;
    ProgressDialog pd;
    private ProgressDialog mprogress;
    private StorageReference mImageStorage;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        mydata = FirebaseDatabase.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mImageStorage = FirebaseStorage.getInstance().getReference();

        listView = (ListView)findViewById(R.id.myListView);


        arrayadapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);

                name = arrayList.get(position);
                Intent intent = new Intent(getApplicationContext(),OrderDetails.class);
                intent.putExtra("id",name);
                startActivity(intent);



//                PaymentFragment serviceProvideFragment = new PaymentFragment(getApplicationContext());
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction ft = fragmentManager.beginTransaction();
//                ft.addToBackStack("PaymentPage");
////                args.putString("Main",expandableListTitle.get(groupPosition));
////                serviceProvideFragment.setArguments(args);
////            fragmentManager.popBackStack();
//                ft.replace(R.id.content_frame, serviceProvideFragment, "ourservicescategory")
//                        .addToBackStack(null)
//                        .commit();
//                CropImage.activity()
//                        .setGuidelines(CropImageView.Guidelines.ON)
//                        .start(MainActivity.this);

            }
        });


        myref = mydata.getReference().child("OrderId");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    String name = (String) messageSnapshot.getKey();

                    arrayList.add(name);
                    arrayadapter.notifyDataSetChanged();


//                    String value = (String) messageSnapshot.child(name).getValue();
//                    String l =  messageSnapshot.getValue();
//                    final long l = (long) messageSnapshot.getValue();
//
//                    Log.i("Lakhan2 " ,"  Name  " + name + "   Value  " + value + " l " + l + "\n");
//                    String one = name.replace("+",".");
//                    String one1 = one.replace("%","/");
//
//                    Log.i("Lakhan rathi2" ,"  Name  " + one + " l " + one1 + "\n");
//                    String finalName = name;
//                    if (name.contains("(")) {
//                        finalName = name.substring(name.indexOf("(")+1,name.indexOf(")")-1);
//                    }
//                    Log.i("Lakhan8 ", "  Name  " + name + "   Value  " + value + " l " + l + "\n");
//                    StorageReference pathReference =  mStorageRef.child("MICA/HERITAGE/1101 TCH.jpg");
//                    final String finalName1 = finalName;
//                    pathReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Uri> task) {
//
//                            if (task.isSuccessful()){
//                                products.add(new Product("MICA","HERITAGE",name, finalName1,l,task.getResult().toString()));
//                                customAdapter.notifyDataSetChanged();
//
//
//                            }
//                        }
//                    });


//                    products.add(new Product("MICA","HERITAGE",name,finalName,l,null));
                    //
//                    appoinment_name
//                    Log.i("Lakhan2 " ,"Name " + name + "Value " + value + "\n");
//                    appoinment_name.add(name);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
