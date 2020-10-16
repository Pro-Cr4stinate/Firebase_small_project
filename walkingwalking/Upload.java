package com.example.walkingwalking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.InetAddresses;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Random;

public class Upload extends AppCompatActivity {

    private static final int MAX_LENGTH = 8 ;
    private ImageButton UpImage;
    private EditText Memlocation;
    private EditText Memdate;
    private EditText Memdesc;
    private Button MemUpload;
    private Uri UPImageUri = null;
    private ProgressBar progbar;
    private StorageReference FBstorage;
    private DatabaseReference FBdatabase;
    private static final int GALLERY_REQ = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        UpImage = (ImageButton) findViewById(R.id.imageUP);
        Memlocation = (EditText) findViewById(R.id.Location);
        Memdate = (EditText) findViewById(R.id.Date);
        Memdesc = (EditText) findViewById(R.id.Desc);
        MemUpload = (Button) findViewById(R.id.Upload);
        progbar = (ProgressBar)findViewById(R.id.progbar);

        FBstorage = FirebaseStorage.getInstance().getReference("Images");
        FBdatabase = FirebaseDatabase.getInstance().getReference("Images");

        UpImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryintent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryintent.setType("image/*");
                startActivityForResult(galleryintent, GALLERY_REQ);
            }
        });


        MemUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostToDB();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQ && resultCode == RESULT_OK) {
            UPImageUri = data.getData();
            UpImage.setImageURI(UPImageUri);
        }
    }
    private String FileEXT(Uri uri){
        ContentResolver CR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(CR.getType(uri));
    }
    private void PostToDB() {
        final String Location_value = Memlocation.getText().toString().trim();
        final String Date_value = Memdate.getText().toString().trim();
        final String Description_value = Memdesc.getText().toString().trim();
        if (UPImageUri == null){
            Toast.makeText(this, "No Image selected", Toast.LENGTH_SHORT).show();
        }else{
            StorageReference storeref = FBstorage.child(System.currentTimeMillis() + "." + FileEXT(UPImageUri)).child(UPImageUri.getLastPathSegment());

            storeref.putFile(UPImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progbar.setProgress(0);
                        }
                    },500);
                    Toast.makeText(Upload.this, "Upload success", Toast.LENGTH_SHORT).show();
//                    GoUpload goupload = new GoUpload(Location_Value.getText().toString().trim(), Date_Value.getText().toString().trim(), taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
//                    String uploadID = FBdatabase.push().getKey();
//                    FBdatabase.child(uploadID).setValue(goupload);
                    DatabaseReference newref = FBdatabase.push();
                    newref.child("date").setValue(Date_value);
                    newref.child("location").setValue(Location_value);
                    newref.child("description").setValue(Description_value);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Upload.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred()/ taskSnapshot.getTotalByteCount());
                    progbar.setProgress((int) progress);
                }
            });
        }


    }


    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(MAX_LENGTH);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
}
