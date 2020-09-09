package caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.controller;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.R;
import caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.Lang;
import caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.Vocab;
import caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.VocabRepository;
import caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.utils.Utils;
import  caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.music;

import static caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.utils.Utils.KEY_DIFFICULTY;
import static caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.utils.Utils.KEY_GROUP_NAME;
import static caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.utils.Utils.KEY_SUBJECT;

/**
 * the Welcome page, first page when app launched
 */
public class WelcomeActivity extends AppCompatActivity {

    private static final int PICKFILE_RESULT_CODE = 1;
    private final static int REQUEST_CODE_PERMISSION = 1;
    private final static String KEY_BG = "BackgroundMusic";

    private static String[] DIFFICULTY_LEVELS = new String[]{"Easy", "Normal", "Hard", "Tough"};

    private OnPermissionsCallback permissionsCallback;
    private VocabRepository repository;

    private int bgLength = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide the bars
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            bgLength = savedInstanceState.getInt(KEY_BG);

        }
        music.play(this, R.raw.welcome1,bgLength,WelcomeActivity.class.getName());

        repository = VocabRepository.getInstance();

        // Check if the app is opened for the first time, if so, fill in the vocabulary database
        boolean isFirst = Utils.getBoolean(this, Utils.KEY_ALL_NEW, true);
        if (isFirst) {
            List<Vocab> vocabs = new ArrayList<>();
            String[] colors_c = getResources().getStringArray(R.array.Colors_English);
            String[] colors_e = getResources().getStringArray(R.array.Colors_Chinese);
            for (int i = 0; i < colors_c.length; i++) {
                vocabs.add(new Vocab(colors_c[i], Lang.EN, colors_e[i], Lang.CN, "color"));
            }
            String[] number_c = getResources().getStringArray(R.array.Numbers_English);
            String[] number_e = getResources().getStringArray(R.array.Numbers_Chinese);
            for (int i = 0; i < colors_c.length; i++) {
                vocabs.add(new Vocab(number_c[i], Lang.EN, number_e[i], Lang.CN, "number"));
            }
            repository.insertBatch(vocabs);
            Utils.saveBoolean(this, Utils.KEY_ALL_NEW, false);
        }

        permissionsCallback = success -> {
            if (!success) {
                Toast.makeText(this, "Location permission denied,we can't gather vehicle location info.", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("text/*");
            startActivityForResult(intent, PICKFILE_RESULT_CODE);
        };
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bgLength = music.pause(WelcomeActivity.class.getName());
        outState.putInt(KEY_BG,bgLength);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play_button:
                music.stop(WelcomeActivity.class.getName());
                startActivity(new Intent(this, GameActivity.class));
                break;
            case R.id.bt_import:
                // check location permission and start locate
                checkPermissions(permissionsCallback);
                break;
            case R.id.bt_level:
                choseDifficulty();
                break;
            case R.id.bt_subject:
                onSubjectSelect();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        music.stop(WelcomeActivity.class.getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        music.play(this, R.raw.welcome1,bgLength,WelcomeActivity.class.getName());
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;
        if (requestCode == PICKFILE_RESULT_CODE) {
            if (data == null) return;
            Uri uri = data.getData();
            showLangDialog(uri);
        }
    }

    private void onSubjectSelect() {
        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.activity_subject_selection, null, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        List<String> list = repository.getAllGroupsName();
        // Initializing spinner object
        // Initializing array adapter object
        Spinner subjectSpinner = dialogView.findViewById(R.id.subjects_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // layout of spinner
        subjectSpinner.setAdapter(adapter);  // add adapter to native language spinner
        AlertDialog alertDialog = builder.setView(dialogView).setTitle("Please select the Subject").setPositiveButton("OK", null).create();

        alertDialog.setOnShowListener(dialog -> {
            Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
            button.setOnClickListener(view -> {
                // get selected subject
                String selected_subject = subjectSpinner.getSelectedItem().toString();
                Utils.saveString(WelcomeActivity.this, KEY_SUBJECT, selected_subject);
                dialog.dismiss();
            });
        });

        //finally creating the alert dialog and displaying it
        alertDialog.show();
    }

    /**
     * Show a dialog to load a custom csv file
     */
    private void showLangDialog(final Uri uri) {

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.fragment_language_selection, null, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Initializing spinner objects
        final Spinner firstSpinner = dialogView.findViewById(R.id.nativeLanguage_spinner);
        final Spinner secondSpinner = dialogView.findViewById(R.id.foreignLanguage_spinner);
        EditText etName = dialogView.findViewById(R.id.et_name);
        etName.setVisibility(View.VISIBLE);
        ((TextView) dialogView.findViewById(R.id.tv_lang_first)).setText("First column Language");
        ((TextView) dialogView.findViewById(R.id.tv_lang_second)).setText("Second column Language");
        ArrayAdapter<Lang> langAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Lang.values());
        langAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // layout of spinner
        // Initializing array adapter object
        firstSpinner.setAdapter(langAdapter);  // add adapter to native language spinner
        firstSpinner.setSelection(0);
        secondSpinner.setAdapter(langAdapter); // add adapter to foreign language spinner
        secondSpinner.setSelection(1);
        //setting the view of the builder to our custom view that we already inflated
        AlertDialog alertDialog = builder.setView(dialogView).setTitle("Please select the language of the file you imported").setPositiveButton("OK", null).create();

        alertDialog.setOnShowListener(dialog -> {
            Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
            button.setOnClickListener(view -> {
                Lang firstLang = (Lang) firstSpinner.getSelectedItem();
                Lang secondLang = (Lang) secondSpinner.getSelectedItem();

                // open next activity if user selected two different languages
                if (secondLang.code == firstLang.code) {
                    String error = "Please select two different languages";
                    Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                    return;
                }
                String name = etName.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(this, "Please enter the group name of this set of words!", Toast.LENGTH_SHORT).show();
                    return;
                }

                InputStream inputStream;
                try {
                    ContentResolver resolver = this.getContentResolver();
                    inputStream = resolver.openInputStream(uri);

                    Map<String, String> content = Utils.read(inputStream);
                    List<Vocab> list = new ArrayList<>();
                    Utils.saveString2Set(this, KEY_GROUP_NAME, name);
                    for (Map.Entry<String, String> entry : content.entrySet()) {
                        list.add(new Vocab(entry.getKey(), firstLang, entry.getValue(), secondLang, name));
                    }
                    repository.insertBatch(list);
                    Toast.makeText(this, "Import Success!", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "File not exist!", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            });
        });

        //finally creating the alert dialog and displaying it
        alertDialog.show();
    }


    private void choseDifficulty() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, DIFFICULTY_LEVELS);
        builder.setAdapter(arrayAdapter, (dialog, which) -> Utils.saveInt(this, KEY_DIFFICULTY, which)).create().show();
    }

    interface OnPermissionsCallback {
        void onResult(boolean success);
    }

    /**
     * check user permissions
     */
    private void checkPermissions(OnPermissionsCallback callback) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionsCallback = callback;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION);
        } else {
            callback.onResult(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION: {
                if (permissionsCallback == null) break;
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionsCallback.onResult(true);
                } else {
                    permissionsCallback.onResult(false);
                }
                break;
            }
        }
    }


}
