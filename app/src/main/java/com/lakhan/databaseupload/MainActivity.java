package com.lakhan.databaseupload;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    private EditText  fees, eme, cont, time, lo, cate,fname,color,stype,finishtype,material,size,descrip,delChar,delTime,discount;
    private ImageView pic,pic1,pic2,pic3;
//    private DatabaseHandler db;
ProgressDialog pd;
    private ProgressDialog mprogress;
    private String f_name = "None" , f_rate, f_fees=  "None", f_pri, f_eme = "None", f_cont = "None", f_time = "None", f_lo = "None", f_cate = "None",f_color = "None",f_stype = "None",f_finishtype = "None",f_size = "None",f_description = "None",f_material = "None";
    FirebaseDatabase mydata;
    DatabaseReference myref;
    StorageReference mStorageRef;
    private StorageReference mImageStorage;
    Uri choosenImage,choosenImage1,choosenImage2,choosenImage3;
    private DatabaseReference mdatabase,mdata;
    HashMap<String,String> usermap,smap;
    private Bitmap bp1,bp2,bp3,bp;
    Spinner main,sub,brand,type,product,subtype,onemore;
    String f_main,f_sub,f_brand,f_type,f_subtype,f_onemore;
    StorageReference filepath,filepath1,filepath2,filepath3;
    private String f_delChr = "None" , f_delTime = "None", f_discount=  "None";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mydata = FirebaseDatabase.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mImageStorage = FirebaseStorage.getInstance().getReference();



        pic = (ImageView) findViewById(R.id.imageView);

        pic1 = (ImageView) findViewById(R.id.imageView1);
        pic2 = (ImageView) findViewById(R.id.imageView2);
        pic3 = (ImageView) findViewById(R.id.imageView3);




        fname = (EditText) findViewById(R.id.fname);
        fees = (EditText) findViewById(R.id.fees);   //item code
//        pri = (EditText) findViewById(R.id.pri);
        time = (EditText) findViewById(R.id.time);   // price
        eme = (EditText) findViewById(R.id.emergency); // specification
//        rate = (EditText) findViewById(R.id.rate);
        cont = (EditText) findViewById(R.id.contact); // catalouge code
        lo = (EditText) findViewById(R.id.location); // sold by
        cate = (EditText) findViewById(R.id.cate); // rc code


        color = (EditText) findViewById(R.id.color); // rc code
        stype = (EditText) findViewById(R.id.Type); // rc code
        finishtype = (EditText) findViewById(R.id.FinishType); // rc code
        material = (EditText) findViewById(R.id.Material); // rc code
        size = (EditText) findViewById(R.id.Size); // rc code
        descrip = (EditText) findViewById(R.id.Description); // rc code

        delChar = (EditText) findViewById(R.id.DelivaryCharge); // rc code
        delTime = (EditText) findViewById(R.id.DelivaryTime); // rc code
        discount = (EditText) findViewById(R.id.DiscountPrice); // rc code




        main = (Spinner)findViewById(R.id.mainspinner);
        sub = (Spinner)findViewById(R.id.subspinner);
        brand = (Spinner)findViewById(R.id.brandspinner);
        type = (Spinner)findViewById(R.id.typespinner);
        product = (Spinner)findViewById(R.id.productspinner);
        subtype = (Spinner)findViewById(R.id.subtypespinner);
        onemore = (Spinner)findViewById(R.id.onemorespinner);

        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.None,R.layout.spinner_item);

        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
        type.setAdapter(Typead);


        final ArrayAdapter<CharSequence> subTypead = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.None,R.layout.spinner_item);

        subTypead.setDropDownViewResource(R.layout.spinner_dropdown);
        subtype.setAdapter(subTypead);

        ArrayAdapter<CharSequence> onemoread = ArrayAdapter.createFromResource(getApplicationContext(),
                                                                R.array.None,R.layout.spinner_item);

                                                        onemoread.setDropDownViewResource(R.layout.spinner_dropdown);
                                                        onemore.setAdapter(onemoread);
//

        ArrayAdapter<CharSequence> mainad =  ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.main,R.layout.spinner_item);

        mainad.setDropDownViewResource(R.layout.spinner_dropdown);
        main.setAdapter(mainad);

        main.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



                if (i==1){

                    ArrayAdapter<CharSequence> sunAd = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.InteriorMaterial,R.layout.spinner_item);

                    sunAd.setDropDownViewResource(R.layout.spinner_dropdown);
                    sub.setAdapter(sunAd);

                    sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                            if (i==7){
//                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
//                                        R.array.Paints,R.layout.spinner_item);
//
//                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
//                                brand.setAdapter(brandad);
//
//                            }
                            if (i==1){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Artifical_Trees_And_Flowers,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);
                                brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                        if (i==1)setProduct("ARTIFICAL TREES AND FLOWERS","ARTIFICAL PRODUCTS FOR HOME AND OFFICE DECOR");
                                        if (i==2) setProduct("ARTIFICAL TREES AND FLOWERS","CREEPER HANGING BUSHES VINES");
                                        if (i==3) setProduct("ARTIFICAL TREES AND FLOWERS","HANGING FLOWER,CREEPERS LOOSE FLOWER HEADS AND WEDDING FLOWERS");
                                        if (i==4) setProduct("ARTIFICAL TREES AND FLOWERS","HIGH QUALITY ARTIFICAL BONSAI AND ACCESSORIES");
                                        if (i==5) setProduct("ARTIFICAL TREES AND FLOWERS","HIGH QUALITY ARTIFICAL GREEN WALL(VERTICAL GARDEN MATS)");
                                        if(i==6) setProduct("ARTIFICAL TREES AND FLOWERS","HIGH QUALITY ARTIFICAL PLANTS");
                                        if(i==7) setProduct("ARTIFICAL TREES AND FLOWERS","UV PROTECTED GREEN WALL");
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });


                            }
                            if (i==2){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Hardware,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                                brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                        if (i==1)setProduct("HARDWARE","CABLE MANAGER");
                                        if (i==2) setProduct("HARDWARE","DOOR CLOSER");
                                        if (i==3) setProduct("HARDWARE","DOOR STOPPER");
                                        if (i==4) setProduct("HARDWARE","FLOOR SPRING");
                                        if (i==7) setProduct("HARDWARE","KEY HOLDER");
                                        if(i==8) setProduct("HARDWARE","PATCH FITTING SERIES");
                                        if(i==9) setProduct("HARDWARE","RIM LOCK SERIES");
                                        if (i==5){

                                            ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                    R.array.handelsize,R.layout.spinner_item);

                                            Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                            type.setAdapter(Typead);
                                            type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                                    if (i==1) setProduct("Handles","96MM");
                                                    if (i==2) setProduct("Handles","128MM");
                                                    if (i==3) setProduct("Handles","160MM");
                                                    if (i==4) setProduct("Handles","224MM");
                                                    if (i==5) setProduct("Handles","288MM");
                                                    if (i==6) setProduct("Handles","300MM");
                                                    if (i==7) setProduct("Handles","320MM");
                                                    if (i==8) setProduct("Handles","450MM");


                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> adapterView) {

                                                }
                                            });





                                        }

                                        if (i==6){
//                                            ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
//                                                    R.array.Clay_Brick,R.layout.spinner_item);
//
//                                            Typead.setDropDownViewResource(R.layout.spinner_dropdown);
//                                            type.setAdapter(Typead);

                                            final ArrayList<getData> getData = new ArrayList<getData>();
                                            final ArrayList<String> namelist = new ArrayList<String>();
                                            final ArrayAdapter<String> productad = new ArrayAdapter<String>(getApplicationContext(),
                                                    R.layout.spinner_item, namelist);

                                            productad.setDropDownViewResource(R.layout.spinner_dropdown);
                                            product.setAdapter(productad);

                                            mdata = FirebaseDatabase.getInstance().getReference().child("Rathi").child("HARDWARE").child("KNOBS");
                                            mdata.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                    for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {

                                                        final String name = (String) messageSnapshot.getKey();
                                                        Long value1 = (Long) messageSnapshot.getValue();

//                 n
                                                        namelist.add(name);
                                                        getData.add(new getData(name,Long.toString(value1)));
                                                        productad.notifyDataSetChanged();

                                                    }

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });

                                            product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                                    fname.setText(getData.get(i).getName());
                                                    time.setText(getData.get(i).getPrice());

                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> adapterView) {

                                                }
                                            });


                                        }
//                                        if (i==2){
//                                            ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
//                                                    R.array.Cement_Brick,R.layout.spinner_item);
//
//                                            Typead.setDropDownViewResource(R.layout.spinner_dropdown);
//                                            type.setAdapter(Typead);
//
//                                        }
//                                        if (i==3){
//                                            ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
//                                                    R.array.CLC_Block,R.layout.spinner_item);
//
//                                            Typead.setDropDownViewResource(R.layout.spinner_dropdown);
//                                            type.setAdapter(Typead);
//
//                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });








                            }
                            if (i==3){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Plywood,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                            if (i==7){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Mica,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);
                                brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                        if (i==1)setProduct("MICA","HERITAGE");
                                        if (i==3) setProduct("MICA","MERINO");

                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.micasize,R.layout.spinner_item);

                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subtypead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.micadimen,R.layout.spinner_item);

                                                subtypead.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subtypead);
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });


                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }












                else if (i==2){

                    ArrayAdapter<CharSequence> sunAd = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.Ready_Made_Furniture,R.layout.spinner_item);

                    sunAd.setDropDownViewResource(R.layout.spinner_dropdown);
                    sub.setAdapter(sunAd);

                    sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                            if (i==7){
//                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
//                                        R.array.Paints,R.layout.spinner_item);
//
//                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
//                                brand.setAdapter(brandad);
//
//                            }
                            if (i==1){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Bed,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }else if (i==11){


                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Sofa,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);
                            }
                            else {
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.None,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);


                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });






                }











            else if (i==3){

                    ArrayAdapter<CharSequence> sunAd = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.Construction_Materials,R.layout.spinner_item);

                    sunAd.setDropDownViewResource(R.layout.spinner_dropdown);
                    sub.setAdapter(sunAd);

                    sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                            if (i==7){
//                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
//                                        R.array.Paints,R.layout.spinner_item);
//
//                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
//                                brand.setAdapter(brandad);
//
//                            }
                            if (i==1){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Cement,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                            if (i==2){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.None,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                            if (i==3){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Sand,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                            if (i==4){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Murum,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                            if (i==5){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.None,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                            if (i==6){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Stone_Giti,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                            if (i==7){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Brick,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);
                                brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                                        if (i==7){
//                                            ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
//                                                    R.array.Paints,R.layout.spinner_item);
//
//                                            brandad.setDropDownViewResource(R.layout.spinner_dropdown);
//                                            brand.setAdapter(brandad);
//
//                                        }
                                        if (i==1){
                                            ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                    R.array.Clay_Brick,R.layout.spinner_item);

                                            Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                            type.setAdapter(Typead);

                                        }
                                        if (i==2){
                                            ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                    R.array.Cement_Brick,R.layout.spinner_item);

                                            Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                            type.setAdapter(Typead);

                                        }
                                        if (i==3){
                                            ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                    R.array.CLC_Block,R.layout.spinner_item);

                                            Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                            type.setAdapter(Typead);

                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });



                            }

                            if (i==8){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Tile,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);
                                brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.TileType,R.layout.spinner_item);

                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subtypead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.TileSize,R.layout.spinner_item);

                                                subtypead.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subtypead);

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });

                            }


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }

                else if (i==4){

                    ArrayAdapter<CharSequence> sunAd = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.Aluminium_Work,R.layout.spinner_item);

                    sunAd.setDropDownViewResource(R.layout.spinner_dropdown);
                    sub.setAdapter(sunAd);

                    sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                            if (i==7){
//                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
//                                        R.array.Paints,R.layout.spinner_item);
//
//                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
//                                brand.setAdapter(brandad);
//
//                            }
                            if (i==1){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Window,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }else {
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.None,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);


                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });





                }

                else if (i==5){

                    ArrayAdapter<CharSequence> sunAd = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.Frabrication,R.layout.spinner_item);

                    sunAd.setDropDownViewResource(R.layout.spinner_dropdown);
                    sub.setAdapter(sunAd);

                    sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



                            ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                    R.array.None,R.layout.spinner_item);

                            brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                            brand.setAdapter(brandad);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });





                }
                else if (i==6){

                    ArrayAdapter<CharSequence> sunAd = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.Electric,R.layout.spinner_item);

                    sunAd.setDropDownViewResource(R.layout.spinner_dropdown);
                    sub.setAdapter(sunAd);

                    sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                            if (i==7){
//                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
//                                        R.array.Paints,R.layout.spinner_item);
//
//                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
//                                brand.setAdapter(brandad);
//
//                            }
                            if (i==1){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Wire,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                            if (i==2){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Switch_Socket,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                            if (i==3){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Pannel_Light,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                            if (i==4){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.COB,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                            if (i==5){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Spot_Light,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }

                            if (i==6){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Fan,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                            if (i==7){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Pipe,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                            if (i==8){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Flexible_Pipe,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                            if (i==9){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.None,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                            if (i==10){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Shodel,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                            if (i==11){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Plate,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);
                                brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                                        if (i==7){
//                                            ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
//                                                    R.array.Paints,R.layout.spinner_item);
//
//                                            brandad.setDropDownViewResource(R.layout.spinner_dropdown);
//                                            brand.setAdapter(brandad);
//
//                                        }
                                        if (i==1){
                                            ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                    R.array.Anchor,R.layout.spinner_item);

                                            Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                            type.setAdapter(Typead);

                                        }
                                        if (i==2){
                                            ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                    R.array.RR,R.layout.spinner_item);

                                            Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                            type.setAdapter(Typead);

                                        }
                                        if (i==3){
                                            ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                    R.array.Grate_White,R.layout.spinner_item);

                                            Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                            type.setAdapter(Typead);

                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });


                            }

                            if (i==12){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Steel_Plate,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                            if (i==13){
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Switch_Socket,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });



                }








                else if (i==7){

                    ArrayAdapter<CharSequence> sunAd = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.Type,R.layout.spinner_item);

                    sunAd.setDropDownViewResource(R.layout.spinner_dropdown);
                    sub.setAdapter(sunAd);
                    sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
////
                                ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.Plumbing,R.layout.spinner_item);

                                brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                                brand.setAdapter(brandad);
                                brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.subtype,R.layout.spinner_item);


                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subtypead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.Size,R.layout.spinner_item);

                                                subtypead.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subtypead);

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });


//                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });






                }










                else if (i==8){




                    ArrayAdapter<CharSequence> sunAd = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.Paints,R.layout.spinner_item);

                    sunAd.setDropDownViewResource(R.layout.spinner_dropdown);
                    sub.setAdapter(sunAd);
                    sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
////
                            ArrayAdapter<CharSequence> brandad = ArrayAdapter.createFromResource(getApplicationContext(),
                                    R.array.Asian,R.layout.spinner_item);

                            brandad.setDropDownViewResource(R.layout.spinner_dropdown);
                            brand.setAdapter(brandad);
                            brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                    if (i==1){

                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.Interior,R.layout.spinner_item);

                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subtypeadq = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.liter,R.layout.spinner_item);

                                                subtypeadq.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subtypeadq);
//                                                subtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                                    @Override
//                                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                                                        ArrayAdapter<CharSequence> onemoread = ArrayAdapter.createFromResource(getApplicationContext(),
//                                                                R.array.subtype,R.layout.spinner_item);
//
//                                                        onemoread.setDropDownViewResource(R.layout.spinner_dropdown);
//                                                        onemore.setAdapter(onemoread);
//
//                                                    }
//
//                                                    @Override
//                                                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                                                    }
//                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });

                                    }
                                    if (i==2){

                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.Exterior_Wall_Finishes,R.layout.spinner_item);

                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subTypead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.liter,R.layout.spinner_item);

                                                subTypead.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subTypead);

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });

                                    }
                                    if (i==3){


                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.Distempers,R.layout.spinner_item);

                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subTypead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.liter,R.layout.spinner_item);

                                                subTypead.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subTypead);

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });
                                    }
                                    if (i==4){


                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.Tractor_Acrylic_Distemper,R.layout.spinner_item);

                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subTypead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.liter,R.layout.spinner_item);

                                                subTypead.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subTypead);

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });
                                    }
                                    if (i==5){


                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.Tractor_UNO,R.layout.spinner_item);

                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subTypead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.liter,R.layout.spinner_item);

                                                subTypead.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subTypead);

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });
                                    }
                                    if (i==6){


                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.Acrylic_Paints,R.layout.spinner_item);

                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subTypead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.liter,R.layout.spinner_item);

                                                subTypead.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subTypead);

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });
                                    }
                                    if (i==7){

                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.APCO_Premium_Gloss_Enamel,R.layout.spinner_item);

                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subTypead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.liter,R.layout.spinner_item);

                                                subTypead.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subTypead);

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });

                                    }
                                    if (i==8){


                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.APCO_Premium_Stain_Enamel,R.layout.spinner_item);

                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subTypead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.liter,R.layout.spinner_item);

                                                subTypead.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subTypead);

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });
                                    }
                                    if (i==9){


                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.AP_Utsav_Enamel,R.layout.spinner_item);

                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subTypead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.liter,R.layout.spinner_item);

                                                subTypead.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subTypead);

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });
                                    }
                                    if (i==10){


                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.Gattu_G_P_Syn_Enamel_White,R.layout.spinner_item);

                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subTypead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.liter,R.layout.spinner_item);

                                                subTypead.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subTypead);

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });
                                    }
                                    if (i==11){


                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.Primers,R.layout.spinner_item);

                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subTypead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.liter,R.layout.spinner_item);

                                                subTypead.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subTypead);

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });
                                    }
                                    if (i==12){


                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.Miscelaneous,R.layout.spinner_item);

                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subTypead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.liter,R.layout.spinner_item);

                                                subTypead.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subTypead);

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });
                                    }
                                    if (i==13){


                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.Wood_Finishes,R.layout.spinner_item);

                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subTypead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.kg,R.layout.spinner_item);

                                                subTypead.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subTypead);

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });
                                    }
                                    if (i==14){


                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.Putty,R.layout.spinner_item);

                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subTypead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.kg,R.layout.spinner_item);

                                                subTypead.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subTypead);

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });
                                    }
                                    if (i==15){


                                        ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                R.array.Adhessive,R.layout.spinner_item);

                                        Typead.setDropDownViewResource(R.layout.spinner_dropdown);
                                        type.setAdapter(Typead);
                                        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                ArrayAdapter<CharSequence> subTypead = ArrayAdapter.createFromResource(getApplicationContext(),
                                                        R.array.kg,R.layout.spinner_item);

                                                subTypead.setDropDownViewResource(R.layout.spinner_dropdown);
                                                subtype.setAdapter(subTypead);

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });
                                    }


                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });


//                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });









                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }


    public void save(View view){
        getValues();
        if (!getValues()){
            Toast.makeText(getApplicationContext(),"Please select main,sub,brand",Toast.LENGTH_SHORT).show();
            return;
        }
        if (choosenImage != null) {


//            mdatabase = FirebaseDatabase.getInstance().getReference().child("Final_Data").child("Mica").child("Heritage").child(f_cate);

            if (f_brand.equals("None")){

                mdatabase = FirebaseDatabase.getInstance().getReference().child("Final_Data").child(f_main).child(f_sub).child(f_cate);
                filepath = mImageStorage.child("Final_Data").child(f_main).child(f_sub).child(f_cate + ".jpg");
                filepath1 = mImageStorage.child("Final_Data").child(f_main).child(f_sub).child(f_cate + "1.jpg");
                filepath2 = mImageStorage.child("Final_Data").child(f_main).child(f_sub).child(f_cate + "2.jpg");
                filepath3 = mImageStorage.child("Final_Data").child(f_main).child(f_sub).child(f_cate + "3.jpg");

            }else {
                if (f_type.equals("None")) {
                    mdatabase = FirebaseDatabase.getInstance().getReference().child("Final_Data").child(f_main).child(f_sub).child(f_brand).child(f_cate);
                    filepath = mImageStorage.child("Final_Data").child(f_main).child(f_sub).child(f_brand).child(f_cate + ".jpg");
                    filepath1 = mImageStorage.child("Final_Data").child(f_main).child(f_sub).child(f_brand).child(f_cate + "1.jpg");
                    filepath2 = mImageStorage.child("Final_Data").child(f_main).child(f_sub).child(f_brand).child(f_cate + "2.jpg");
                    filepath3 = mImageStorage.child("Final_Data").child(f_main).child(f_sub).child(f_brand).child(f_cate + "3.jpg");

                }else{
                    if (f_subtype.equals("None")) {
                        mdatabase = FirebaseDatabase.getInstance().getReference().child("Final_Data").child(f_main).child(f_sub).child(f_brand).child(f_type).child(f_cate);


                        filepath = mImageStorage.child("Final_Data").child(f_main).child(f_sub).child(f_brand).child(f_type).child(f_cate + ".jpg");
                        filepath1 = mImageStorage.child("Final_Data").child(f_main).child(f_sub).child(f_brand).child(f_type).child(f_cate + "1.jpg");
                        filepath2 = mImageStorage.child("Final_Data").child(f_main).child(f_sub).child(f_brand).child(f_type).child(f_cate + "2.jpg");
                        filepath3 = mImageStorage.child("Final_Data").child(f_main).child(f_sub).child(f_brand).child(f_type).child(f_cate + "3.jpg");

                    }else {

//                        if (f_onemore.equals("None")) {
                            mdatabase = FirebaseDatabase.getInstance().getReference().child("Final_Data").child(f_main).child(f_sub).child(f_brand).child(f_type).child(f_subtype).child(f_cate);
                            filepath = mImageStorage.child("Final_Data").child(f_main).child(f_sub).child(f_brand).child(f_type).child(f_subtype).child(f_cate + ".jpg");
                        filepath1 = mImageStorage.child("Final_Data").child(f_main).child(f_sub).child(f_brand).child(f_type).child(f_subtype).child(f_cate + "1.jpg");
                        filepath2 = mImageStorage.child("Final_Data").child(f_main).child(f_sub).child(f_brand).child(f_type).child(f_subtype).child(f_cate + "2.jpg");
                        filepath3 = mImageStorage.child("Final_Data").child(f_main).child(f_sub).child(f_brand).child(f_type).child(f_subtype).child(f_cate + "3.jpg");

//                        }else {
//                            mdatabase = FirebaseDatabase.getInstance().getReference().child("Final_Data").child(f_main).child(f_sub).child(f_brand).child(f_type).child(f_subtype).child(f_onemore).child(f_cate);
//                            filepath = mImageStorage.child("Final_Data").child(f_main).child(f_sub).child(f_brand).child(f_type).child(f_subtype).child(f_onemore).child(f_cate + ".jpg");
//
//                        }
                    }
                }
            }
            usermap = new HashMap<String, String>();
            usermap.put("Catalogue_Code",f_cont);
            usermap.put("Item_Code",f_fees);
            usermap.put("Item_Specification",f_eme);
            usermap.put("Item_Price",f_time);
            usermap.put("Item_Sold_By",f_lo);
            usermap.put("Item_Name",f_name);
            usermap.put("Item_Delivary_Charge",f_delChr);
            usermap.put("Item_Delivery_Time",f_delTime);
            usermap.put("Item_Discount_Price",f_discount);


            myref = mdatabase.child("Item_Specification");
            smap = new HashMap<String, String>();
            smap.put("Colour",f_color);
            smap.put("Material",f_material);
            smap.put("Size",f_size);
            smap.put("Description",f_description);
            smap.put("Type",f_stype);
            smap.put("Finish Type",f_finishtype);

            try {

                mprogress = new ProgressDialog(MainActivity.this);
                mprogress.setTitle("Uploading");
                mprogress.setMessage("Plase Wait While Uploading");
                mprogress.setCanceledOnTouchOutside(false);
                mprogress.show();
//

                //getting image from gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), choosenImage);
//                filepath = mImageStorage.child("Final_Data").child("HERITAGE").child(f_cate + ".jpg");
                filepath.putFile(choosenImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if (task.isSuccessful()){
//                            mprogress.dismiss();

                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    String imagePath = uri.toString();
                                    usermap.put("Item_Image",imagePath);
                                    if (choosenImage1!=null){

                                        filepath1.putFile(choosenImage1).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                                                if (task.isSuccessful()){

                                                    filepath1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                        @Override
                                                        public void onSuccess(Uri uri) {
                                                            String imagePath1 = uri.toString();
                                                            usermap.put("Item_Image1",imagePath1);


                                                            if (choosenImage2!=null){

                                                                filepath2.putFile(choosenImage2).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                                                                        if (task.isSuccessful()){

                                                                            filepath2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                @Override
                                                                                public void onSuccess(Uri uri) {
                                                                                    String imagePath2 = uri.toString();
                                                                                    usermap.put("Item_Image2",imagePath2);



                                                                                    if (choosenImage3!=null){

                                                                                        filepath2.putFile(choosenImage3).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                                                                                                if (task.isSuccessful()){

                                                                                                    filepath2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                        @Override
                                                                                                        public void onSuccess(Uri uri) {
                                                                                                            String imagePath3 = uri.toString();
                                                                                                            usermap.put("Item_Image3",imagePath3);


//
                                                                                                            mdatabase.setValue(usermap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                                @Override
                                                                                                                public void onSuccess(Void aVoid) {
                                                                                                                    myref.setValue(smap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                                        @Override
                                                                                                                        public void onSuccess(Void aVoid) {
                                                                                                                            mprogress.dismiss();

                                                                                                                        }
                                                                                                                    });
                                                                                                                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();

                                                                                                                }
                                                                                                            });
//
                                                                                                        }
                                                                                                    });
                                                                                                }

                                                                                            }
                                                                                        }) ;
                                                                                    }else {

//                                        mprogress.dismiss();
///
                                                                                        mdatabase.setValue(usermap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                            @Override
                                                                                            public void onSuccess(Void aVoid) {
                                                                                                myref.setValue(smap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                    @Override
                                                                                                    public void onSuccess(Void aVoid) {
                                                                                                        mprogress.dismiss();

                                                                                                    }
                                                                                                });
                                                                                                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();

                                                                                            }
                                                                                        });
                                                                                    }


                                                                                }
                                                                            });
                                                                        }

                                                                    }
                                                                }) ;
                                                            }else {

                                                                mdatabase.setValue(usermap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {
                                                                        myref.setValue(smap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                            @Override
                                                                            public void onSuccess(Void aVoid) {
                                                                                mprogress.dismiss();

                                                                            }
                                                                        });
                                                                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();

                                                                    }
                                                                });
                                                            }






















//
//                                                            mdatabase.setValue(usermap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                                @Override
//                                                                public void onSuccess(Void aVoid) {
//                                                                    myref.setValue(smap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                                        @Override
//                                                                        public void onSuccess(Void aVoid) {
//                                                                            mprogress.dismiss();
//
//                                                                        }
//                                                                    });
//                                                                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
//
//                                                                }
//                                                            });
////                                                            mdatabase.setValue(usermap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                                @Override
//                                                                public void onSuccess(Void aVoid) {
//                                                                    myref.setValue(smap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                                        @Override
//                                                                        public void onSuccess(Void aVoid) {
//                                                                            mprogress.dismiss();
//
//                                                                        }
//                                                                    });
//                                                                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
//
//                                                                }
//                                                            });
                                                        }
                                                    });
                                                }

                                            }
                                        }) ;
                                    }else {

//                                        mprogress.dismiss();
                                        mdatabase.setValue(usermap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                myref.setValue(smap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        mprogress.dismiss();

                                                    }
                                                });
                                                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();

                                            }
                                        });
                                    }





//
//                                    mdatabase.setValue(usermap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                        @Override
//                                        public void onSuccess(Void aVoid) {
//                                            myref.setValue(smap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                @Override
//                                                public void onSuccess(Void aVoid) {
////                                                    mprogress.dismiss();
////                                                    if (choosenImage1!=null){
////
////                                                      filepath1.putFile(choosenImage1).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
////                                                          @Override
////                                                          public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
////
////                                                              if (task.isSuccessful()){
////
////                                                                  filepath1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
////                                                                      @Override
////                                                                      public void onSuccess(Uri uri) {
////                                                                          String imagePath1 = uri.toString();
////                                                                          usermap.put("Item_Image1",imagePath1);
////
////                                                                          mdatabase.setValue(usermap).addOnSuccessListener(new OnSuccessListener<Void>() {
////                                                                              @Override
////                                                                              public void onSuccess(Void aVoid) {
////                                                                                  myref.setValue(smap).addOnSuccessListener(new OnSuccessListener<Void>() {
////                                                                                      @Override
////                                                                                      public void onSuccess(Void aVoid) {
////                                                                                          mprogress.dismiss();
////
////                                                                                      }
////                                                                                  });
////                                                                              }
////                                                                          });
////                                                                      }
////                                                                  });
////                                                              }
////
////                                                          }
////                                                      }) ;
////                                                    }else {
////
////                                                                                                            mprogress.dismiss();
////
////                                                    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//                                                }
//                                            });
////
//                                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
////
//                                        }
//                                    });




                                }
                            });

//                                        Strin/g Download_uri = task.getResult().get;
//                                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();

                            //noinspection VisibleForTests
//                            String Download_uri = task.getResult().getDownloadUrl().toString();
//                            mdatabaseref.child("Image").setValue(Download_uri).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    mprogress.dismiss();
//
//                                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
//
//
//                                }
//                            });
                        }else
                        {

                            Toast.makeText(getApplicationContext()," not Success",Toast.LENGTH_LONG).show();
                            mprogress.dismiss();
                        }

                    }
                });



                //Setting image to ImageView
//                imgView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }












//                        bp = d??ecodeUri(choosenImage, 400);
//                        pic.setImageBitmap(bp);
        }
        //addContact();
    }

    public void select(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 0);

//        selectImage();
    }
    public void select1(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);

//        selectImage();
    }

    public void select2(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 2);

//        selectImage();
    }

    public void select3(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 3);

//        selectImage();
    }

//    public void selectImage() {
//        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, 0);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    choosenImage = data.getData();
                    bp = decodeUri(choosenImage, 400);
                    pic.setImageBitmap(bp);

//                    pic.setImageBitmap(BitmapFactory.decodeFile(choosenImage.toString()));

                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    choosenImage1 = data.getData();
                    bp1 = decodeUri(choosenImage, 400);
                    pic1.setImageBitmap(bp1);

//                    pic.setImageBitmap(BitmapFactory.decodeFile(choosenImage.toString()));

                }
                break;

            case 2:
                if (resultCode == RESULT_OK) {
                    choosenImage2 = data.getData();
                    bp2 = decodeUri(choosenImage, 400);
                    pic2.setImageBitmap(bp2);

//                    pic.setImageBitmap(BitmapFactory.decodeFile(choosenImage.toString()));

                }

                break;
            case 3:
                if (resultCode == RESULT_OK) {
                    choosenImage3 = data.getData();
                    bp3 = decodeUri(choosenImage, 400);
                    pic3.setImageBitmap(bp3);

//                    pic.setImageBitmap(BitmapFactory.decodeFile(choosenImage.toString()));

                }

                break;
        }
    }

    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {

        try {

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

            // The new size we want to scale to
            // final int REQUIRED_SIZE =  size;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    private boolean getValues() {
        boolean ok = true;
        f_name = fname.getText().toString();
//        f_rate = rate.getText().toString();
        f_fees = fees.getText().toString();
        f_eme = eme.getText().toString();
        f_cate = cate.getText().toString();
        f_time = time.getText().toString();
//        f_pri = pri.getText().toString();
        f_lo = lo.getText().toString();
        f_cont = cont.getText().toString();

        f_size = size.getText().toString();
        f_description = descrip.getText().toString();
        f_color = color.getText().toString();
        f_stype=stype.getText().toString();
        f_finishtype=finishtype.getText().toString();
        f_material=material.getText().toString();


        f_delChr = delChar.getText().toString();
        f_delTime = delTime.getText().toString();
        f_discount = discount.getText().toString();

        f_type = type.getSelectedItem().toString();
        if (f_type.equals("Select"))
            f_type = "None";

        f_subtype = subtype.getSelectedItem().toString();
        if (f_subtype.equals("Select"))
            f_subtype = "None";

        f_onemore = onemore.getSelectedItem().toString();
        if (f_onemore.equals("Select"))
            f_onemore = "None";



        f_main = main.getSelectedItem().toString();
        if (f_main.equals("Select")){
            ok = false;
        }else {

            f_sub = sub.getSelectedItem().toString();
            if (f_sub.equals("Select")) {
                ok = false;
                f_sub = "None";
            }else {

                f_brand = brand.getSelectedItem().toString();
                if (f_brand.equals("Select")) {
                    ok = false;
                    f_brand = "None";
                }
            }
        }
/*
        f_name = " Dr.Lakhan Rathi";
        f_rate = "4";
        f_fees = "100";
        f_eme = "yes";
        f_cate = "1";
        f_time = "10";
        f_pri ="high";
        f_lo = "pune";
        f_cont = "7350588622";
*/
//        photo = profileImage(anImage);
        return ok;
    }



    public void setProduct(String type,String ok){
        final ArrayList<getData> getData = new ArrayList<getData>();
        final ArrayList<String> namelist = new ArrayList<String>();
        final ArrayAdapter<String> productad = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinner_item, namelist);

        productad.setDropDownViewResource(R.layout.spinner_dropdown);
        product.setAdapter(productad);

        mdata = FirebaseDatabase.getInstance().getReference().child("Rathi").child(type).child(ok);
        mdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {

                    final String name = (String) messageSnapshot.getKey();
                    Long value1 = (Long) messageSnapshot.getValue();

//                 n
                    namelist.add(name);
                    getData.add(new getData(name,Long.toString(value1)));
                    productad.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                fname.setText(getData.get(i).getName());
                time.setText(getData.get(i).getPrice());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


}
