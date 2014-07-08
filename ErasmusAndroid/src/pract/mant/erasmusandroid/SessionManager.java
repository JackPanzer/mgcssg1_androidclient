package pract.mant.erasmusandroid;
import java.util.HashMap;

import pract.mant.alumno.PrincipalAlumno;
import pract.mant.modelo.ComplexUsuario;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Clase usada para registrar la sesion del usuario que esta registrado en la aplicación
 * @author Betanzos
 *
 */

public class SessionManager {
	 
    SharedPreferences pref;	// Shared Preferences
    Editor editor;    		// Editor for Shared preferences   
    Context _context;		// Context
     
    int PRIVATE_MODE = 0;	// Shared pref mode
     
    // Sharedpref file name
    private static final String PREF_NAME = "ClienteAndroid";
    
    
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn"; 
    
    
    // Id Usuario
    public static final String KEY_ID = "id";
     
    // Nick Name
    public static final String KEY_NICK = "nick";
    
    // User name 
    public static final String KEY_NAME = "name";
     
     
    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    
    /**
     * Crea una sesion de usuario
     * @param _miUsuario contiene los datos del usuario actual
     */
    public void createLoginSession(ComplexUsuario _miUsuario){
       
    	// Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        
        int id = _miUsuario.getId();
        editor.putString(KEY_ID, String.valueOf(id));        
        editor.putString(KEY_NICK, _miUsuario.getNick());
        editor.putString(KEY_NAME, _miUsuario.getNombre() +" "+ _miUsuario.getApellidos());
       
        editor.commit();
    }
    
    /**
     * Este metodo comprueba que verdaderamente el usuario está logueado,
     * si no se sale de la aplicación
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, MainActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             
            // Staring Login Activity
            _context.startActivity(i);
        }
         
    }
    
    
    /**
     * Devuelve los datos almacenados de la sesion
     * @return tabla hash con los datos de la sesion
     */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        
        
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
         
        // user nick
        user.put(KEY_NICK, pref.getString(KEY_NICK, null));
        
        // user id
        user.put(KEY_ID, pref.getString(KEY_ID, null));
         
        // return user
        return user;
    }
    
    /**
     * Borra los datos de la sesion actual y sale de la sesion
     */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
         
        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, MainActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         
        // Staring Login Activity
        _context.startActivity(i);
    }
    
    /**
     * Comprueba si está en la aplicación
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
