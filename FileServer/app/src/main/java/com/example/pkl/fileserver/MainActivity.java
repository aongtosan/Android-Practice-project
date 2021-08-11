package com.example.pkl.fileserver;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;
import android.provider.MediaStore;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.graphics.Bitmap;

public class MainActivity extends AppCompatActivity {

    public String filePath=null;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Permission StrictMode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // btnGallery
        final ImageButton gallery_btn = (ImageButton) findViewById(R.id.btnGallery);
        gallery_btn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(Intent.createChooser(intent, "Select photo from"), 0);
                }
            }
        });

        final ImageButton btn1 =(ImageButton)findViewById(R.id.camera_btn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager())!= null){
                    startActivityForResult(intent, 1);
                }
            }
        });

        final ImageButton btn4 = (ImageButton) findViewById(R.id.audio_btn);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                if(intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 2);
                }
            }
        });

        // btnUpload
        Button btnUpload = (Button)findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(SaveData()){
                    Toast.makeText(MainActivity.this,"File has been uploaded. " ,Toast.LENGTH_LONG).show();
                }
            }
        });

        // btnDownload
        Button btnDownload = (Button) findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int count;
                String from = "http://www.lstpch.com/android/plus.jpg";
                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()+"/";
                //String path = "/storage/emulated/0/";
                try {
                    URL url = new URL(from);
                    URLConnection con = url.openConnection();
                    con.connect();
                    InputStream input = new BufferedInputStream(url.openStream());
                    String fileName = from.substring(from.lastIndexOf('/')+1, from.length());
                    OutputStream output = new FileOutputStream(path+fileName); // save to file path
                    byte data[] = new byte[1024];
                    while ((count = input.read(data)) != -1) {
                        output.write(data, 0, count);
                    }
                    output.flush();
                    output.close();
                    input.close();
                    Toast.makeText(MainActivity.this,"File has been downloaded. " ,Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this,"Download Error!" ,Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }//end onCreate

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode== 0 && resultCode == Activity.RESULT_OK) {
            try {
                Uri uri = data.getData();
                filePath = getImageFilePath(uri);
                try {
                    ImageView imageView = (ImageView) findViewById(R.id.imageView);
                    imageView.getLayoutParams().height = 400;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                Log.e("Log", "Error on saving file");
            }
        }

        if (requestCode== 1 && resultCode == Activity.RESULT_OK) {
            try {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.getLayoutParams().height = 400;
                imageView.setImageBitmap(imageBitmap);

               File file = new File(this.getFilesDir(), "tem.jpg");
                try(FileOutputStream out = this.openFileOutput("tem.jpg", this.MODE_PRIVATE)) {
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                filePath =  this.getFilesDir() + "/" + "tem.jpg";

            } catch (Exception e) {
                Log.e("Log", "Error from Camera Activity");
            }
        }

        if (requestCode== 2 && resultCode == Activity.RESULT_OK && data!=null) {
            try {
                Uri uri = data.getData();
                try {
                    File file = new File(this.getFilesDir(), "temAudit.wav");
                    InputStream inStream = this.getContentResolver().openInputStream(uri);
                    byte[] buff = new byte[inStream.available()];
                    inStream.read(buff);
                    try(FileOutputStream fos = this.openFileOutput("temAudit.wav", this.MODE_PRIVATE)) {
                        fos.write(buff);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    filePath =  this.getFilesDir() + "/" + "temAudit.wav";
                    MediaPlayer mPlayer = new MediaPlayer();
                    mPlayer.setDataSource(filePath);
                    mPlayer.prepare();
                    mPlayer.start();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImageView imageAudio = (ImageView) findViewById(R.id.imageView);
                imageAudio.setImageResource(R.drawable.ic_sound);
            } catch (Exception e) {
                Log.e("Log", "Error from Audio Activity");
            }
        }

    }//end onActivityResult

    public boolean SaveData() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.err_title);
        dialog.setIcon(android.R.drawable.ic_menu_info_details);
        dialog.setPositiveButton("Close", null);

        if (filePath != null) {
            String strUrlServer = "http://www.lstpch.com/android/uploadFile.php";
            String resultServer = uploadFiletoServer(filePath, strUrlServer);
            String strStatusID = "0";
            String strError = "Unknow Status!";
            try {
                JSONObject c = new JSONObject(resultServer);
                strStatusID = c.getString("StatusID");
                strError = c.getString("Error");
            } catch (JSONException e) {
                Toast.makeText(MainActivity.this,"Upload Error!" ,Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            if (strStatusID.equals("0")) {
                dialog.setMessage(strError);
                dialog.show();
                return false;
            } else {
                dialog.setTitle(R.string.submit_title);
                dialog.setMessage(R.string.submit_result);
                dialog.show();
                filePath = null;
                return true;
            }
        }else {
            Toast.makeText(MainActivity.this,"File not found!" ,Toast.LENGTH_LONG).show();
            return false;
        }
    }//end saveData

    public String uploadFiletoServer(String strFilePath, String strUrlServer) {
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        int resCode = 0;
        String resMessage = "";
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary =  "*****";
        try {
            File file = new File(strFilePath);
            if(!file.exists())
            {
                return "{\"StatusID\":\"0\",\"Error\":\"Please check file path\"}";
            }
            FileInputStream fileInputStream = new FileInputStream(new File(strFilePath));
            URL url = new URL(strUrlServer);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream());
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"filUpload\";filename=\"" + strFilePath + "\"" + lineEnd);
            outputStream.writeBytes(lineEnd);

            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            while (bytesRead > 0) {
                outputStream.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            resCode = conn.getResponseCode();
            if(resCode == HttpURLConnection.HTTP_OK){
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read = 0;
                while ((read = is.read()) != -1) {
                    bos.write(read);
                }
                byte[] result = bos.toByteArray();
                bos.close();
                resMessage = new String(result);
            }

            Log.d("resCode=",Integer.toString(resCode));
            Log.d("resMessage=",resMessage);

            fileInputStream.close();
            outputStream.flush();
            outputStream.close();

            return resMessage;

        } catch (Exception ex) {
            Log.e("Log", "uploadFiletoServer error");
            ex.printStackTrace();
            return null;
        }
    }//end uploadFiletoServer

    public String getImageFilePath(Uri uri) {
        String path = null, image_id = null;

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            image_id = cursor.getString(0);
            image_id = image_id.substring(image_id.lastIndexOf(":") + 1);
            cursor.close();
        }
        cursor = getContentResolver().query(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + " = ? ", new String[]{image_id}, null);
        if (cursor!=null) {
            cursor.moveToFirst();
            path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor.close();
        }
        return path;
    }
}//end Class