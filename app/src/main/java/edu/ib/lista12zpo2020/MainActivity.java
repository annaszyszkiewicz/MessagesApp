package edu.ib.lista12zpo2020;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * klasa aktywnosci glownego okna aplikacji, w ktorym wpisywana jest wiaodmosc i klikany przycisk wysylania wiaodmosci
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    public static final String FOLDERNAME = "Messages";
    public static final String TAG = "EDUIB";

    ArrayList<Message> messages = new ArrayList<>();

    /**
     * metoda tworzaca folder z wiadomosciami oraz historie wiadomosci
     * po uruchomieniu aplikacji
     *
     * @param savedInstanceState zachowanie danych aplikacji
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        File folder = new File(String.valueOf(getExternalFilesDir(FOLDERNAME)));
        FileFilter filter = pathname -> pathname.getName().endsWith(".txt");

        File[] table = folder.listFiles(filter);
        for (int i = 0; i < table.length; i++) {
            try (FileInputStream is = new FileInputStream(table[i])) {
                int n = is.read();
                StringBuilder fileString = new StringBuilder();
                while (n != -1) {
                    fileString.append((char) n);
                    n = is.read();
                }
                messages.add(0, new Message(table[i].getName(), fileString.toString()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RecyclerView rvMessage = (RecyclerView) findViewById(R.id.rvMsg);
        MessageAdapter adapter = new MessageAdapter(messages);
        rvMessage.setAdapter(adapter);
        rvMessage.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * metoda umozliwiajaca zapisanie wiadomosci w pliku,
     * wyslanie wiaodmosci w odpowiednie miejsce
     * i dodanie wiaodmosci do historii wiadomosci
     * po kliknieciu przycisku "ENTER"
     *
     * @param view
     */
    public void onEnter(View view) {
        Calendar time = Calendar.getInstance();
        String filename = (time.getTime().toString()) + ".txt";
        RecyclerView rvMessage = (RecyclerView) findViewById(R.id.rvMsg);
        EditText txtMessage = (EditText) findViewById(R.id.txtMsg);

        Log.d(TAG, txtMessage.getText().toString());
        // File means file path
        File myExternalFile = new File(getExternalFilesDir(FOLDERNAME), filename);

        // try with resources
        try (FileOutputStream os = new FileOutputStream(myExternalFile)) {
            ;
            os.write(txtMessage.getText().toString().getBytes());
            os.close();
            Toast.makeText(this, "File saved", Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            Log.e(TAG, e.toString());
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, txtMessage.getText().toString());

        startActivity(intent);

        Message m = new Message(filename, txtMessage.getText().toString());
        messages.add(0, m);
        rvMessage.getAdapter().notifyItemInserted(messages.indexOf(m));

    }
}