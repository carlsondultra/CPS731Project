package com.example.project731;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.After;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class FirebaseLoginActivityTest {
    private FirebaseLoginActivity fL;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        fL = Mockito.mock(FirebaseLoginActivity.class);

    }

    @Test
    public void successfulLogin(){
        fL.loginUser("male@m.com","123456");
        verify(fL).loginUser("male@m.com","123456");

    }
}