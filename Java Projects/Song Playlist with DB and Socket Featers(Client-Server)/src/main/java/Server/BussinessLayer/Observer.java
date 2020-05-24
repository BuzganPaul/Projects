package Server.BussinessLayer;


public abstract class Observer {
   protected FriendsManager subject;
   public abstract void update();
}
