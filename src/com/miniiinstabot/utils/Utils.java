package com.miniiinstabot.utils;

import com.miniiinstabot.model.UserAuthModel;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    
    public List<UserAuthModel> getUserAuthList(List<String> txt){
        
        List<UserAuthModel> userAuthModels = new ArrayList<>();
        userAuthModels.clear();
        for(String line : txt){
            if(line.contains("-")){
                String[] s = line.split("-");
                if(s[0].contains("Login: ") && s[1].contains("Password: ")){
                    
                    String uId = s[0].replace("Login: ", "");
                    String pass = s[1].replace("Password: ", "");
                    
                    userAuthModels.add(new UserAuthModel(uId.trim(), pass.trim()));
                    
                }
            }
        }
        
        return userAuthModels;
        
    }
    
}
