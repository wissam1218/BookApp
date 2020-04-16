package com.example.bookapp;

import android.os.Bundle;
import android.os.Environment;
import com.github.barteksc.pdfviewer.PDFView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;

import static com.example.bookapp.MainActivity.classNumber;
import static com.example.bookapp.MainActivity.lesson;

public class PDFViewer extends AppCompatActivity {
    // User must ensure that both class number and lesson number are entered in the main menu correctly
    // these two variables will be used to load the correct pdf file

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);

        // The correct PDF file is found in the Classrooms folder

        String filepath = Environment.getExternalStorageDirectory().getAbsoluteFile()
                +"/Classrooms/class"+ classNumber + "/Curriculum/Lesson "+lesson+"/pdf_for_lesson.pdf";
        File file = new File(filepath);
        PDFView pdf = findViewById(R.id.pdfView);

        pdf.fromFile(file)
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
                .fitEachPage(false) // fit each page to the view, else smaller pages are scaled relative to largest page.
                .pageSnap(false) // snap pages to screen boundaries
                .pageFling(false) // make a fling change only a single page like ViewPager
                .nightMode(false) // toggle night mode
                .load();
    }
}
