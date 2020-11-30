package com.example.project731;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Mock
    FirebaseRegisterActivity f = new FirebaseRegisterActivity();
    @Mock
    private UserProfile uprofile;

    @Mock
    private ShoeProfileForLists shoe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        shoe = new ShoeProfileForLists("Off-White Jordan 1's Chicago",  "drawable://" + R.drawable.offwhitechicagos );
        uprofile = new UserProfile(-1,"testemail@test.com", shoe);;
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
    @Test
    public void getUserProfileName(){
        String name = "testemail@test.com";
        assertEquals(name,uprofile.getNewUser());
    }
    @Test
    public void getUserProfileShoeName(){
        String shoeName = "Off-White Jordan 1's Chicago";
        assertEquals(shoeName,uprofile.getShoeName());
    }
    @Test
    public void getShoeProfileShoeImage(){
        String shoeName = "drawable://" + R.drawable.offwhitechicagos;
        assertEquals(shoeName,shoe.getShoeImage());
    }



}