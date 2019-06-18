package com.ptit.android.kidslearning.Alphabel;

public class Alphabet {
    private int Id_Alphabet;
    private String Name_Alphabet, Sound_Alphabet, Image_Alphabet, SoundExample, ImageExample, NameExample;

    Alphabet(int id_Alphabet, String name_Alphabet, String sound_Alphabet, String image_Alphabet, String soundExample, String imageExample, String nameExample) {
        Id_Alphabet = id_Alphabet;
        ImageExample = imageExample;
        SoundExample = soundExample;
        Image_Alphabet = image_Alphabet;
        Sound_Alphabet = sound_Alphabet;
        Name_Alphabet = name_Alphabet;
        NameExample = nameExample;
    }

    public String getNameExample() {
        return NameExample;
    }

    public void setNameExample(String nameExample) {
        NameExample = nameExample;
    }

    public String getSoundExample() {
        return SoundExample;
    }

    public void setSoundExample(String soundExample) {
        SoundExample = soundExample;
    }

    public String getImageExample() {
        return ImageExample;
    }

    public void setImageExample(String imageExample) {
        ImageExample = imageExample;
    }

    public Alphabet() {
    }

    public int getId_Alphabet() {
        return Id_Alphabet;
    }

    public void setId_Alphabet(int id_Alphabet) {
        Id_Alphabet = id_Alphabet;
    }

    public String getImage_Alphabet() {
        return Image_Alphabet;
    }

    public void setImage_Alphabet(String image_Alphabet) {
        Image_Alphabet = image_Alphabet;
    }

    public String getSound_Alphabet() {
        return Sound_Alphabet;
    }

    public void setSound_Alphabet(String sound_Alphabet) {
        Sound_Alphabet = sound_Alphabet;
    }

    public String getName_Alphabet() {
        return Name_Alphabet;
    }

    public void setName_Alphabet(String name_Alphabet) {
        Name_Alphabet = name_Alphabet;
    }
}
