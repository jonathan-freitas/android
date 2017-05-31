package br.com.senaigo.mobile.northwindtraders.entities;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bruno on 03/04/16.
 */
public class Category implements Parcelable{


    /*Propriedades da entidade para o SQLITE*/
    public static final String TABELA= "Category ";
    public static final String KEY_CATEGORY_ID = "categoryId";
    public static final String KEY_CATEGORY_NAME = "categoryName";
    public static final String KEY_CATEGORY_DESCRIPTION = "description";
    public static final String KEY_CATEGORY_PICTURE = "picture";

    protected Integer categoryId;
    protected String categoryName;
    protected String description;
    protected Bitmap picture;


    protected Category(Parcel in) {
        categoryId = in.readInt();
        categoryName = in.readString();
        description = in.readString();
        picture = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(categoryId);
        dest.writeString(categoryName);
        dest.writeString(description);
        dest.writeValue(picture);
    }

    public Category() {
    }

    public Category(Integer categoryId, String categoryName, String description, Bitmap picture) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.picture = picture;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", picture=" + picture +
                '}';
    }
}
