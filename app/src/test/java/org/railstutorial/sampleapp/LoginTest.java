package org.railstutorial.sampleapp;


import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.railstutorial.sampleapp.auth.LoginPresenter;
import org.railstutorial.sampleapp.auth.LoginView;
import org.railstutorial.sampleapp.auth.UserRepository;
import org.railstutorial.sampleapp.model.Envelop;
import org.railstutorial.sampleapp.model.User;


public class LoginTest {

    private LoginPresenter mLoginPresenter;

    @Mock
    AppModule mAppModule;

    @Mock
    LoginView mLoginView;

    @Mock
    UserModule mUserModule;

    @Mock
    UserRepository mUserManager;

    @Mock
    NetModule mNetModule;

    @Mock
    NetComponent mNetComponent;

    @Mock
    ArgumentCaptor<retrofit2.Callback<Envelop<User>>> mCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        DaggerNetComponent.builder()
                .appModule(mAppModule)
                .netModule(mNetModule)
                .userModule(mUserModule)
                .build();

//        mLoginPresenter = new LoginPresenter(mLoginView, mUserManager);
    }

    @Test
    public void testLogin() throws Exception {
        String username = "example@railstutorial.org";
        String password = "foobar";

        mLoginPresenter.login(username, password);

//        verify(mUserManager).performLogin(username, password, null);
    }
}
