class Solution {
    public String solution(String new_id) {
        String step1 = new_id.toLowerCase();
        
        StringBuilder _step2 = new StringBuilder();
        for(int i=0; i<step1.length(); i++) {
            char c = step1.charAt(i);
            if(Character.isLowerCase(c) || Character.isDigit(c) || c == '-' || c == '_' || c == '.' ) {
                _step2.append(c);
            }
        }
        String step3 = _step2.toString().replaceAll("[.]+", ".");
    	
        StringBuilder step4 = new StringBuilder(step3);
        if(step4.length() > 0 && step4.charAt(0) == '.')
            step4.deleteCharAt(0);
        if(step4.length() > 0 && step4.charAt(step4.length()-1) == '.')
            step4.deleteCharAt(step4.length()-1);
     	String _step4 = step4.toString();
        
        StringBuilder step5 = (step4.length() > 0) ? step4 : step4.append("a");
        String _step5 = step5.toString();
        
        StringBuilder step6 = step5;
        if(step6.length() >= 16)
            step6.setLength(15);
        if(step6.length() > 0 && step6.charAt(step6.length()-1) == '.')
            step6.deleteCharAt(step6.length()-1);
        String _step6 = step6.toString();
        
        StringBuilder step7 = step6;
        int lastIndex = step7.length() - 1;
        while(step7.length() <= 2) {
            step7.append(step7.charAt(lastIndex));
        }
        String _step7 = step7.toString();
        
        return _step7.toString();
    }
    
    public boolean isValidId(String id) {
        // Check case 1
        if(id.length() < 3 || id.length() > 15) {
            return false;
        }
        // Check case 2
        for(int i=0; i<id.length(); i++) {
            char c = id.charAt(i);
            if(!(Character.isLowerCase(c) || Character.isDigit(c) || c == '-' || c == '_' || c == '.' )) {
                return false;
            }
        }
        System.out.println("test");
        // Check case 3
        if(id.indexOf(0) == '.' || id.indexOf(id.length()-1) == '.')
            return false;
        int countEndCharacter = 0;
        for(int i=0; i<id.length(); i++) {
            char c = id.charAt(i);
            if(c == '.')
                countEndCharacter++;
            else
                countEndCharacter = 0;
            if(countEndCharacter == 2)
                return false;
        }
        return true;
    }
}