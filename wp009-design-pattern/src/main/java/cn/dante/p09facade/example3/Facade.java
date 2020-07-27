package cn.dante.p09facade.example3;

public class Facade {
   public void done(){
       new Presentation().generate();
       new Business().generate();
       new DAO().generate();
   }
}
