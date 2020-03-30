package com.example.bookapp;

import android.content.Intent;
import android.os.Bundle;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.util.regex.Pattern;

public class PDFViewer extends AppCompatActivity {
    // a pdf must be chosen from storage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(1)
                .withFilter(Pattern.compile(".*\\.pdf$")) // filter names with a regex
                .withFilterDirectories(true) // directories filterable
                .withHiddenFiles(true) // show hidden files/folders
                .start();
    }
    // when pdf is chosen, we load pdf in a separate activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String filepath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            File file = new File(filepath);
            PDFView pdf = findViewById(R.id.pdfView);
            pdf.fromFile(file).enableSwipe(true)
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .enableAnnotationRendering(false)
                    .password(null)
                    .scrollHandle(null)
                    .enableAntialiasing(true)
                    .spacing(0)
                    .autoSpacing(true)
                    .pageFitPolicy(FitPolicy.WIDTH)
                    .fitEachPage(false)
                    .nightMode(true)
                    .load();
        }
    }
}
