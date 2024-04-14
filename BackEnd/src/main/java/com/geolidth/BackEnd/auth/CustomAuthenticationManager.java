package com.geolidth.BackEnd.auth;



public interface CustomAuthenticationManager {
    AuthenticationResult authenticate(UserCredentials userCredentials);


}