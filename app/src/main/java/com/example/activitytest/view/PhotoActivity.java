package com.example.activitytest.view;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.Selection;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.activitytest.ListView.PersonActivity;
import com.example.activitytest.R;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

public class PhotoActivity extends BaseActivity {

    public static final int take_photo=1;
    public  static final int CHOOSE_PHOTO=2;

    private ImageView im_photo;
    private Button btn_photo,btn_getphoto;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        im_photo= (ImageView) findViewById(R.id.im_photo);
        btn_photo= (Button) findViewById(R.id.btn_photo);

        btn_getphoto= (Button) findViewById(R.id.btn_getphoto);

        btn_getphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //申请读写SD卡的权限
                if (ContextCompat.checkSelfPermission(PhotoActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(PhotoActivity.this,new String[]{
                            "Manifest.permission.WRITE_EXTERNAL_STORAGE"
                    },22);

                }else {
                    openAlbum();

                }


            }
        });


        //调用相机拍照
        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File MyPicture=new File(getExternalCacheDir(),"MyPicture.jpg");
                try{
                    if (MyPicture.exists()){
                        MyPicture.delete();
                    }

                    MyPicture.createNewFile();
                } catch (IOException e) {

                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT>=24){

                    imageUri= FileProvider.getUriForFile(PhotoActivity.this,
                            "nihao",MyPicture);
                }else {

                    imageUri=Uri.fromFile(MyPicture);
                }

                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,take_photo);

            }
        });
    }
    //打开相册
    public void openAlbum(){
        Intent intent1=new Intent("android.intent.action.GET_CONTENT");
        intent1.setType("image/*");
        startActivityForResult(intent1,CHOOSE_PHOTO);

    }

    //判断用户是否给权限
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 22:
                if (grantResults.length>0  && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();

                }else {

                    Toast.makeText(PhotoActivity.this,"获取权限失败",Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;

        }
    }
    //


    //获取拍照的照片并设置到imageview中
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case take_photo:
                if(resultCode==RESULT_OK){
                    try{
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        im_photo.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode==RESULT_OK){
                    if (Build.VERSION.SDK_INT>=19){

                        //android4.4以上版本使用这个方法处理照片
                        AfterKitKat(data);


                    }else {
                        //android4.4之前的版本使用这个方法处理照片

                        BeforeKitKat(data);
                    }


                }


            default:
                break;



        }
    }

    private void AfterKitKat(Intent data){
        String imagePath=null;
        Uri uri=data.getData();
        if (DocumentsContract.isDocumentUri(PhotoActivity.this,uri)){
            //如果是document类型的uri,则通过document_id来处理
            String docuId=DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id=docuId.split(":")[1]; //解析出数字格式的id
                String selection=MediaStore.Images.Media._ID+"="+id;
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if ("com.android.provides.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri= ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads")
                        ,Long.valueOf(docuId));
                imagePath=getImagePath(contentUri,null);

            }else if ("content".equalsIgnoreCase(uri.getScheme())){
                //如果是content类型的uri,则使用普通方式去处理
                imagePath=getImagePath(uri,null);

            }else if ("file".equalsIgnoreCase(uri.getScheme())){
                //如果是file类型的uri,直接获取图片路径即可
                imagePath=uri.getPath();
            }
            displayImage(imagePath);//根据图片路径显示图片
        }


    }
    private void BeforeKitKat(Intent data){
        Uri uri=data.getData();
        String imagePath=getImagePath(uri,null);
        displayImage(imagePath);

    }

    private String getImagePath(Uri uri,String selection){
        String path=null;
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){

            if (cursor.moveToFirst()){

                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

            }

            cursor.close();
        }

        return path;

    }

    private void displayImage(String imagepath){
        if (imagepath!=null){

            Bitmap bitmap=BitmapFactory.decodeFile(imagepath);
            im_photo.setImageBitmap(bitmap);
        }else{
            Toast.makeText(PhotoActivity.this,"获取照片失败",Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.back,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Menuback:
                finish();
            break;
            default:
                break;
        }
        return true;
    }
}