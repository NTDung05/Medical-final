package com.example.medical;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medical.Custom.customListview_Thuoc;
import com.example.medical.Data.dataBase_Thuoc;
import com.example.medical.model.Thuoc;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class Thuoc_activity extends AppCompatActivity {
    EditText edTenthuoc, edDVT, edDongia;
    ImageView edImg;
    Button btThem, btXoa, btSua;
    ImageButton btUpfile;
    ListView lvThuoc;
    private static int REQUEST = 123;
    private static final int PICK_IMAGE = 1;
    private static int maThuoc;
    private customListview_Thuoc customAdapter;
    private List<Thuoc> listThuoc;
    dataBase_Thuoc db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin Thuốc");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.info_thuoc);
        db = new dataBase_Thuoc(this);
        listThuoc = db.getAllThuoc();

        setControl();
        setEvent();
        setAdapter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("WrongViewCast")
    private void setControl() {
        edTenthuoc = (EditText) findViewById(R.id.edTenthuoc);
        edDongia = (EditText) findViewById(R.id.edDongia);
        edDVT = (EditText) findViewById(R.id.edDVT);
        btThem = (Button) findViewById(R.id.btThem);
        btSua = (Button) findViewById(R.id.btSua);
        btXoa = (Button) findViewById(R.id.btXoa);
        lvThuoc = (ListView) findViewById(R.id.lvThuoc);
        btUpfile = (ImageButton) findViewById(R.id.btUphinhanh);
        edImg = (ImageView) findViewById(R.id.tvsrc);
    }

    private void setEvent() {
        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edTenthuoc.getText().toString();
                String dvt = edDVT.getText().toString();
                String gia = edDongia.getText().toString();

                if (ten.equals("") || dvt.equals("") || gia.equals("")) {
                    Toast.makeText(getApplicationContext(), " Vui long nhap đay du thong tin", Toast.LENGTH_LONG).show();
                } else {
                    Thuoc thuoc = createThuoc();
                    if (thuoc != null) {
                        db.addThuoc(thuoc);
                    }
                    listThuoc.clear();
                    listThuoc.addAll(db.getAllThuoc());
                    setAdapter();
                }

            }
        });

        lvThuoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edTenthuoc.setText(listThuoc.get(position).getTenThuoc());
                edDongia.setText(listThuoc.get(position).getDonGia());
                edDVT.setText(listThuoc.get(position).getDVT());
                Bitmap bitmap = BitmapFactory.decodeByteArray(listThuoc.get(position).getImg(), 0, listThuoc.get(position).getImg().length);
                edImg.setImageBitmap(bitmap);
                maThuoc = listThuoc.get(position).getMaThuoc();
                btThem.setEnabled(true);
                btSua.setEnabled(true);
                btXoa.setEnabled(true);
            }
        });

        btXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteThuoc(maThuoc);
                updateList();
                btXoa.setEnabled(false);
                btThem.setEnabled(true);
                btSua.setEnabled(false);
            }
        });
        btUpfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(gallery.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Chọn Hình Ảnh"), PICK_IMAGE);
            }
        });

        btSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thuoc thuoc = new Thuoc();
                thuoc.setMaThuoc(maThuoc);
                thuoc.setTenThuoc(edTenthuoc.getText().toString());
                thuoc.setDVT(edDVT.getText().toString());
                thuoc.setDonGia(edDongia.getText().toString());
                thuoc.setImg(ConverttoArrayByte(edImg));
                int i = db.updateThuoc(thuoc);
                if (i <= 0) {
                    Toast.makeText(getApplicationContext(), " UPDATE không thành công", Toast.LENGTH_LONG).show();
                } else {
                    updateList();
                }
                btThem.setEnabled(true);
                btSua.setEnabled(false);
            }
        });
    }

    private Thuoc createThuoc() {
        String tenThuoc = edTenthuoc.getText().toString();
        String DVT = edDVT.getText().toString();
        String donGia = edDongia.getText().toString();
        byte[] img = ConverttoArrayByte(edImg);
        Thuoc thuoc = new Thuoc(tenThuoc, DVT, donGia, img);
        return thuoc;
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new customListview_Thuoc(this, R.layout.custom_listview_thuoc, listThuoc);
            lvThuoc.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            lvThuoc.setSelection(customAdapter.getCount() - 1);
        }
    }

    public void updateList() {
        listThuoc.clear();
        listThuoc.addAll(db.getAllThuoc());
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 600, 600, true);

                edImg.setImageBitmap(bitmap1);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public byte[] ConverttoArrayByte(ImageView img) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
