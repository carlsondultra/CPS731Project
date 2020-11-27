package com.example.project731;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    FirebaseRegisterActivity f = new FirebaseRegisterActivity();
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void textEmpty(){

        f.txt_email = "";
        f.name2 = "";
        f.txt_password = "";
        f.txt_password_confirm = "";
        assertEquals(false,f.registerNoToast());
    }
    @Test
    public void nameEmpty(){
        f.txt_email = "jeff@jeff.com";
        f.name2 = "";
        f.txt_password = "123456";
        f.txt_password_confirm = "123456";
        f.radioButton = null;
        assertEquals(false,f.registerNoToast());
    }
    @Test
    public void passTooShort(){
        f.txt_email = "jeff@jeff.com";
        f.name2 = "jeff";
        f.txt_password = "12345";
        f.txt_password_confirm = "123456";
        f.radioButton = null;
        assertEquals(false,f.registerNoToast());
    }
    @Test
    public void passDontMatch(){
        f.txt_email = "jeff@jeff.com";
        f.name2 = "jeff";
        f.txt_password = "123456";
        f.txt_password_confirm = "1234567";
        f.radioButton = null;
        assertEquals(false,f.registerNoToast());
    }
    @Test
    public void sexNotSelected(){
        f.txt_email = "jeff@jeff.com";
        f.name2 = "jeff";
        f.txt_password = "123456";
        f.txt_password_confirm = "123456";
        f.radioButton = null;
        assertEquals(false,f.registerNoToast());
    }

}