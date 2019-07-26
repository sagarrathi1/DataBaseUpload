package com.lakhan.databaseupload;

public class DatabasePojo {

    private String product,type_product,sub_type,company,rc_code,Catalogue_Code,Item_Code,Item_Image,Item_Image1,Item_Image2,Item_Image3,Item_Name,Item_Price,Item_Sold_By;

    private String Item_Delivary_Charge,Item_Delivery_Time,Item_Discount_Price;

    private DatabasePojoItemSpecification databasePojoItemSpecification;

    private String Colour,Description,Finish_Type,Material,Size,Type;

    public DatabasePojo(){}

    public DatabasePojo(
            String product,
            String type,
            String sub_type,
            String company,
            String rc_code,
            String Catalogue_Code,
            String Item_Code,
            String Item_Delivary_Charge,
            String Item_Delivery_Time,
            String Item_Discount_Price,
            String Item_Image,
            String Item_Name,
            String Item_Price,
            String Item_Sold_By,
            String Colour,
            String Description,
            String Finish_Type,
            String Material,
            String Size,
            String Type)
    {

        this.Catalogue_Code = Catalogue_Code;
        this.company = company;
        this.product = product;
        this.type_product = type;
        this.sub_type = sub_type;
        this.rc_code = rc_code;
        this.Item_Code = Item_Code;
        this.Item_Image = Item_Image;
        this.Item_Name = Item_Name;
        this.Item_Price = Item_Price;
        this.Item_Sold_By = Item_Sold_By;
        this.Colour  = Colour;
        this.Description = Description;
        this.Finish_Type = Finish_Type;
        this.Material = Material;
        this.Size = Size;
        this.Type = Type;
        this.Item_Delivary_Charge = Item_Delivary_Charge;
        this.Item_Delivery_Time = Item_Delivery_Time;
        this.Item_Discount_Price = Item_Discount_Price;


    }




    public DatabasePojo(String product,String type,String company,String rc_code,String Catalogue_Code,String Item_Code,String Item_Image,String Item_Name,String Item_Price,String Item_Sold_By){

        this.Catalogue_Code = Catalogue_Code;
        this.company = company;
        this.product = product;
        this.type_product = type;
//        this.sub_type = sub_type;
        this.rc_code = rc_code;
        this.Item_Code = Item_Code;
        this.Item_Image = Item_Image;
        this.Item_Name = Item_Name;
        this.Item_Price = Item_Price;
        this.Item_Sold_By = Item_Sold_By;


    }

    public DatabasePojo(String product,
                        String type,
                        String rc_code,
                        String Catalogue_Code,
                        String Item_Code,
                        String Item_Delivary_Charge,
                        String Item_Delivery_Time,
                        String Item_Discount_Price,
                        String Item_Image,
                        String Item_Image1,
                        String Item_Image2,
                        String Item_Image3,
                        String Item_Name,
                        String Item_Price,
                        String Item_Sold_By,
                        String Colour,
                        String Description,
                        String Finish_Type,
                        String Material,
                        String Size,
                        String Type){

        this.Catalogue_Code = Catalogue_Code;
//        this.company = company;
        this.product = product;
        this.type_product = type;
//        this.sub_type = sub_type;
        this.rc_code = rc_code;
        this.Item_Code = Item_Code;
        this.Item_Image = Item_Image;
        this.Item_Image1 = Item_Image1;
        this.Item_Image2 = Item_Image2;
        this.Item_Image3 = Item_Image3;
        this.Item_Name = Item_Name;
        this.Item_Price = Item_Price;
        this.Item_Sold_By = Item_Sold_By;
        this.Colour  = Colour;
        this.Description = Description;
        this.Finish_Type = Finish_Type;
        this.Material = Material;
        this.Size = Size;
        this.Type = Type;
        this.Item_Delivary_Charge = Item_Delivary_Charge;
        this.Item_Delivery_Time = Item_Delivery_Time;
        this.Item_Discount_Price = Item_Discount_Price;




    }


    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRc_code() {
        return rc_code;
    }

    public void setRc_code(String rc_code) {
        this.rc_code = rc_code;
    }

    public String getCatalogue_Code() {
        return Catalogue_Code;
    }

    public void setCatalogue_Code(String catalogue_Code) {
        Catalogue_Code = catalogue_Code;
    }

    public String getItem_Code() {
        return Item_Code;
    }

    public void setItem_Code(String item_Code) {
        Item_Code = item_Code;
    }

    public String getItem_Image() {
        return Item_Image;
    }

    public void setItem_Image(String item_Image) {
        Item_Image = item_Image;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public void setItem_Name(String item_Name) {
        Item_Name = item_Name;
    }

    public String getItem_Price() {
        return Item_Price;
    }

    public void setItem_Price(String item_Price) {
        Item_Price = item_Price;
    }

    public String getItem_Sold_By() {
        return Item_Sold_By;
    }

    public void setItem_Sold_By(String item_Sold_By) {
        Item_Sold_By = item_Sold_By;
    }

    public DatabasePojoItemSpecification getDatabasePojoItemSpecification() {
        return databasePojoItemSpecification;
    }

    public void setDatabasePojoItemSpecification(DatabasePojoItemSpecification databasePojoItemSpecification) {
        this.databasePojoItemSpecification = databasePojoItemSpecification;
    }

    public String getColour() {
        return Colour;
    }

    public void setColour(String colour) {
        Colour = colour;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getFinish_Type() {
        return Finish_Type;
    }

    public void setFinish_Type(String finish_Type) {
        Finish_Type = finish_Type;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getType_product() {
        return type_product;
    }

    public void setType_product(String type_product) {
        this.type_product = type_product;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getItem_Delivery_Time() {
        return Item_Delivery_Time;
    }

    public void setItem_Delivery_Time(String item_Delivery_Time) {
        Item_Delivery_Time = item_Delivery_Time;
    }

    public String getItem_Delivary_Charge() {
        return Item_Delivary_Charge;
    }

    public void setItem_Delivary_Charge(String item_Delivary_Charge) {
        Item_Delivary_Charge = item_Delivary_Charge;
    }

    public String getItem_Discount_Price() {
        return Item_Discount_Price;
    }

    public void setItem_Discount_Price(String item_Discount_Price) {
        Item_Discount_Price = item_Discount_Price;
    }

    public String getItem_Image1() {
        return Item_Image1;
    }

    public void setItem_Image1(String item_Image1) {
        Item_Image1 = item_Image1;
    }

    public String getItem_Image2() {
        return Item_Image2;
    }

    public void setItem_Image2(String item_Image2) {
        Item_Image2 = item_Image2;
    }

    public String getItem_Image3() {
        return Item_Image3;
    }

    public void setItem_Image3(String item_Image3) {
        Item_Image3 = item_Image3;
    }
}
