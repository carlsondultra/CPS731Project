package com.example.project731;

import android.database.sqlite.SQLiteDatabase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class UserProfileDatabaseHelperTest {
    @Mock
    private UserProfile uprofile;
    @Mock
    private UserProfileDatabaseHelper uPHelper;
    @Mock
   private ShoeProfileForLists shoe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        uPHelper = Mockito.mock(UserProfileDatabaseHelper.class);
        shoe = new ShoeProfileForLists("Off-White Jordan 1's Chicago",  "drawable://" + R.drawable.offwhitechicagos );
        uprofile = new UserProfile(-1,"testemail@test.com", shoe);;

    }
    @Test
    public void addOne() {
        uPHelper.addOne(uprofile);
        verify(uPHelper).addOne(uprofile);

    }

    @Test
    public void deleteOne() {
        uPHelper.deleteOne(uprofile);
        verify(uPHelper).deleteOne(uprofile);
    }

    @Test
    public void getEveryone() {
        uPHelper.getEveryone(uprofile.getNewUser());
        verify(uPHelper).getEveryone(uprofile.getNewUser());
    }
}