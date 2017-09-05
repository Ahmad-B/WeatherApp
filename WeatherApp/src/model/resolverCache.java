/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;

/**
 *
 * @author laszlojmoricz
 */
public class resolverCache {
//STORES COMMONLY USED PLACES, SO YAHOO WEATHER'S WOEID RESOLVER IS NOT NEEDED
    private HashMap<String, String> hm;

    public resolverCache() {
        hm = new HashMap<>();
        
        hm.put("london", "44418");
        hm.put("bristol", "13963");
        hm.put("brighton", "13911");
        hm.put("manchester", "28218");
        hm.put("leeds", "26042");
        hm.put("liverpool", "26734");
        hm.put("birmingham", "12723");
        hm.put("edinburgh", "19344");
        hm.put("glasgow", "21125");
        hm.put("cardiff", "15127");
        hm.put("newcastle", "30079");
    }
    //whitespace prior and followed by the string also have to be removed
    public String resolve(String location) throws Exception {
        if (hm.get(location.toLowerCase())==null) throw new Exception();
        return hm.get(location.toLowerCase());   
    }

}
