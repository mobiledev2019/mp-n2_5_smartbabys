package com.ptit.android.kidslearning.Game;

import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ptit.android.kidslearning.R;
import com.ptit.android.kidslearning.Utils.StringHandler;
import com.ptit.android.kidslearning.Vocabulary.Vocabulary;
import com.ptit.android.kidslearning.Vocabulary.VocabularyDataAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    TextView tvScore, tvTitleQuestion, tvIndexQues;
    ImageView imgQuestion, imgRadio;
    LinearLayout layoutText, layoutBtn, layoutBtn2;
    List<Vocabulary> list;
    ArrayList<Integer> listedIndex;
    LinearLayout.LayoutParams params;

    /**
     * Object string handler
     */
    StringHandler handler = new StringHandler();
    Random rd = new Random();
    TextView[] tv;
    Button[] btn;
    Vocabulary vocabulary;
    String tvCheck = "";

    /**
     * set static indexs
     */
    private static final int NUM_QUESTION = 10;
    private int Score = 0, m = 0;
    private int questionIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initialize();
    }



    /**
     * Initialize
     */
    //
    private void initialize() {
        mapping();
        VocabularyDataAccess access = new VocabularyDataAccess(GameActivity.this);// khởi tạo đối tượng lớp truy cập đối tượng
        list = access.getListVocabulary();
        listedIndex = new ArrayList<>();
        params = new LinearLayout.LayoutParams(60, 100, Gravity.CENTER_HORIZONTAL);
        params.setMargins(10, 0, 10, 0);
        setViewForLayoutText();
    }

    /**
     * Mapping
     */
    void mapping() {
        tvScore = findViewById(R.id.tv_score);
        tvTitleQuestion = findViewById(R.id.tv_title_question);
        tvIndexQues = findViewById(R.id.indexQues);
        imgQuestion = findViewById(R.id.img_question);
        imgRadio = findViewById(R.id.imgRadio);
        layoutText = findViewById(R.id.layout_text);
        layoutBtn = findViewById(R.id.layout_btn);
        layoutBtn2 = findViewById(R.id.layout_btn2);
    }

    /**
     * Set view for layout text
     */
    void setViewForLayoutText() {
        /**
         * /
         */
        if (questionIndex <= NUM_QUESTION) {
            reset();
            tvIndexQues.setText(questionIndex + "/10");// câu hỏi số mấy bao nhiu
            //Get object word
            vocabulary = randomVocab();// lấy từ ra
            imgQuestion.setImageResource(getResources().getIdentifier(vocabulary.getImage_Word(), "drawable", getPackageName()));
            // sự kiện nhấn vào load khi phát ra âm thanh
            imgRadio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MediaPlayer player = MediaPlayer.create(GameActivity.this,
                            getResources().getIdentifier(vocabulary.getSound_Word(), "raw", getPackageName()));
                    player.start();
                }
            });
            // tách các chữ cái trong từ
            ArrayList<Character> chars = handler.stringToChar(vocabulary.getKeyWord().toUpperCase());
            tv = new TextView[chars.size()];
            for (int i = 0; i < tv.length; i++) {
                tv[i] = new TextView(this);
                tv[i].setLayoutParams(params);
                tv[i].setBackgroundResource(R.drawable.text_bkg);
                tv[i].setTextColor(Color.RED);
                tv[i].setTextSize(30);
                layoutText.addView(tv[i]);
            }
            setViewForLayoutBtn(chars);//
            event();
        } else {
            dialogShow();
        }
    }

    /**
     * Set event
     */
    //
    void event() {
        if (m < tv.length) {
            for (int n = 0; n < btn.length; n++) {
                final int finalN = n;
                btn[n].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv[m].setText(btn[finalN].getText() + "");
                        btn[finalN].setVisibility(View.INVISIBLE);
                        btn[finalN].setEnabled(false);
                        m++;
                        event();
                    }
                });
            }
        } else {
            for (TextView aTv : tv) {
                tvCheck = tvCheck + aTv.getText();
            }
            if (vocabulary.getKeyWord().toUpperCase().equals(tvCheck)) {
                Score += 10;
                tvScore.setText(Score + "");
            }
            Toast.makeText(GameActivity.this, "Đáp án đúng là " + vocabulary.getKeyWord().toUpperCase(), Toast.LENGTH_LONG).show();
            questionIndex++;
            setViewForLayoutText();
        }
    }

    /**
     * // hiển thị các chữ cái cho lựa chọn
     * Set view for layout button
     */
    void setViewForLayoutBtn(ArrayList<Character> chars) {
        btn = new Button[10];
        ArrayList<Character> cList = listForBtn(chars);
        for (int j = 0; j < btn.length; j++) {
            btn[j] = new Button(this);
            btn[j].setLayoutParams(params);
            btn[j].setBackgroundResource(R.drawable.button_bkg);
            btn[j].setText(cList.get(j) + "");
            btn[j].setTextColor(getResources().getColor(R.color.white));
            if (j < 5) {
                layoutBtn.addView(btn[j]);
            } else
                layoutBtn2.addView(btn[j]);
        }
    }
    /**
     * Dialog for finish
     */
    void dialogShow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_game, null));

        //set negative button
        builder.setNegativeButton(R.string.play_again, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                questionIndex = 1;
                Score = 0;
                setViewForLayoutText();
                dialog.dismiss();
            }
        });
        //set positive button
        builder.setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.create().show();
    }

    /**
     * Get character random for button
     */
    // sử lý rodam các chữ cái để cho lựa chon
    ArrayList<Character> listForBtn(ArrayList<Character> list1) {
        ArrayList<Character> characterArrayList = new ArrayList<>();
        Character[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        characterArrayList.addAll(list1);
        for (int i = characterArrayList.size(); i < 10; i++) {
            int a = rd.nextInt(characters.length - 1);
            characterArrayList.add(characters[a]);
        }
        return handler.randomSort(characterArrayList);
    }

    /**
     * Reset question
     */
    void reset() {
        layoutText.removeAllViews();
        layoutBtn.removeAllViews();
        layoutBtn2.removeAllViews();
        tvCheck = "";
        m = 0;
    }

    /**
     * / lấy ngẫu nhiên 1 từ
     * Get random a word
     */
    Vocabulary randomVocab() {
        int index;
        do {
            index = rd.nextInt(list.size() - 1);
        } while (checkPosition(index));
        listedIndex.add(index);
        return list.get(index);
    }

    /**
     * / kiểm tra vị trí tồn tại trong listIndex
     * Check position exists in listIndex
     */
    boolean checkPosition(int p) {
        boolean check = false;
        for (int i : listedIndex
        ) {
            if (i == p) {
                check = true;
                break;
            } else
                check = false;
        }
        return check;
    }
}