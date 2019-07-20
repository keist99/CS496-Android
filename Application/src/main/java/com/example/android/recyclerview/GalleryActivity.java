package com.example.android.recyclerview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;

/**
 * Created by User on 1/2/2018.
 */

public class GalleryActivity extends Activity{

    private static final String TAG = "GalleryActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();
    }
    //이미지 url과 이미지 이름 가져오기
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        String phoneName = getIntent().getStringExtra("name_key");
        String phoneNumber = getIntent().getStringExtra("number_key");

        byte[] arr = getIntent().getByteArrayExtra("image_key");
        Bitmap phoneImage = BitmapFactory.decodeByteArray(arr, 0, arr.length);
//        ImageView BigImage = (ImageView)findViewById(R.id.BigImage);
//        BigImage.setImageBitmap(image);



        //Bitmap phoneImage = (Bitmap) getIntent().getParcelableExtra("image_key");

        setImage(phoneName, phoneNumber, phoneImage);


    }


    private void setImage(String phoneName, String phoneNumber, Bitmap phoneImage){
        Log.d(TAG, "setImage: setting te image and name to widgets.");
        //일단은 mDataset에서의 한 줄을 가져와보면
        TextView name = findViewById(R.id.name_text);
        TextView number = findViewById(R.id.number_text);
        ImageView image = findViewById(R.id.image_view);


        name.setText(phoneName);
        number.setText(phoneNumber);
        image.setImageBitmap(phoneImage);

        //스트링인 image_url을 가져와서 url을 이용해서 이미지 보이게 하는 코드인듯
        //이미지는 주영씨한테 물어봐서
//        ImageView image = findViewById(R.id.image);
//        Glide.with(this)
//                .asBitmap()
//                .load(imageUrl)
//                .into(image);
    }

}