package com.example.yasua.gridimageview;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.GridView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

// http://android-note.open-memo.net/sub/layout--show-images-with-GridView.html
// https://developer.android.com/training/permissions/requesting?hl=ja
// https://developer.android.com/guide/topics/data/data-storage?hl=ja#filesInternal
public class GridActivity extends Activity
{
  private String filePath;

  private GridView gridView;
  private GridAdapter gridAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    /** GridViewの取得 */
    gridView = (GridView) findViewById(R.id.gridView);
    // Here, thisActivity is the current activity
    /** 画像フォルダから画像パスの一覧を取得する。 */
    List<String> imgFilePaths = new ArrayList<String>();
    File[] imgFiles = new File("/storage/").listFiles();
    for(File imgFile : imgFiles){
      imgFilePaths.add(imgFile.getAbsolutePath());
    }

    /** アダプターをGridViewに設定。 */
    gridAdapter = new GridAdapter(this, R.layout.grid_item, imgFilePaths);
    gridView.setAdapter(gridAdapter);
  }
}
