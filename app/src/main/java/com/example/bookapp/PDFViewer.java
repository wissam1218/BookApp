package com.example.bookapp;

import android.content.Intent;
import android.os.Bundle;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import java.io.File;
import java.util.regex.Pattern;

public class PDFViewer extends AppCompatActivity {
    public static String filepath = "";
    // the initial path will point too "math.pdf"
    public static String path = "/storage/emulated/0/math.pdf";
    TextView tvView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);
        tvView = findViewById(R.id.textView3);
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(1)
                .withFilter(Pattern.compile(".*\\.pdf$")) // Filtering files and directories by file name using regexp
                .withFilterDirectories(true) // Set directories filterable (false by default)
                .withHiddenFiles(true) // Show hidden files and folders
                .start();
        loadPDF();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
           filepath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            tvView.setText(filepath); // this path is shown to be correct
            path = filepath;
        }
    }
    // function to load specified pdf
    private void loadPDF(){
        File file = new File(path);
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
