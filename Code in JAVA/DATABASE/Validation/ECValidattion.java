package ECUtils;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.regex.*;

public class ECValidattion {

    public static interface ECValidator {

        public String validate(String s1);
    }

    public static class ReqValidator implements ECValidator {

        public String validate(String s1) {
            String res = null;
            try {
                if (s1 == null || "".equalsIgnoreCase(s1)) {
                    res = "Field is required!";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        }
    }

    public static class PNValidator implements ECValidator {

        public String validate(String pn) {
            if(pn==null || "".equalsIgnoreCase(pn.trim())){
                return null;
            }
            String res = null;
            try {
                if (Pattern.matches("^0?[6-9]{1}\\d{9}$", pn) == false) {
                    res = "Invalid Phone No!";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        }
    }
    
    
    public static class WebSiteValidator implements ECValidator {

        public String validate(String pn) {
            if(pn==null || "".equalsIgnoreCase(pn.trim())){
                return null;
            }
            String res = null;
            try {
                if (Pattern.matches("^\\w+\\.\\w+$", pn) == false) {
                    res = "Invalid Website!";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        }
    }

    
     public static class AadharValidator implements ECValidator {

        public String validate(String pn) {
            if(pn==null || "".equalsIgnoreCase(pn.trim())){
                return null;
            }
            String res = null;
            try {
                if (Pattern.matches("^\\d{12}$", pn) == false) {
                    res = "Invalid Adhar No!";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        }
    }


    public static class PinCodeValidator implements ECValidator {

        public String validate(String pin) {
            if(pin==null || "".equalsIgnoreCase(pin.trim())){
                return null;
            }
            String res = null;
            try {
                if (Pattern.matches("^\\d{6}$", pin) == false) {
                    res = "Invalid Phone No!";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        }
    }

    public static class NameAndReqValidator implements ECValidator {

        public String validate(String name) {
            String res = null;
            try {
                if (Pattern.matches("^[a-z,A-Z, ]+$", name) == false) {
                    res = "Invalid Name!";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        }
    }
    
        public static class NameValidator implements ECValidator {

        public String validate(String name) {
            if(name==null || "".equalsIgnoreCase(name.trim())){
                return null;
            }
            String res = null;
            try {
                if (Pattern.matches("^[a-z,A-Z, ]+$", name) == false) {
                    res = "Invalid Name!";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        }
    }


    public static class IntValidator implements ECValidator {

        public String validate(String no) {
            if(no==null || "".equalsIgnoreCase(no.trim())){
                return null;
            }
            String res = null;
            try {
                Integer.parseInt(no);
            } catch (Exception e) {
                res = "Invalid Integer!";
            }
            return res;
        }
    }

    public static class DoubleValidator implements ECValidator {

        public String validate(String no) {
            if(no==null || "".equalsIgnoreCase(no.trim())){
                return null;
            }
            String res = null;
            try {
                Double.parseDouble(no);
            } catch (Exception e) {
                res = "Invalid double value!";
            }
            return res;
        }
    }

    public static class BooleanValidator implements ECValidator {

        public String validate(String no) {
            if(no==null || "".equalsIgnoreCase(no.trim())){
                return null;
            }
            String res = null;
            try {
                if ("true".equalsIgnoreCase(no) || "false".equalsIgnoreCase(no)) {
                } else {
                    res = "Invalid Boolean!";
                }
            } catch (Exception e) {
            }
            return res;
        }
    }

    public static class EmailValidator implements ECValidator {

        public String validate(String name) {
            if(name==null || "".equalsIgnoreCase(name.trim())){
                return null;
            }
            String res = null;
            try {
                if (Pattern.matches("^\\w+@\\w+\\.\\w+$", name) == false) {
                    res = "Invalid EMail";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        }
    }

    public static class DateValidator implements ECValidator {

        public String validate(String dateStr) {
            if(dateStr==null || "".equalsIgnoreCase(dateStr.trim())){
                return null;
            }
            String res = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date d1 = sdf.parse(dateStr);
                String temp = sdf.format(d1);
                if (dateStr.equals(temp)) {
                } else {
                    res = "Invalid Date!!";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        }
    }

    public static class PassValidator implements ECValidator {

        public String validate(String pass) {
            String res = null;
            try {
                if (Pattern.matches(".*[^\\w\\s].*", pass) == false) {
                    res = "Special Symbol is required!";
                }
                if (pass.length() < 6) {
                    res = "length is too short, min length 6!";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        }
    }
}
