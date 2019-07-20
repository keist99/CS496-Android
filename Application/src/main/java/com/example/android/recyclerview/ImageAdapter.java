package com.example.android.recyclerview;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private ArrayList<String> thumbsDataList;
    private ArrayList<String> thumbsIDList;
    private Context mContext;

    public ImageAdapter(Context c){
        mContext = c;
        thumbsDataList = new ArrayList<String>();
        thumbsIDList = new ArrayList<String>();
        getThumbInfo(thumbsIDList, thumbsDataList);
    }

    @Override
    public int getCount() {
        return thumbsIDList.size();
    }

    public boolean deleteSelected(int sIndex){
        return true;
    }

    @Override
    public Object getItem(int arg0) {
        return arg0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 주어진 위치(position)에 출력할 이미지를 반환함
        ImageView imageView;
        if (convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 450));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);

        } else {
            imageView = (ImageView) convertView;
        }
        BitmapFactory.Options bo = new BitmapFactory.Options();
        bo.inSampleSize = 8;
        Bitmap bmp = BitmapFactory.decodeFile(thumbsDataList.get(position), bo);
        Bitmap resized = Bitmap.createScaledBitmap(bmp, 95, 95, true);
        imageView.setImageBitmap(resized); // 이미지 뷰에 주어진 위치의 이미지를 설정함
        return imageView;
    }
    private void getThumbInfo(ArrayList<String> thumbsIDs, ArrayList<String> thumbsDatas){
        String[] proj = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE };

        Cursor imageCursor = mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, proj, null, null, null);

        if (imageCursor != null && imageCursor.moveToFirst()){
            String thumbsID;
            String thumbsImageID;
            String thumbsData;

            int thumbsIDCol = imageCursor.getColumnIndex(MediaStore.Images.Media._ID);
            int thumbsDataCol = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
            int thumbsImageIDCol = imageCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);

            do {
                thumbsID = imageCursor.getString(thumbsIDCol);
                thumbsData = imageCursor.getString(thumbsDataCol);
                thumbsImageID = imageCursor.getString(thumbsImageIDCol);
                if (thumbsImageID != null){
                    thumbsIDs.add(thumbsID);
                    thumbsDatas.add(thumbsData);
                }
            } while (imageCursor.moveToNext());
        }
        imageCursor.close();
        return;
    }
}
