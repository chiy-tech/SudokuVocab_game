package caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.controller;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.gridlayout.widget.GridLayout;
import caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.R;
import caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.Lang;
import caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.Sudoku;
import caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.Vocab;
import caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.VocabRepository;
import caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.utils.SudokuGenerator;
import caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.utils.Utils;
import  caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.music;

/**
 * sudoku game page
 */
public class GameActivity extends AppCompatActivity {
    /**
     * sudoku game table size type
     */
    enum SudokuSizeType {
        Four("4x4", 4, 2, 2),
        SIX("6x6", 6, 2, 3),
        NINE("9x9", 9, 3, 3),
        TWELVE("12x12", 12, 3, 4);

        String describe;
        int size;
        //  row count
        int x;
        //   column count
        int y;

        SudokuSizeType(String describe, int size, int x, int y) {
            this.describe = describe;
            this.size = size;
            this.x = x;
            this.y = y;
        }
    }

    private SudokuSizeType curType = SudokuSizeType.NINE;
    /**
     * Number of words to be filled
     */
    private int puzzleNum = 4;


    SudokuGenerator sudoku;                             // generates the initial sudoku config
    SudokuGenerator sudokuSolution;

    SudokuAdapter sudokuAdapter;
    /**
     * Selected language
     */
    Lang nativeLang, foreignLang;           // native & foreign lang from previous page
    String selected_subject;
    private boolean voiceMode = false;
    private int selectCode;
    ArrayList<Vocab> vocabs;
    ArrayList<Sudoku> sudokus;

    TextToSpeech tts;

    GridView gvSudo;
    GridLayout gvSudoRefs;
    CardView cv;
    Button btVoice;
    TextView tvTimeCost;



    private Timer timer;
    private int timeCount = 0;
    private String timeCostStr = "";
    private int difficulty = 1;

    private static String LANG_NATIVE = "LANG_NATIVE";
    private static String LANG_FOREIGN = "LANG_FOREIGN";
    private static String CUR_TYPE = "CUR_TYPE";
    private static String SUDOKU_DARA = "SUDOKU_DARA";
    private static String VOCABS = "VOCABS";
    private static String SELECTED_INDEX = "SELECTED_INDEX";
    private static String TIME_COST = "TIME_COST";
    private static String VOICE_MODE = "VOICE_MODE";
    private final static String KEY_BG = "BackgroundMusic";

    private int bgLength = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        boolean newGame = true;
        // Recovery state
        if (savedInstanceState != null) {
            nativeLang = (Lang) savedInstanceState.getSerializable(LANG_NATIVE);
            foreignLang = (Lang) savedInstanceState.getSerializable(LANG_FOREIGN);
            curType = (SudokuSizeType) savedInstanceState.getSerializable(CUR_TYPE);
            sudokus = (ArrayList<Sudoku>) savedInstanceState.getSerializable(SUDOKU_DARA);
            vocabs = (ArrayList<Vocab>) savedInstanceState.getSerializable(VOCABS);
            selectCode = savedInstanceState.getInt(SELECTED_INDEX);
            timeCount = savedInstanceState.getInt(TIME_COST);
            voiceMode = savedInstanceState.getBoolean(VOICE_MODE);
            newGame = false;
            bgLength = savedInstanceState.getInt(KEY_BG);
        } else {
            // getting data from previous screens
            nativeLang = Lang.CN;
            foreignLang = Lang.EN;
        }
        selected_subject = Utils.getString(this, Utils.KEY_SUBJECT);
        if (TextUtils.isEmpty(selected_subject)){
            selected_subject = VocabRepository.getInstance().getAllGroupsName().get(0);
        }
        difficulty = Utils.getInt(this, Utils.KEY_DIFFICULTY, 1);
        gvSudo = findViewById(R.id.gv_sudo);
        gvSudoRefs = findViewById(R.id.gv_sudo_refs);
        cv = findViewById(R.id.cv_voice);
        btVoice = findViewById(R.id.voiceMode);
        tvTimeCost = findViewById(R.id.tv_time_cost);
        if (timeCount > 0) timeCount();
        initSudoku(newGame);
        onVoiceModeChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!voiceMode) music.play(this, R.raw.welcome2,bgLength,GameActivity.class.getName());// play backmusic

    }

    private void timeCount() {
        cancelTimer();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    timeCostStr = String.format("%02d:%02d:%02d", timeCount / 3600, timeCount % 3600 / 60, timeCount % 60);
                    tvTimeCost.setText(timeCostStr);
                    timeCount++;
                });
            }
        }, 0, 1000);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        music.stop(GameActivity.class.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }

    private void cancelTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
            timeCount = 0;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save state when the screen is rotated
        outState.putSerializable(LANG_NATIVE, nativeLang);
        outState.putSerializable(LANG_FOREIGN, foreignLang);
        outState.putSerializable(CUR_TYPE, curType);
        outState.putSerializable(SUDOKU_DARA, sudokus);
        outState.putSerializable(VOCABS, vocabs);
        outState.putSerializable(SELECTED_INDEX, selectCode);
        outState.putSerializable(TIME_COST, timeCount);
        outState.putBoolean(VOICE_MODE, voiceMode);
        bgLength = music.pause(GameActivity.class.getName());
        outState.putInt(KEY_BG,bgLength);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_switch:
                switchModel();
                return true;
            case R.id.action_setting:
                settingGrid();
                return true;
            case R.id.action_check:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                String message = checkAnswer(false) ? "Correct so far" : "Something Incorrect";
                builder.setMessage(message).setPositiveButton("Continue", (dialog, which) -> {
                }).create().show();
                return true;
            case R.id.action_update:
                initSudoku(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void switchModel() {
        Lang tmp = foreignLang;
        foreignLang = nativeLang;
        nativeLang = tmp;
        initSudoku(false);
    }

    private void settingGrid() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item);
        final List<SudokuSizeType> list = new ArrayList<>(SudokuSizeType.values().length - 1);
        for (SudokuSizeType type : SudokuSizeType.values()) {
            if (curType != type) {
                list.add(type);
                arrayAdapter.add(type.describe);
            }
        }
        builder.setAdapter(arrayAdapter, (dialog, which) -> {
            curType = list.get(which);
            vocabs = new ArrayList<>(VocabRepository.getInstance().queryByGroup(selected_subject, curType.size));
            if (vocabs.size() < curType.size) {
                Toast.makeText(this, "Sorry,the current subject has fewer than " + curType.size + " words.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (getResources().getConfiguration().screenWidthDp < 550 && curType == SudokuSizeType.TWELVE) {
                Toast toast = Toast.makeText(getApplicationContext(), "12 X 12 is better in Landscape or Tablet.", Toast.LENGTH_LONG);
                LinearLayout toastView = (LinearLayout) toast.getView();
                toast.setGravity(Gravity.CENTER, 0, 0);
                ImageView imageCodeProject = new ImageView(getApplicationContext());
                imageCodeProject.setImageResource(R.drawable.attention);
                toastView.addView(imageCodeProject, 0);
                toast.show();
            }
            initSudoku(true);
        }).create().show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // init tts
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = tts.setLanguage(foreignLang.lo);
                if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE
                        && result != TextToSpeech.LANG_AVAILABLE) {
                    Toast.makeText(GameActivity.this, "TTS ERROR！",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void onClick(View view) {
        switch (view.getId()) {
            // submit answer
            case R.id.done_button:
                cancelTimer();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                String message = "Incorrect";
                if (checkAnswer(true)) {
                    message = "You win\n\nTime Cost: " + timeCostStr;
                } else {
                    VocabRepository.getInstance().update(vocabs);
                }
                builder.setMessage(message).setPositiveButton("Play Again", (dialog, which) -> initSudoku(true)).create().show();
                break;
            // voice mode open/close
            case R.id.voiceMode:

                voiceMode = !voiceMode;
                if (voiceMode){
                    bgLength = music.pause(GameActivity.class.getName());
                }else {
                    music.play(this, R.raw.welcome2,bgLength,GameActivity.class.getName());// play backmusic
                }
                sudokuAdapter.notifyDataSetInvalidated();
                onVoiceModeChanged();
                break;
            default:
                break;
        }
    }

    private void onVoiceModeChanged() {

        cv.setBackgroundColor(getResources().getColor(voiceMode ? R.color.orange : android.R.color.transparent));
        btVoice.setTextColor(getResources().getColor(voiceMode ? android.R.color.white : android.R.color.black));
    }

    /**
     * Initialize all Sudoku layouts
     */
    List<TextView> textViews;

    private void initSudoku(boolean newGame) {
        if (difficulty == 0) {
            puzzleNum =curType.size/2;//random.nextInt((curType.size / 2 - 1) + 1) + 1;
        } else if (difficulty == 1) {
            puzzleNum = curType.size * curType.size /6+curType.size;
        } else if (difficulty == 2) {
            puzzleNum = curType.size * curType.size /3+curType.size;
        } else {
            puzzleNum = curType.size * curType.size /2+curType.size;
        }
        gvSudoRefs.removeAllViews();
        gvSudoRefs.setColumnCount(curType.x);
        gvSudoRefs.setRowCount(curType.y);
        if (newGame) {
            selectCode = 0;
            vocabs = new ArrayList<>(VocabRepository.getInstance().queryByGroup(selected_subject, curType.size));
        }
        textViews = new ArrayList<>(vocabs.size());
        for (int i = 0; i < vocabs.size(); i++) {
            TextView tv = new TextView(getBaseContext());
            tv.setPadding(1, 10, 1, 10);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.reference_text_size));
            tv.setGravity(Gravity.CENTER);
            tv.setOnClickListener(v -> {
                tts.setLanguage(nativeLang.lo);
                if (voiceMode)
                    tts.speak(tv.getText().toString(), TextToSpeech.QUEUE_ADD, null);
                onPanelSelected(v);
            });
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();

            int currentCol = i / curType.y;
            int currentRow = (i - currentCol * curType.y) % curType.y;
            // The last parameter in the specs is the weight, which gives equal size to the cells
            layoutParams.columnSpec = GridLayout.spec(currentCol, 1, 1);
            layoutParams.rowSpec = GridLayout.spec(currentRow, 1, 1);
            textViews.add(tv);
            gvSudoRefs.addView(tv, layoutParams);
        }
        for (int i = 0; i < textViews.size(); i++) {
            textViews.get(i).setText(vocabs.get(i).getNativeWord(nativeLang));
        }

        onPanelSelected(textViews.get(selectCode));

        if (newGame) {
            // getting the initial sudoku grid configuration
            sudoku = new SudokuGenerator(curType.size);
            sudokuSolution = new SudokuGenerator(curType.size, curType.x, curType.y);
            while (!sudokuSolution.fillSolution()) {

            }

            for (int i = 0; i < curType.size; i++)
                System.arraycopy(sudokuSolution.mat[i], 0, sudoku.mat[i], 0, curType.size);

            sudoku.removeKDigits(puzzleNum);
            sudokus = new ArrayList<>(curType.size * curType.size);
            for (int i = 0; i < curType.size; i++) {
                for (int j = 0; j < curType.size; j++) {
                    int code = sudokuSolution.mat[i][j];
//                String w = vocabs.get(code - 1).getForeignWord(foreignLang);
                    sudokus.add(new Sudoku(vocabs.get(code - 1), code - 1, sudoku.mat[i][j] != 0));
                }
            }
            initSudokuAdapter();
            timeCount();
        } else {
            if (sudokuAdapter == null) {
                initSudokuAdapter();
            } else sudokuAdapter.notifyDataSetChanged();
        }
    }

    private void initSudokuAdapter() {
        sudokuAdapter = new SudokuAdapter(sudokus, this);
        gvSudo.setNumColumns(curType.size);
        gvSudo.setAdapter(sudokuAdapter);
        gvSudo.setOnItemClickListener((parent, view, position, id) -> {
            Sudoku s = sudokus.get(position);
            if (!s.isShow()) {
                s.setChoice(vocabs.get(selectCode));
                s.setChoiceCode(selectCode);
            }
            if (voiceMode && s.isShow()) {
                tts.setLanguage(foreignLang.lo);
                tts.speak(s.getChoice(foreignLang),
                        TextToSpeech.QUEUE_ADD, null);
            }
            sudokuAdapter.notifyDataSetChanged();
        });
    }

    private void onPanelSelected(View v) {
        for (int j = 0; j < textViews.size(); j++) {
            TextView t = textViews.get(j);
            if (t == v) {
                selectCode = j;
                t.setBackgroundColor(getResources().getColor(R.color.orange));
                t.setTextColor(getResources().getColor(android.R.color.white));
            } else {
                t.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                t.setTextColor(getResources().getColor(android.R.color.black));
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        music.stop(GameActivity.class.getName());
        tts.shutdown();
    }

    /**
     * adapter for sudoku GridView
     */
    class SudokuAdapter extends BaseAdapter {

        private List<Sudoku> data;
        private Context context;

        public SudokuAdapter(List<Sudoku> data, Context context) {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_sudoku, parent, false);
            }
            tv = convertView.findViewById(R.id.tv_item_sudoku);
            Sudoku item = (Sudoku) getItem(position);
            tv.setText(voiceMode ? String.valueOf(item.getChoiceCode() < 0 ? "?" : item.getChoiceCode() + 1) : item.getChoice(foreignLang));

            if (!item.isShow()) {
                tv.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                tv.setTextColor(getResources().getColor(android.R.color.white));
            } else {
                tv.setBackgroundColor(getResources().getColor(android.R.color.white));
                tv.setTextColor(getResources().getColor(android.R.color.black));
            }
            return convertView;
        }
    }

    // check if users answer is correct
    private boolean checkAnswer(boolean checkAll) {
        boolean correct = true;
        for (Sudoku s : sudokus) {
            if (checkAll) {
                if (!s.isCorrect()) {
                    correct = false;
                    vocabs.get(s.getCode()).errorMaked();
                }
            } else {
                if (s.getChoiceCode() >= 0 && !s.isCorrect()) {
                    correct = false;
                }
            }
        }
        return correct;
    }

}
