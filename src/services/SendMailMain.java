package services;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rubenpaixao
 */
public class SendMailMain {
    public static void main(String[] args) {
        SendMail sm = new SendMail("smtp.gmail.com","465"); 
        String origem = "etoiletest@gmail.com";
        String destino = "ruben.npaixao@gmail.com";
        String assunto = "Registo ÉtoilePlatform";
        String mensagem = "Registo Efectuado com sucesso na plataforma Etoile\n A sua password é: XXXXXXX .";

        sm.sendMail(origem,destino,assunto,mensagem);       
    }
}
    