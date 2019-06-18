package com.ptit.android.kidslearning.Vocabulary;

public class Vocabulary {
    private int Id_Word;
    private String KeyWord, Tranlate, TopicID, Sound_Word, Image_Word;

    public Vocabulary() {
    }

    public Vocabulary(int id_Word, String keyWord, String tranlate, String topicID, String sound_Word, String image_Word) {
        Id_Word = id_Word;
        Tranlate = tranlate;
        KeyWord = keyWord;
        Sound_Word = sound_Word;
        TopicID = topicID;
        Image_Word = image_Word;
    }


    public int getId_Word() {
        return Id_Word;
    }

    public void setId_Word(int id_Word) {
        Id_Word = id_Word;
    }

    public String getSound_Word() {
        return Sound_Word;
    }

    public void setSound_Word(String sound_Word) {
        Sound_Word = sound_Word;
    }

    public String getTopicID() {
        return TopicID;
    }

    public void setTopicID(String topicID) {
        TopicID = topicID;
    }

    public String getTranlate() {
        return Tranlate;
    }

    public void setTranlate(String tranlate) {
        Tranlate = tranlate;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public String getImage_Word() {
        return Image_Word;
    }

    public void setImage_Word(String image_Word) {
        Image_Word = image_Word;
    }

}
