package app.utilits;

public class CredenciaisInvalidasException extends Exception {
    
    public CredenciaisInvalidasException(){
        super("Erro: Credenciais Inválidas");
    }
}
