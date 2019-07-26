package com.lakhan.databaseupload;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

//    private ArrayList<Product> products;
    private Context context;
    TextView name;
    ImageView image;
    TextView price;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference ref,mdatabase1;
    CartAdapterLisnter Cartitem;
    ArrayList<DatabasePojo> databasePojos;
    HashMap<String,String> usermap,smap;
    FirebaseUser current_user;
    String uid;
    int ispaymentPage;
    int check = 0;





    public interface CartAdapterLisnter{

        void onCartItemClick(DatabasePojo masterItemId, int quantity);


    }



    public CartAdapter(Context context, ArrayList<DatabasePojo> databasePojos,int bool){

        this.context = context;
        this.databasePojos = databasePojos;
        this.Cartitem = (CartAdapterLisnter) context;
        this.ispaymentPage = bool;


    }



    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cartitemview,viewGroup,false);
        MyViewHolder vh = new MyViewHolder(view); // pass the view to View Holder
        return vh;    }


    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int i) {
        viewHolder.name.setText(databasePojos.get(i).getRc_code());

        if (databasePojos.get(i).getItem_Discount_Price()!=null) {
            float discountPercent = Float.valueOf(databasePojos.get(i).getItem_Discount_Price()) / Float.valueOf(databasePojos.get(i).getItem_Price());
            float multiple = discountPercent * 100;
            float finalDiscount = 100 - multiple;
            viewHolder.Percent.setText(finalDiscount + "% OFF");
            viewHolder.discountPrice.setText("Rs."+databasePojos.get(i).getItem_Discount_Price());
            viewHolder.originalprice.setText("Rs." + databasePojos.get(i).getItem_Price());
            viewHolder.originalprice.setPaintFlags(viewHolder.originalprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        }else {

            viewHolder.discountPrice.setText("Rs." + databasePojos.get(i).getItem_Price());
            viewHolder.Percent.setVisibility(View.INVISIBLE);
            viewHolder.originalprice.setVisibility(View.INVISIBLE);

        }

        Picasso.with(context)
                .load(databasePojos.get(i).getItem_Image())
                .resize(550,500)
                .placeholder(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(viewHolder.image);





        viewHolder.quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if (databasePojos.size()!=0)
                Cartitem.onCartItemClick(databasePojos.get(i),position+1);
//                Toast.makeText(context,""+position + i + databasePojos.size(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//
//
//        viewHolder.quantity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Cartitem.onCartItemClick(databasePojos.get(i),position+1);
//
//            }
//        });


        viewHolder.cartRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference().child("USER").child(mAuth.getCurrentUser().getUid()).child("Cart").child(databasePojos.get(i).getRc_code());
//                Map<String, Object > childUpdates = new HashMap<>();
//                childUpdates.put("Item_Quantity",Integer.toString(quantity));

                ref.removeValue();


            }
        });


        current_user = FirebaseAuth.getInstance().getCurrentUser();
        uid = current_user.getUid();


        viewHolder.wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference().child("USER").child(mAuth.getCurrentUser().getUid()).child("Cart").child(databasePojos.get(i).getRc_code());
//                Map<String, Object > childUpdates = databasePojos.get(i).toM;
//                childUpdates.put("Item_Quantity",Integer.toString(quantity));
                ref.removeValue();


//                viewHolder.heartImage.setImageResource(R.drawable.ic_heart_red);
                mdatabase1 = FirebaseDatabase.getInstance().getReference().child("USER").child(uid).child("Wishlist").child(databasePojos.get(i).getRc_code());
//                HashMap<String,String> usermap1 = new HashMap<String, String>();


                usermap = new HashMap<String, String>();
                usermap.put("Catalogue_Code",databasePojos.get(i).getCatalogue_Code());
                usermap.put("Item_Code",databasePojos.get(i).getItem_Code());
                usermap.put("Item_Price",databasePojos.get(i).getItem_Price());
                usermap.put("Item_Sold_By",databasePojos.get(i).getItem_Sold_By());
                usermap.put("Item_Name",databasePojos.get(i).getItem_Name());
                usermap.put("Item_Delivary_Charge",databasePojos.get(i).getItem_Delivary_Charge());
                usermap.put("Item_Delivery_Time",databasePojos.get(i).getItem_Delivery_Time());
                usermap.put("Item_Discount_Price",databasePojos.get(i).getItem_Discount_Price());
                usermap.put("Colour",databasePojos.get(i).getColour());
                usermap.put("Material",databasePojos.get(i).getMaterial());
                usermap.put("Size",databasePojos.get(i).getSize());
                usermap.put("Description",databasePojos.get(i).getDescription());
                usermap.put("Type",databasePojos.get(i).getType());
                usermap.put("Finish Type",databasePojos.get(i).getFinish_Type());
                usermap.put("Item_Image",databasePojos.get(i).getItem_Image());
                usermap.put("Item_Image1",databasePojos.get(i).getItem_Image1());
                usermap.put("Item_Image2",databasePojos.get(i).getItem_Image2());
                usermap.put("Item_Image3",databasePojos.get(i).getItem_Image3());
                usermap.put("Item_Category",databasePojos.get(i).getProduct());
                usermap.put("Item_Product",databasePojos.get(i).getType_product());
                usermap.put("Item_Product_Type",databasePojos.get(i).getSub_type());
                usermap.put("Item_Company",databasePojos.get(i).getCompany());

                mdatabase1.setValue(usermap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
//                        mregprogress.dismiss();
                        Toast.makeText(context,"Added to wishlist",Toast.LENGTH_LONG).show();
                    }
                });



            }
        });


        if (ispaymentPage==2){

            viewHolder.wishOrremove.setVisibility(View.GONE);
            viewHolder.quantity.setVisibility(View.INVISIBLE);
            viewHolder.quantityText.setVisibility(View.VISIBLE);


        }


//        viewHolder.heartImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
//                String uid = current_user.getUid();
//
//                viewHolder.heartImage.setImageResource(R.drawable.ic_heart_red);
//                mdatabase1 = FirebaseDatabase.getInstance().getReference().child("USER").child(uid).child("Wishlist").child(""+products.get(i).getCategory() + "^" +products.get(i).getSubCategory() + "^" + products.get(i).getCode());
//                HashMap<String,String> usermap1 = new HashMap<String, String>();
//                usermap1.put(""+products.get(i).getCategory() + "^" +products.get(i).getSubCategory() + "^" + products.get(i).getCode() , String.valueOf(products.get(i).getPrice()));
////
////
//
//
//                mdatabase1.setValue( String.valueOf(products.get(i).getPrice())).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//
//                        if (task.isSuccessful()){
//
//                            Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show();
//
//                        }
//                        else {
////                            mregprogress.hide();
//                            Toast.makeText(context, "Something is Wrong", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    }
//                });
//
//
//            }
//        });

////        Glide.with(context)
////                .using(new FirebaseImageLoader())
////                .load(products.get(i).getUrl())
////                .into(viewHolder.image);
//

    }

    @Override
    public int getItemCount() {
        return databasePojos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //         init the item view's
        TextView name;
        ImageView image;
        TextView originalprice,discountPrice,Percent,quantityText;
        Spinner quantity;
        Button cartRemove,wishlist;
        LinearLayout wishOrremove;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.cardName);
            image = (ImageView) itemView.findViewById(R.id.cardImageView);
            discountPrice = (TextView)itemView.findViewById(R.id.cardDiscountPrice);
            originalprice = (TextView)itemView.findViewById(R.id.cardOriginalPrice);
            Percent = (TextView)itemView.findViewById(R.id.cardDiscountPercent);
            quantityText = (TextView)itemView.findViewById(R.id.quantityText);


            quantity = (Spinner)itemView.findViewById(R.id.Quantityspinner);
            cartRemove = (Button) itemView.findViewById(R.id.cartItemRemoveButton);
            wishlist = (Button) itemView.findViewById(R.id.cartMoveToWishlistButton);
            wishOrremove = (LinearLayout)itemView.findViewById(R.id.wishorremove);
            mAuth = FirebaseAuth.getInstance();


            ArrayAdapter<CharSequence> Typead = ArrayAdapter.createFromResource(context,
                    R.array.quantity,R.layout.spinner_item);

            Typead.setDropDownViewResource(R.layout.spinner_dropdown);
            quantity.setAdapter(Typead);
//            quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    Cartitem.onCartItemClick(databasePojos.get(position),position+1);
//
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//            });


//            quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                    Cartitem.onCartItemClick(databasePojos.get(i),position);
//
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> adapterView) {
//
//                }
//            });

        }
    }

}