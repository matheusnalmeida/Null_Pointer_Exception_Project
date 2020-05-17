package app.utilits;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Matheus Nunes
 */
public class CRM {

    private String crm;

    public CRM(String crm) throws CRMInvalidoException {
        String[] ufs = {"RO", "AC", "AM", "RR", "PA", "AP", "TO", "MA", "PI", "CE", "RN",
            "PB", "PE", "AL", "SE", "BA", "MG", "ES", "RJ", "SP", "PR", "SC", "RS", "MS", "MT", "GO", "DF"};
        String regex1 = "^[0-9]{4,10}/(" + (String.join("|", ufs)) + ")$";
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(crm.toUpperCase().trim());
        if (matcher1.find()) {
            this.crm = matcher1.group();
        }else{
            throw new CRMInvalidoException("CRM Inválido");
        }
    }

    public String getCrm() {
        return crm;
    }

}
