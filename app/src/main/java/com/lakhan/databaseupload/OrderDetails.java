package com.lakhan.databaseupload;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class OrderDetails extends AppCompatActivity {
    View view;

    private FirebaseAuth mAuth;
    FirebaseDatabase mydata;
    DatabaseReference myref,getMyref,ref,priceReg,addressRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userName;
    private TextView headerUserName;
    RecyclerView recyclerView;
    private StorageReference mStorageRef;
    CartAdapter customAdapter;
    String main,sub;
    private ProgressDialog mregprogress;
    TextView nowishlist;
    ArrayList<DatabasePojo> databasePojos = new ArrayList<DatabasePojo>();
    float totalPrice = 0,discountPrice = 0,originalPrice = 0,totalPriceQuantity = 0,discountPriceQuantity = 0,originalPriceQuantity = 0;
    TextView totalPriceText,discountPriceText,originalPriceText;
    TextView cartCountText;
    LinearLayout linearLayout;
    Button totalPriceButton,placeOrderButton;
    TextView address_Name,addressDefault,address_addressText,address_address1Text,address_cityText,Address_stateText,Address_mobileText;
    private RadioButton radioButton;
    Context context;
    String id;
    //    private Button placeOrderButton;
//    TextView originalprice,discountPriceText,Percent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);


        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mAuth = FirebaseAuth.getInstance();
        mydata = FirebaseDatabase.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        customAdapter = new CartAdapter(this, this.databasePojos,2);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
//        this.callMain = (PaymentFragmentListner) getActivity();

        myref = FirebaseDatabase.getInstance().getReference().child("OrderId");
        ValueEventListener valueEventListener = myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                databasePojos.clear();
                totalPrice = 0;
                discountPrice = 0;
                originalPrice = 0;
                totalPriceQuantity = 0;
                discountPriceQuantity = 0;
                originalPriceQuantity = 0;

//                if (dataSnapshot.hasChild("Cart")){

//                    nowishlist.setVisibility(View.GONE);
//                    recyclerView.setVisibility(View.VISIBLE);

                getMyref = FirebaseDatabase.getInstance().getReference().child("OrderId").child(id).child("CartItems");
                getMyref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                        if (dataSnapshot.hasChild("Item_Product")) {
                            for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                                final String name = (String) messageSnapshot.getKey();
//                                cartCountText.setText("Item(" + dataSnapshot.getChildrenCount() + ")");

                                databasePojos.add(new DatabasePojo((String) messageSnapshot.child("Item_Category").getValue(),
                                        (String) messageSnapshot.child("Item_Product").getValue(),
                                        name,
                                        (String) messageSnapshot.child("Catalogue_Code").getValue(),
                                        (String) messageSnapshot.child("Item_Code").getValue(),
                                        (String) messageSnapshot.child("Item_Delivary_Charge").getValue(),
                                        (String) messageSnapshot.child("Item_Delivery_Time").getValue(),
                                        (String) messageSnapshot.child("Item_Discount_Price").getValue(),
                                        (String) messageSnapshot.child("Item_Image").getValue(),
                                        (String) messageSnapshot.child("Item_Image1").getValue(),
                                        (String) messageSnapshot.child("Item_Image2").getValue(),
                                        (String) messageSnapshot.child("Item_Image3").getValue(),
                                        (String) messageSnapshot.child("Item_Name").getValue(),
                                        (String) messageSnapshot.child("Item_Price").getValue(),
                                        (String) messageSnapshot.child("Item_Sold_By").getValue(),
                                        (String) messageSnapshot.child("Colour").getValue(),
                                        (String) messageSnapshot.child("Description").getValue(),
                                        (String) messageSnapshot.child("Finish Type").getValue(),
                                        (String) messageSnapshot.child("Material").getValue(),
                                        (String) messageSnapshot.child("Size").getValue(),
                                        (String) messageSnapshot.child("Type").getValue()
                                ));

//                                calculatetotalprice((String) messageSnapshot.child("Item_Discount_Price").getValue(),(String) messageSnapshot.child("Item_Price").getValue(),(String) messageSnapshot.child("Item_Quantity").getValue());

                                customAdapter.notifyDataSetChanged();
//                                mregprogress.hide();


                            }
//                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


//                }
//                else{
//
//
////                    nowishlist.setVisibility(View.VISIBLE);
//                    recyclerView.setVisibility(View.GONE);
//                    linearLayout.setVisibility(View.GONE);
//                    cartCountText.setVisibility(View.GONE);
//                    mregprogress.hide();
//
//
//                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        address_Name = (TextView)findViewById(R.id.address_Name);
        address_addressText = (TextView)findViewById(R.id.address_addressText);
        address_address1Text = (TextView)findViewById(R.id.address_address1Text);
        address_cityText = (TextView)findViewById(R.id.address_cityText);
        Address_stateText = (TextView)findViewById(R.id.Address_stateText);
        Address_mobileText = (TextView)findViewById(R.id.Address_mobileText);
        addressRef = FirebaseDatabase.getInstance().getReference().child("OrderId").child(id).child("CartAddress");
        addressRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChild("address_Name")){

//                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {

//                    address_Name.setText((String) messageSnapshot.child("address_Name").getValue());
//                    address_cityText.setText((String) messageSnapshot.child("address_cityText").getValue()+ " - " +(String) messageSnapshot.child("address_pincode").getValue());
//                    address_addressText.setText((String) messageSnapshot.child("address_addressText").getValue());
//                    Address_stateText.setText((String) messageSnapshot.child("address_stateText").getValue());
//                    Address_mobileText.setText((String) messageSnapshot.child("address_mobileText").getValue());
//                    address_address1Text.setText((String) messageSnapshot.child("address_address1Text").getValue());
                    address_Name.setText((String) dataSnapshot.child("address_Name").getValue());
                    address_cityText.setText((String) dataSnapshot.child("address_cityText").getValue()+ " - " +(String) dataSnapshot.child("address_pincode").getValue());
                    address_addressText.setText((String) dataSnapshot.child("address_addressText").getValue());
                    Address_stateText.setText((String) dataSnapshot.child("address_stateText").getValue());
                    Address_mobileText.setText((String) dataSnapshot.child("address_mobileText").getValue());
                    address_address1Text.setText((String) dataSnapshot.child("address_address1Text").getValue());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        discountPriceText = (TextView)findViewById(R.id.cardDiscountPrice);
//        originalprice = (TextView)findViewById(R.id.cardOriginalPrice);
//        Percent = (TextView)findViewById(R.id.cardDiscountPercent);

        totalPriceText = (TextView)findViewById(R.id.TotalText);
        originalPriceText = (TextView)findViewById(R.id.TotalMRPText);
        discountPriceText = (TextView)findViewById(R.id.TotalDiscountText);
        priceReg = FirebaseDatabase.getInstance().getReference().child("OrderId").child(id).child("CartPrice");
        priceReg.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("totalPrice")) {
//                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {

//                    address_Name.setText((String) messageSnapshot.child("address_Name").getValue());
//                    address_cityText.setText((String) messageSnapshot.child("address_cityText").getValue()+ " - " +(String) messageSnapshot.child("address_pincode").getValue());
//                    address_addressText.setText((String) messageSnapshot.child("address_addressText").getValue());
//                    Address_stateText.setText((String) messageSnapshot.child("address_stateText").getValue());
//                    Address_mobileText.setText((String) messageSnapshot.child("address_mobileText").getValue());
//                    address_address1Text.setText((String) messageSnapshot.child("address_address1Text").getValue());
                    totalPriceText.setText(Long.toString((Long) dataSnapshot.child("totalPrice").getValue()));
                    originalPriceText.setText(Long.toString((Long) dataSnapshot.child("totalOriginalPrice").getValue()));
                    discountPriceText.setText(Long.toString((Long) dataSnapshot.child("totalDiscount").getValue()));
//                Address_stateText.setText((String) dataSnapshot.child("address_stateText").getValue());
//                Address_mobileText.setText((String) dataSnapshot.child("address_mobileText").getValue());
//                address_address1Text.setText((String) dataSnapshot.child("address_address1Text").getValue());

//                }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        radioButton = (RadioButton)findViewById(R.id.radioButtonCashOnDelivery);
//        placeOrderButton = (Button)findViewById(R.id.PlaceOrderButton);
//        placeOrderButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!radioButton.isChecked()){
//
//                    Toast.makeText(context,"Please select payment option at bottom",Toast.LENGTH_LONG).show();
//                }else {
//
////                    callMain.PaymentFragmentListner();
//
////                    Random rnd = new Random();
////                    int number = rnd.nextInt(999999);
//                }
//            }
//        });


    }
}
