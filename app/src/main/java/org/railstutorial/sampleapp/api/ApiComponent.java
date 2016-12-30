package org.railstutorial.sampleapp.api;

import org.railstutorial.sampleapp.auth.LoginActivity;

//@Component(dependencies = NetComponent.class, modules = ApiModule.class)
public interface ApiComponent {

    void inject(LoginActivity activity);

}
