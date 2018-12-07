package com.example.yasua.gridimageview;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// http://android-note.open-memo.net/sub/layout--show-images-with-GridView.html
// https://developer.android.com/training/permissions/requesting?hl=ja
// https://developer.android.com/guide/topics/data/data-storage?hl=ja#filesInternal
// 設定のアプリ一覧からストレージのアクセスを許可する必要がある．
// https://qiita.com/yamacraft/items/b1cfe43e52cf3c58a046
public class GridActivity extends AppCompatActivity
{
  private GridView gridView;
  private GridAdapter gridAdapter;
  private static final int REQUEST_CODE_PERMISSION = 2;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    // Assume thisActivity is the current activity
    // Here, thisActivity is the current activity
    if (ContextCompat.checkSelfPermission(this,
        Manifest.permission.READ_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {

      // Should we show an explanation?
      if (ActivityCompat.shouldShowRequestPermissionRationale(this,
          Manifest.permission.READ_EXTERNAL_STORAGE)) {
        // Show an expanation to the user *asynchronously* -- don't block
        // this thread waiting for the user's response! After the user
        // sees the explanation, try again to request the permission.
      } else {

        // No explanation needed, we can request the permission.

        ActivityCompat.requestPermissions(this,
            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
            REQUEST_CODE_PERMISSION);
        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
        // app-defined int constant. The callback method gets the
        // result of the request.
      }
    }
    else{
      // 許可されている、またはAndroid 6.0以前
      gridView = (GridView) findViewById(R.id.gridView);
      // Here, thisActivity is the current activity
      List<String> imgFilePaths = new ArrayList<String>();
      File[] imgFiles = new File("/storage/emulated/0/Pictures/Clipper/").listFiles();
      for(File imgFile : imgFiles){
        imgFilePaths.add(imgFile.getAbsolutePath());
      }

      /** アダプターをGridViewに設定。 */
      gridAdapter = new GridAdapter(this, R.layout.grid_item, imgFilePaths);
      gridView.setAdapter(gridAdapter);
    }
  }
}
