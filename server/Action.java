public class Action {
    String name;

    public Action(){
        name = "";
    }
    public void active(Client c, String name){
        this.name = name;
        switch (this.name) {
            case "Eat":
                c.Send_Action_toClient(this.name);
                break;
            case "Water":
                c.Send_Action_toClient(this.name);
                break;
            case "Buy":
                c.Send_Action_toClient("spawn");
                break;
            case "w":
                System.out.println("Đây là số 4");
                break;
            case "l":
                System.out.println("Đây là số 5");
                break;
            default:
                break;
        }
    }

}
