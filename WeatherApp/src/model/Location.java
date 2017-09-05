package model;

/**
 *
 * @author laszlojmoricz
 */
public class Location {
//TRIES TO RESOLVE WOEID WITHOUT YAHOO'S API
    public static String locationResolver(String location) {
    
        resolverCache checkFirst = new resolverCache();
        
        try{
            return checkFirst.resolve(location);
        }   catch(Exception e){
        
                try { return Parser.parseMe(location);
                }   catch (Exception f) {
                    return ("Bad request");
                    }           
        }        
    }    

}
